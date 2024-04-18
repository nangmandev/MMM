package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukbotCreateRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukbotInviteRequest;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.users.exception.UserException;
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
class MukboServiceImplTest {

    @Mock
    private MukboRepository mukboRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MukBTIResultRepository mukBTIResultRepository;

    @Mock
    private MukBTIRepository mukBTIRepository;

    @InjectMocks
    private MukboServiceImpl mukboService;

    private static MukboEntity mukboEntity;
    private static MukboEntity anotherGroupMukbo;
    private static MukboEntity mukbotEntity;
    private static MukboEntity soloMukboEntity;
    private static MBTI mbti;
    private static MukboInviteRequest mukboInviteRequest;
    private static MukbotInviteRequest mukbotInviteRequest;
    private static List<MukBTIResultEntity> mukBTIResultEntities;
    private static List<MukBTIEntity> mukBTIEntities;
    private static UserEntity user;
    private static UserEntity anotherGroupUser;
    private static UserEntity sologroupUser;
    private static MukbotCreateRequest mukbotCreateRequest;
    private static MukgroupEntity mukgroupEntity;
    private static MukgroupEntity soloMukgroupEntity;

    @BeforeAll
    static void 자료장전(){
        mukBTIEntities = new ArrayList<>();
        for(MukBTIType type : MukBTIType.values()){
            mukBTIEntities.add(
                    MukBTIEntity.builder()
                            .type(type)
                            .build()
            );
        }
        // 먹비티아이

        mukBTIResultEntities = new ArrayList<>();
        for(MukBTIEntity entity : mukBTIEntities){
            mukBTIResultEntities.add(
                    MukBTIResultEntity.createByType(30, entity, mukboEntity)
            );
        }
        // 한 먹보에 대한 먹비티아이 결과

        mbti = MBTI.builder()
                .ei(70)
                .ns(50)
                .tf(30)
                .jp(10)
                .mint(100)
                .pine(100)
                .die(100)
                .build();
        // 먹비티아이

        mukgroupEntity = MukgroupEntity.builder()
                .mukgroupId(1L)
                .name("123")
                .isSolo(Boolean.FALSE)
                .build();

        soloMukgroupEntity = MukgroupEntity.builder()
                .mukgroupId(2L)
                .name("456")
                .isSolo(Boolean.TRUE)
                .build();

        mukboInviteRequest = MukboInviteRequest.builder()
                .email("ssafy2@ssafy.com")
                .nickname("ssafy2")
                .mukbotId(1L)
                .build();
        // 먹보초대

        mukbotInviteRequest = MukbotInviteRequest.builder()
                .name("ssafy")
                .mbti(mbti)
                .build();
        // 먹봇초대

        mukboEntity = MukboEntity.builder()
                .mukboId(1L)
                .type(MukboType.HUMAN)
                .name("mukbo")
                .mukgroupEntity(mukgroupEntity)
                .mukBTIResultEntities(mukBTIResultEntities)
                .build();
        // 먹보

        mukboEntity = MukboEntity.builder()
                .mukboId(1L)
                .type(MukboType.HUMAN)
                .name("mukbo")
                .mukgroupEntity(mukgroupEntity)
                .mukBTIResultEntities(mukBTIResultEntities)
                .build();

        anotherGroupMukbo = MukboEntity.builder()
                .mukboId(2L)
                .type(MukboType.HUMAN)
                .name("mukbo")
                .mukgroupEntity(soloMukgroupEntity)
                .mukBTIResultEntities(mukBTIResultEntities)
                .build();
        
        soloMukboEntity = MukboEntity.builder()
                .mukboId(3L)
                .type(MukboType.HUMAN)
                .name("solomukbo")
                .mukgroupEntity(soloMukgroupEntity)
                .mukBTIResultEntities(mukBTIResultEntities)
                .build();
        // 솔로먹보

        mukbotEntity = MukboEntity.builder()
                .mukboId(2L)
                .type(MukboType.MUKBOT)
                .name("mukbot")
                .mukBTIResultEntities(mukBTIResultEntities)
                .build();
        // 먹봇

        anotherGroupUser = UserEntity.builder()
                .id(1L)
                .email("ssafy2@ssafy.com")
                .nickname("ssafy2")
                .mukboEntity(anotherGroupMukbo)
                .mukBTIResultEntities(mukBTIResultEntities)
                .build();
        // 유저

        user = UserEntity.builder()
                .id(2L)
                .email("ssafy1@ssafy.com")
                .nickname("ssafy1")
                .mukboEntity(mukboEntity)
                .mukBTIResultEntities(mukBTIResultEntities)
                .build();
        // 타그룹유저

        sologroupUser = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .mukboEntity(soloMukboEntity)
                .mukBTIResultEntities(mukBTIResultEntities)
                .build();
        // 솔로그룹 유저

        mukbotCreateRequest = MukbotCreateRequest.builder()
                .name("1234")
                .mbti(mbti)
                .build();
        // 먹봇생성요청
    }

    @Test
    void 먹그룹_모든먹보및먹비티아이_조회_성공(){
        BDDMockito.given(mukboRepository.findAllMukboByGroupId(any()))
                .willReturn(List.of(mukboEntity));

        assertDoesNotThrow(() -> mukboService.findAllMukboResponsesByGroupId(1L));
    }

    @Test
    void 먹그룹_모든먹봇및먹비티아이_조회_성공(){
        BDDMockito.given(mukboRepository.findAllMukboByGroupId(any()))
                .willReturn(List.of(mukboEntity, mukbotEntity));

        assertDoesNotThrow(() -> mukboService.findAllMukbotResponsesByGroupId(1L));
        assertEquals(1, mukboService.findAllMukbotResponsesByGroupId(1L).size());
    }

    @Test
    void 먹보초대_성공(){
        
        BDDMockito.given(mukboRepository.findByMukboId(any()))
                .willReturn(Optional.of(soloMukboEntity));

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user))
                .willReturn(Optional.of(anotherGroupUser));
        
        assertDoesNotThrow(() -> mukboService.inviteMukbo(user.getEmail(), 1L, mukboInviteRequest));
    }

    @Test
    void 먹보초대_유효하지_않은_이메일_실패(){
        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.empty());

        assertThrows(UserException.class, () -> mukboService.inviteMukbo(user.getEmail(), 1L, mukboInviteRequest));
    }

    @Test
    void 먹봇수정_성공(){
        BDDMockito.given(mukboRepository.findByMukboId(any()))
                .willReturn(Optional.of(mukboEntity));

        BDDMockito.given(mukBTIResultRepository.findAllMukBTIResultByMukboId(any()))
                .willReturn(mukBTIResultEntities);

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        assertDoesNotThrow(() -> mukboService.modifyMukbot(user.getEmail(), 2L, mbti, "ssafy123"));
    }

    @Test
    void 먹보수정_성공(){
        BDDMockito.given(mukboRepository.findByUserId(any()))
                .willReturn(Optional.of(mukboEntity));

        assertDoesNotThrow(() -> mukboService.modifyMokbo(1L, "ssasfy1234"));
    }

    @Test
    void 먹보생성_성공(){
        BDDMockito.given(mukBTIRepository.findAllMukBTI())
                        .willReturn(mukBTIEntities);

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        assertDoesNotThrow(() -> mukboService.saveMukbot(user.getEmail(), mukbotCreateRequest));
    }

    @Test
    void 솔로그룹_먹보생성_실패(){
        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(sologroupUser));

        assertThrows(MukGroupException.class, () -> mukboService.saveMukbot(sologroupUser.getEmail(), mukbotCreateRequest));
    }

    @Test
    void 먹보삭제_성공(){
        BDDMockito.given(mukboRepository.findByMukboId(any()))
                        .willReturn(Optional.of(mukboEntity));

        assertDoesNotThrow(() -> mukboService.deleteMukbo(1L));
    }

}