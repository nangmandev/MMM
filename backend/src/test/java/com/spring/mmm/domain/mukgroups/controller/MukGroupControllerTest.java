package com.spring.mmm.domain.mukgroups.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mmm.domain.CustomMockUser;
import com.spring.mmm.domain.TestFixture;
import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.*;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;
import com.spring.mmm.domain.mukgroups.controller.response.MukbosResponse;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.MukboService;
import com.spring.mmm.domain.mukgroups.service.MukgroupService;
import com.spring.mmm.domain.muklogs.service.MuklogService;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MukGroupController.class)
class MukGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MuklogService muklogService;

    @MockBean
    private MukboService mukboService;

    @MockBean
    private MukgroupService mukgroupService;

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private MukGroupController mukGroupController;

    private static ObjectMapper objectMapper;
    private static MBTI mbti;
    private static UserEntity user;
    private static MukboEntity mukboEntity;
    private static MukgroupEntity mukgroupEntity;

    @BeforeAll
    static void 자료장전(){
        objectMapper = new ObjectMapper();
        // 오브젝트매퍼 장전

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

        mukgroupEntity = MukgroupEntity.create("123", Boolean.FALSE);
        mukboEntity = MukboEntity.builder()
                .mukboId(1L)
                .name("123123")
                .type(MukboType.HUMAN)
                .mukgroupEntity(mukgroupEntity)
                .build();
        user = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .isRecorded(Boolean.FALSE)
                .mukboEntity(mukboEntity)
                .build();

    }

    @CustomMockUser
    @Test
    void 먹그룹_찾기_성공() throws Exception{
        BDDMockito.given(mukgroupService.findMyMukgroup(any()))
                .willReturn(TestFixture.mukgroupEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/groups"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹그룹_만들기_성공() throws Exception{
        MukgroupCreateRequest mukgroupCreateRequest = MukgroupCreateRequest.builder()
                .name("ssafy")
                .build();

        MockMultipartFile req = new MockMultipartFile("data", null, "application/json", objectMapper.writeValueAsString(mukgroupCreateRequest).getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart(HttpMethod.POST, "/groups")
                        .file(req)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹그룹_이름_수정_성공() throws Exception{
        MukgroupModifyRequest mukgroupModifyRequest = MukgroupModifyRequest.builder()
                .name("newName")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/groups/1/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mukgroupModifyRequest))
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹그룹_모든_먹보_찾기_성공() throws Exception{
        List<MukboResponse> mukboResponses = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            mukboResponses.add(
                    MukboResponse.builder()
                            .mukboId((long)i)
                            .name(Integer.toString(i))
                            .type(MukboType.HUMAN)
                            .build()
            );
        }

        BDDMockito.given(mukboService.findAllMukboResponsesByGroupId(any()))
                        .willReturn(mukboResponses);

        mockMvc.perform(MockMvcRequestBuilders.get("/groups/1/users").with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹그룹_모든_먹봇_찾기_성공() throws Exception{
        List<MukboResponse> mukbotResponses = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            mukbotResponses.add(
                    MukboResponse.builder()
                            .mukboId((long)i)
                            .name(Integer.toString(i))
                            .type(MukboType.MUKBOT)
                            .build()
            );
        }

        BDDMockito.given(mukboService.findAllMukboResponsesByGroupId(any()))
                .willReturn(mukbotResponses);

        mockMvc.perform(MockMvcRequestBuilders.get("/groups/1/users").with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹봇_저장_성공() throws Exception{
        MukbotCreateRequest mukbotCreateRequest = MukbotCreateRequest.builder()
                .name("ssafy")
                .mbti(mbti)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/groups/1/mukbots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mukbotCreateRequest))
                .with(csrf()))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 유저_초대_성공() throws Exception{
        MukboInviteRequest mukboInviteRequest = MukboInviteRequest.builder()
                .email("ssafy@ssafy.com")
                .nickname("safyy")
                .mukbotId(1L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/groups/1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mukboInviteRequest))
                .with(csrf()))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹보이름_수정_성공() throws Exception{
        MukboModifyRequest mukboModifyRequest = MukboModifyRequest.builder()
                .name("change")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/groups/1/users/1/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mukboModifyRequest))
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹보_제거_성공() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/groups/1/mukbos/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹그룹_탈퇴_성공() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/groups/1/exit")
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @CustomMockUser
    @Test
    void 먹그룹_MBTI_계산() throws Exception{
        MukgroupMBTICalcRequest mbtiCalcRequest = MukgroupMBTICalcRequest.builder()
                .mukbos(new ArrayList<>())
                .build();

        BDDMockito.given(mukgroupService.calcGroupMukBTI(any(), any()))
                .willReturn(mbti);

        mockMvc.perform(MockMvcRequestBuilders.post("/groups/1/mbti")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mbtiCalcRequest))
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }

}