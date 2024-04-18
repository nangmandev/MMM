package com.spring.mmm.domain.mukjuks.service;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.TestFixture;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.mukjuks.controller.response.MukjukResponse;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import com.spring.mmm.domain.mukjuks.event.FoodRecordedEvent;
import com.spring.mmm.domain.mukjuks.exception.MukjukErrorCode;
import com.spring.mmm.domain.mukjuks.exception.MukjukException;
import com.spring.mmm.domain.mukjuks.service.port.MukGroupMukjukRepository;
import com.spring.mmm.domain.mukjuks.service.port.MukjukRepository;
import com.spring.mmm.domain.muklogs.exception.MukgroupNotFoundException;
import com.spring.mmm.domain.muklogs.exception.MuklogErrorCode;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MukjukServiceImplTest {

    @Mock
    MukjukRepository mukjukRepository;
    @Mock
    MukgroupRepository mukgroupRepository;
    @Mock
    MukboRepository mukboRepository;


    @Mock
    RecommendedFoodRepository recommendedFoodRepository;
    @Mock
    MukGroupMukjukRepository mukGroupMukjukRepository;

    @InjectMocks
    MukjukServiceImpl mukjukService;

    @Test
    void 먹적_조회_테스트_성공() throws Exception {
        // given
        Long groupId = 1L;
        UserDetailsImpl users = new UserDetailsImpl(
                UserEntity.builder()
                        .id(1L)
                        .build()
                , "tester@naver.com");


        given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(TestFixture.mukgroupEntity));
        given(mukboRepository.findByUserId(any()))
                .willReturn(Optional.of(TestFixture.mukboEntityGroup));
        given(mukjukRepository.findAllBadges(any()))
                .willReturn(TestFixture.badges);

        // when
        MukjukResponse sut = mukjukService.findAllMukjuks(groupId, users);
        // then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(sut.getTitleMukjukId()).isEqualTo(1);
        sa.assertThat(sut.getBadges().size()).isSameAs(10);
        sa.assertAll();
    }


    @Test
    void 먹적_조회_테스트_실패_내가_그룹에_속하지_않는_경우() throws Exception {
        Long groupId = 1L;
        UserDetailsImpl users = new UserDetailsImpl(
                UserEntity.builder()
                        .id(1L)
                        .build()
                , "tester@naver.com");

        given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(TestFixture.mukgroupEntity));
        given(mukboRepository.findByUserId(any()))
                .willReturn(Optional.of(TestFixture.mukboEntitySologroup));
        // when // then
        assertThatThrownBy(() -> mukjukService.findAllMukjuks(groupId, users))
                .isInstanceOf(MukGroupException.class)
                .hasFieldOrPropertyWithValue("errorCode", MukGroupErrorCode.FORBIDDEN);
    }

    @Test
    void 먹적_조회_테스트_실패_그룹이_솔로_그룹인_경우() throws Exception {
        Long groupId = 1L;
        UserDetailsImpl users = new UserDetailsImpl(
                UserEntity.builder()
                        .id(1L)
                        .build()
                , "tester@naver.com");

        given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(TestFixture.soloMukgroupEntity));
        // when // then
        assertThatThrownBy(() -> mukjukService.findAllMukjuks(groupId, users))
                .isInstanceOf(MukGroupException.class)
                .hasFieldOrPropertyWithValue("errorCode", MukGroupErrorCode.SOLO_CANT_ACCESS_MUKJUK);
    }

    // recommendId를 기반으로 카테고리 가져오기
    // 그룹 아이디를 뽑아내기. -> 그 그룹이 깨지 못한 먹적 목록 가져오기
    // 카테고리에 해당하는 먹어스 카운트 가져오기
    // -> 먹적 목록 중 카테고리에 해당하는 먹적의 조건과 매칭/(성공/실패) 해서 성공 or 실패 구분하기
    // 성공할 경우 먹그룹먹적 엔티티 만들어주기
    // 먹로그 이벤트 발행하기.
    @Test
    void 먹어스_기록_이벤트_핸들링_테스트_성공_먹적을_달성하지_못한_경우() throws Exception {
        // given
        FoodRecordedEvent foodRecordedEvent = new FoodRecordedEvent() {
            @Override
            public Long getFoodRecommendedId() {
                return 1L;
            }
        };
        given(recommendedFoodRepository.findByRecommendedFoodId(any()))
                .willReturn(Optional.of(TestFixture.recommendedFood));
        given(mukjukRepository.findUnclearedMukjuk(any()))
                .willReturn(TestFixture.mukjuks);
        given(mukgroupRepository.countMukusByFoodCategory(any(), any()))
                .willReturn(1);
        // when
        mukjukService.handleFoodRecordedEvent(foodRecordedEvent);
    }

    @Test
    void 먹어스_기록_이벤트_핸들링_테스트_성공_새로운_먹적을_달성한_경우() throws Exception {
        // given
        FoodRecordedEvent foodRecordedEvent = new FoodRecordedEvent() {
            @Override
            public Long getFoodRecommendedId() {
                return 1L;
            }
        };
        given(recommendedFoodRepository.findByRecommendedFoodId(any()))
                .willReturn(Optional.of(TestFixture.recommendedFood));
        given(mukjukRepository.findUnclearedMukjuk(any()))
                .willReturn(TestFixture.mukjuks);
        given(mukgroupRepository.countMukusByFoodCategory(any(), any()))
                .willReturn(3);
        // when
        mukjukService.handleFoodRecordedEvent(foodRecordedEvent);
    }

    @Test
    void 먹어스_기록_이벤트_핸들링_테스트_실패_전달받은_아이디에_해당하는_추천음식이_없는_경우() throws Exception {
        // given
        FoodRecordedEvent foodRecordedEvent = new FoodRecordedEvent() {
            @Override
            public Long getFoodRecommendedId() {
                return 1L;
            }
        };
        given(recommendedFoodRepository.findByRecommendedFoodId(any()))
                .willReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> mukjukService.handleFoodRecordedEvent(foodRecordedEvent))
                .isInstanceOf(MukjukException.class)
                .hasFieldOrPropertyWithValue("errorCode", MukjukErrorCode.RECOMMENDED_FOOD_NOT_FOUND);
    }

    @Test
    void 먹어스_기록_이벤트_핸들링_테스트_실패_먹적_매칭이_실패한_경우() throws Exception {
        // given
        FoodRecordedEvent foodRecordedEvent = new FoodRecordedEvent() {
            @Override
            public Long getFoodRecommendedId() {
                return 1L;
            }
        };
        given(recommendedFoodRepository.findByRecommendedFoodId(any()))
                .willReturn(Optional.of(TestFixture.recommendedFood));
        given(mukjukRepository.findUnclearedMukjuk(any()))
                .willReturn(List.of(MukjukEntity.builder().name("").build()));
        given(mukgroupRepository.countMukusByFoodCategory(any(), any()))
                .willReturn(3);

        // when
        assertThatThrownBy(() -> mukjukService.handleFoodRecordedEvent(foodRecordedEvent))
                .isInstanceOf(MukjukException.class)
                .hasFieldOrPropertyWithValue("errorCode", MukjukErrorCode.MUKJUK_NOT_MATCHED);
    }


}