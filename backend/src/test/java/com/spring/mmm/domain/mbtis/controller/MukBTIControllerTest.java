package com.spring.mmm.domain.mbtis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResult;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResultResponse;
import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIAnswerEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.mbtis.service.MukBTIService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = MukBTIController.class)
class MukBTIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MukBTIService mukBTIService;

    @InjectMocks
    private MukBTIController mukBTIController;

    private static List<MukBTIAnswerEntity> mukBTIAnswerEntities;
    private static List<MukBTIQuestionEntity> mukBTIQuestionEntities;
    private static String mukBTIKey;
    private static MBTI mbti;
    private static MukBTIResult mukBTIResult;
    private static MukBTIResultResponse mukBTIResultResponse;
    private static MukBTICalcRequest mukBTICalcRequest;
    private static ObjectMapper objectMapper;
    @BeforeAll
    static void 자료장전(){
        objectMapper = new ObjectMapper();
        //매퍼장전

        mukBTIAnswerEntities = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            mukBTIAnswerEntities.add(
                    MukBTIAnswerEntity.builder()
                            .answerId(i)
                            .content(Integer.toString(i))
                            .score(50)
                            .imageSrc(Integer.toString(i))
                            .build()
            );
        }
        // 답변장전

        mukBTIQuestionEntities = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            mukBTIQuestionEntities.add(
                    MukBTIQuestionEntity.builder()
                            .questionId(i)
                            .content(Integer.toString(i))
                            .imageSrc(Integer.toString(i))
                            .mukBTIAnswerEntities(mukBTIAnswerEntities)
                            .build()
            );
        }
        // 질문장전

        mukBTIKey = "1234";
        // 먹비티아이 키

        mbti = MBTI.builder()
                .ei(100)
                .ns(50)
                .tf(50)
                .jp(100)
                .mint(100)
                .pine(0)
                .die(100)
                .build();
        // 먹비티아이 장전

        mukBTIResult = MukBTIResult.builder()
                .key(mukBTIKey)
                .mbti(mbti)
                .build();
        // 먹비티아이 결과 장전

        mukBTIResultResponse = MukBTIResultResponse.builder()
                .mukBTIResult(mukBTIResult)
                .build();
        // 먹비티아이 결과 반환값 장전
        
        mukBTICalcRequest = MukBTICalcRequest.builder()
                .answers(new ArrayList<>())
                .build();
        // 먹비티아이 계산 요청 장전
    }

    @WithMockUser
    @Test
    void 질문받기_성공() throws Exception{
        BDDMockito.given(mukBTIService.findAllMukBTIQuestion())
                .willReturn(mukBTIQuestionEntities);

        mockMvc.perform(MockMvcRequestBuilders.get("/mbti"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.mukBTIQuestions.length()").value(5)
                )
                .andDo(print());
    }

    @WithMockUser
    @Test
    void 먹비티아이_계산_성공() throws Exception{
        BDDMockito.given(mukBTIService.calcMBTI(any()))
                .willReturn(mukBTIResult);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/mbti")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mukBTICalcRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(
                        status().isCreated()
                )
                .andDo(print());
    }
}