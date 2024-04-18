package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.infra.MukgroupJpaRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MukgroupServiceImplTest {

    @Mock
    private MukgroupRepository mukgroupRepository;

    @Mock
    private MukboRepository mukboRepository;

    @Mock
    private MukBTIResultRepository mukBTIResultRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MukgroupServiceImpl mukgroupService;

    private static UserEntity user;
    private static UserEntity userWithMukbo;
    private static UserEntity userWithMukboSoloGroup;
    private static MukgroupEntity soloMukgroupEntity;
    private static MukgroupEntity mukgroupEntity;
    private static MukboEntity mukboEntitySologroup;

    private static MukboEntity mukboEntityGroup;
    private static MukboEntity mukbotEntity;
    private static MukBTIResultEntity mukBTIResult;

    @BeforeAll
    static void 자료장전(){

        user = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .build();
        // 유저장전
        
        mukgroupEntity = MukgroupEntity.builder()
                .isSolo(Boolean.FALSE)
                .mukgroupId(1L)
                .name("ssafygroup")
                .build();
        // 먹그룹장전
        
        soloMukgroupEntity = MukgroupEntity.builder()
                .mukgroupId(2L)
                .isSolo(Boolean.TRUE)
                .name("ssafysologroup")
                .build();
        // 솔로먹그룹장전

        mukboEntitySologroup = MukboEntity.builder()
                .mukboId(1L)
                .type(MukboType.HUMAN)
                .name("mukssafy")
                .userEntity(user)
                .mukgroupEntity(soloMukgroupEntity)
                .build();
        // 솔로그룹먹보장전

        mukboEntityGroup = MukboEntity.builder()
                .mukboId(1L)
                .type(MukboType.HUMAN)
                .name("mukssafy")
                .userEntity(user)
                .mukgroupEntity(mukgroupEntity)
                .build();
        // 다인그룹먹보장전
        
        mukbotEntity = MukboEntity.builder()
                .mukboId(3L)
                .type(MukboType.MUKBOT)
                .name("mukbot")
                .mukgroupEntity(mukgroupEntity)
                .build();
        // 먹봇장전

        userWithMukbo = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .mukboEntity(mukboEntityGroup)
                .build();
        // 먹보와함께유저

        userWithMukboSoloGroup = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .mukboEntity(mukboEntitySologroup)
                .build();
        // 솔로먹그룹유저

        mukBTIResult = MukBTIResultEntity.builder()
                .score(30)
                .build();
        // 먹비티아이장전
    }

    @Test
    void 솔로_먹그룹_생성_성공(){

        BDDMockito.given(mukboRepository.findByUserId(1L))
                        .willReturn(Optional.of(mukboEntitySologroup));

        BDDMockito.given(userRepository.findByEmail(any()))
                        .willReturn(Optional.of(user));

        assertDoesNotThrow(() -> mukgroupService.saveSoloMukGroup(user.getEmail()));
    }

    @Test
    void 다인_먹그룹_중복_생성_실패(){
        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukbo));

        assertThrows(MukGroupException.class, () -> mukgroupService.saveMukGroup("1234", userWithMukbo.getEmail(), null));
    }

    @Test
    void 내_먹그룹_찾기_성공(){

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukbo));

        assertDoesNotThrow(() -> mukgroupService.findMyMukgroup(userWithMukbo.getEmail()));
    }

    @Test
    void 먹그룹_찾기_성공(){
        BDDMockito.given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(mukgroupEntity));

        assertDoesNotThrow(() -> mukgroupService.findMukgroupById(1L));
    }

    @Test
    void 없는_먹그룹_찾기_실패(){
        BDDMockito.given(mukgroupRepository.findByMukgroupId(any()))
                .willThrow(new MukGroupException(MukGroupErrorCode.NOT_FOUND));

        assertThrows(MukGroupException.class, () -> mukgroupService.findMukgroupById(1L));
    }

    @Test
    void 먹그룹_이름_수정_성공(){
        BDDMockito.given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(mukgroupEntity));

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukbo));

        assertDoesNotThrow(() -> mukgroupService.modifyGroupName(1L, "1234", userWithMukbo.getEmail()));
    }

    @Test
    void 먹보_강퇴_성공(){
        BDDMockito.given(mukboRepository.findByMukboId(any()))
                .willReturn(Optional.of(mukboEntityGroup));

        BDDMockito.given(mukboRepository.findByUserId(any()))
                        .willReturn(Optional.of(mukboEntityGroup));

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukbo));
        
        assertDoesNotThrow(() -> mukgroupService.kickMukbo(userWithMukbo.getEmail(), 1L, 1L));
    }

    @Test
    void 먹봇_강퇴_성공(){
        BDDMockito.given(mukboRepository.findByMukboId(any()))
                .willReturn(Optional.of(mukbotEntity));

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukbo));

        assertDoesNotThrow(() -> mukgroupService.kickMukbo(userWithMukbo.getEmail(), 1L, 3L));
    }

    @Test
    void 해당_먹그룹과_먹보_먹그룹이_일치하지않음_실패(){
        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukbo));

        assertThrows(MukGroupException.class, () -> mukgroupService.kickMukbo(userWithMukbo.getEmail(), 9999L, 9999L));
    }

    @Test
    void 다른_그룹_소속_먹보_강퇴_실패(){
        BDDMockito.given(mukboRepository.findByMukboId(any()))
                        .willReturn(Optional.of(mukboEntityGroup));

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukboSoloGroup));

        assertThrows(MukGroupException.class, () -> mukgroupService.kickMukbo(userWithMukboSoloGroup.getEmail(), 2L, 1L));
    }

    @Test
    void 다인_먹그룹_나가기_성공(){
        BDDMockito.given(mukboRepository.findByUserId(any()))
                .willReturn(Optional.of(mukboEntitySologroup));

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukbo));

        assertDoesNotThrow(() -> mukgroupService.exitMukgroup(userWithMukbo.getEmail(), 1L));
    }

    @Test
    void 혼자_남은_다인_먹그룹_나가기_성공(){
        BDDMockito.given(mukboRepository.findByUserId(any()))
                        .willReturn(Optional.of(mukboEntityGroup));

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukbo));

        assertDoesNotThrow(() -> mukgroupService.exitMukgroup(userWithMukbo.getEmail(), 1L));
    }

    @Test
    void 솔로_먹그룹_나가기_실패(){
        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(userWithMukboSoloGroup));

        assertThrows(MukGroupException.class, () -> mukgroupService.exitMukgroup(userWithMukboSoloGroup.getEmail(), 3L));
    }

    @Test
    void 먹비티아이_계산_성공(){
        BDDMockito.given(mukBTIResultRepository.findAllMukBTIResultByMukboIdAndMukBTIType(any(), any()))
                .willReturn(List.of(mukBTIResult));

        assertDoesNotThrow(() -> mukgroupService.calcGroupMukBTI(1L, MukgroupMBTICalcRequest.builder().build()));
    }
}