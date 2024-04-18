package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.common.service.RedisRepository;
import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcInfo;
import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResult;
import com.spring.mmm.domain.mbtis.domain.*;
import com.spring.mmm.domain.mbtis.exception.MukBTIException;
import com.spring.mmm.domain.mbtis.service.port.MukBTIQuestionRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MukBTIServiceImplTest {

    @Mock
    private MukBTIQuestionRepository mukBTIQuestionRepository;

    @Mock
    private MukBTIRepository mukBTIRepository;

    @Mock
    private MukBTIResultRepository mukBTIResultRepository;

    @Mock
    private RedisRepository redisRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MukBTIServiceImpl mukBTIService;

    private static MukBTICalcRequest mukBTICalcRequest;
    private static List<MukBTIQuestionEntity> questionEntities;
    private static MBTI mbti;
    private static UserEntity user;
    private static MukboEntity mukboEntity;
    private static List<MukBTIEntity> mukBTIs;
    private static List<MukBTIResultEntity> mukBTIResultEntities;
    private static List<MukBTIResultEntity> emptyMukBTIResultEntity;

    @BeforeAll
    static void 먹비티아이_자료장전() {
        List<MukBTICalcInfo> mukBTICalcInfos = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            mukBTICalcInfos.add(MukBTICalcInfo.builder()
                    .quizId(i)
                    .answerId(i)
                    .build()
            );
        }
        // 질문리스트 장전

        mukBTICalcRequest = MukBTICalcRequest.builder()
                .answers(mukBTICalcInfos)
                .build();
        // 요청 장전

        questionEntities = new ArrayList<>();
        int i = 1;
        for (MukBTIType type : MukBTIType.values()) {
            List<MukBTIAnswerEntity> answers = new ArrayList<>();
            answers.add(MukBTIAnswerEntity.builder()
                    .answerId(i)
                    .score(i)
                    .build());

            questionEntities.add(
                    MukBTIQuestionEntity.builder()
                            .questionId(i++)
                            .mukBTIEntity(MukBTIEntity.builder()
                                    .type(type)
                                    .build())
                            .mukBTIAnswerEntities(answers)
                            .build()
            );
        }
        // 질문답변장전

        mbti = MBTI.builder()
                .ei(70)
                .ns(50)
                .tf(30)
                .jp(10)
                .mint(100)
                .pine(100)
                .die(100)
                .build();
        // MBTI장전

        mukboEntity = MukboEntity.create("ssafy", MukboType.HUMAN, null);
        // 먹보장전

        user = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .mukboEntity(mukboEntity)
                .build();
        // 유저장전
        
        mukBTIs = new ArrayList<>();
        for(MukBTIType type : MukBTIType.values()){
            mukBTIs.add(MukBTIEntity.builder()
                    .type(type)
                    .build());
        }
        // 먹비티아이 카테고리 장전

        mukBTIResultEntities = new ArrayList<>();
        for(MukBTIType type : MukBTIType.values()){
            mukBTIResultEntities.add(
                    MukBTIResultEntity.createByType(100, MukBTIEntity.builder().type(type).build(), mukboEntity)
            );
        }
        // 먹비티아이결과 장전

        emptyMukBTIResultEntity = new ArrayList<>();
        // 빈 먹비티아이결과 장전
    }

    @Test
    void 먹비티아이_질문수신_성공() {
        BDDMockito.given(mukBTIQuestionRepository.findAllMukBTIQuestion())
                .willReturn(questionEntities);
        assertEquals(7, mukBTIService.findAllMukBTIQuestion().size());
    }

    @Test
    void 먹비티아이_계산_성공() {
        BDDMockito.given(mukBTIQuestionRepository.findAllMukBTIQuestion())
                .willReturn(questionEntities);
        MukBTIResult result = mukBTIService.calcMBTI(mukBTICalcRequest);

        assertNotNull(result.getKey());
        assertNotNull(result.getMbti());
    }

    @Test
    void 먹비티아이_없는질문_들이댐_실패() {
        List<MukBTICalcInfo> test = new ArrayList<>();
        test.add(MukBTICalcInfo.builder()
                .quizId(8)
                        .answerId(7)
                .build()
        );

        MukBTICalcRequest testRequest = MukBTICalcRequest.builder()
                        .answers(test)
                                .build();

        BDDMockito.given(mukBTIQuestionRepository.findAllMukBTIQuestion())
                .willReturn(questionEntities);

        assertThrows(MukBTIException.class, () -> mukBTIService.calcMBTI(testRequest));
    }

    @Test
    void 먹비티아이_없는답변_들이댐_실패() {
        List<MukBTICalcInfo> test = new ArrayList<>();
        test.add(MukBTICalcInfo.builder()
                .quizId(7)
                        .answerId(1)
                .build()
        );

        MukBTICalcRequest testRequest = MukBTICalcRequest.builder()
                .answers(test)
                .build();

        BDDMockito.given(mukBTIQuestionRepository.findAllMukBTIQuestion())
                .willReturn(questionEntities);

        assertThrows(MukBTIException.class, () -> mukBTIService.calcMBTI(testRequest));
    }

    @Test
    void NULL값이_포함됨_실패(){
        List<MukBTICalcInfo> test = new ArrayList<>();
        test.add(MukBTICalcInfo.builder()
                .answerId(1)
                .build()
        );

        MukBTICalcRequest testRequest = MukBTICalcRequest.builder()
                .answers(test)
                .build();

        assertThrows(MukBTIException.class, () -> mukBTIService.calcMBTI(testRequest));
    }

    @Test
    void 먹비티아이_존재하지않는_키값_실패(){
        BDDMockito.given(redisRepository.getData(any(), any()))
                .willReturn(Optional.empty());

        assertThrows(MukBTIException.class, () -> mukBTIService.save(user.getEmail(), "1234"));
    }

    @Test
    void 먹비티아이_저장_성공(){
        BDDMockito.given(redisRepository.getData(any(), any()))
                .willReturn(Optional.of(mbti));

        BDDMockito.given(mukBTIRepository.findAllMukBTI())
                .willReturn(mukBTIs);

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        assertDoesNotThrow(() -> mukBTIService.save(user.getEmail(), "1234"));
    }

    @Test
    void 먹비티아이_확인_성공(){
        BDDMockito.given(mukBTIResultRepository.findAllMukBTIResultByMukboId(any()))
                .willReturn(mukBTIResultEntities);

        BDDMockito.given(userRepository.findByEmail(any()))
                        .willReturn(Optional.of(user));

        assertDoesNotThrow(() -> mukBTIService.getMukBTI(user.getEmail()));
    }

    @Test
    void 먹비티아이_확인_빈_결과_실패(){
        BDDMockito.given(mukBTIResultRepository.findAllMukBTIResultByMukboId(any()))
                .willReturn(emptyMukBTIResultEntity);

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        assertThrows(MukBTIException.class, () -> mukBTIService.getMukBTI(user.getEmail()));
    }

    @Test
    void 먹비티아이_확인_결과없음_실패(){
        BDDMockito.given(mukBTIResultRepository.findAllMukBTIResultByMukboId(any()))
                .willReturn(new ArrayList<>());

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        assertThrows(MukBTIException.class, () -> mukBTIService.getMukBTI(user.getEmail()));
    }
}