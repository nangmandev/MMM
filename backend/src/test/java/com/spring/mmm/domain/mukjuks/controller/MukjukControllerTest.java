package com.spring.mmm.domain.mukjuks.controller;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import com.spring.mmm.domain.mukjuks.controller.response.MukjukResponse;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import com.spring.mmm.domain.mukjuks.service.MukjukService;
import com.spring.mmm.domain.muklogs.exception.MukgroupNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MukjukController.class)
class MukjukControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MukjukService mukjukService;
    
    @InjectMocks
    MukjukController mukjukController;

    static final String MUKJUK_RETRIEVE_URL = "/groups/1/badges";

    @WithMockUser
    @Test
    void 먹적_조회_테스트_성공() throws Exception{
        // given


        MukgroupEntity mukgroup = MukgroupEntity
                .builder()
                .mukjukEntity(
                        MukjukEntity.builder()
                        .mukjukId(1L)
                        .build())
                .build();
        List<Badge> badges = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            badges.add(Badge.builder()
                            .id((long) i)
                            .name("먹적"+i)
                            .isCleared(i<5)
                    .build());
        }
        given(mukjukService.findAllMukjuks(any(), any()))
                        .willReturn(MukjukResponse.create(mukgroup, badges));
        // when
        mvc.perform(MockMvcRequestBuilders.get(MUKJUK_RETRIEVE_URL))
        // then
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.titleMukjukId").value(1),
                        jsonPath("$.badges.length()").value(10)
                )
                .andDo(print());
    }

    @WithMockUser
    @Test
    void 먹적_조회_테스트_실패_내가_그룹에_속하지_않는_경우() throws Exception{
        // given
        given(mukjukService.findAllMukjuks(any(), any()))
                .willThrow(new MukGroupException(MukGroupErrorCode.FORBIDDEN));
        // when
        mvc.perform(MockMvcRequestBuilders.get(MUKJUK_RETRIEVE_URL))
                // then
                .andExpectAll(
                        status().isForbidden(),
                        jsonPath("$.errorName").value("FORBIDDEN"),
                        jsonPath("$.errorMessage").value("이 먹그룹에 접근할 권한이 없습니다.")
                )
                .andDo(print());
    }
    @WithMockUser
    @Test
    void 먹적_조회_테스트_실패_그룹이_솔로_그룹인_경우() throws Exception{
        // given
        given(mukjukService.findAllMukjuks(any(), any()))
                .willThrow(new MukGroupException(MukGroupErrorCode.SOLO_CANT_ACCESS_MUKJUK));
        // when
        mvc.perform(MockMvcRequestBuilders.get(MUKJUK_RETRIEVE_URL))
                // then
                .andExpectAll(
                        status().isForbidden(),
                        jsonPath("$.errorName").value("SOLO_CANT_ACCESS_MUKJUK"),
                        jsonPath("$.errorMessage").value("솔로는 먹적 기능을 이용할 수 없습니다.")
                )
                .andDo(print());
    }

}