package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MukboServiceTest {

    @Mock private MukboRepository mukboRepository;
    @InjectMocks private MukboServiceImpl mukboService;

    static UserEntity userEntity;
    static MukgroupEntity mukgroupEntity;

    @BeforeAll
    static void 엔티티장전(){
         userEntity = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .build();

         mukgroupEntity = MukgroupEntity.builder()
                 .mukgroupId(1L)
                 .name("ssafyGroup")
                 .isSolo(Boolean.FALSE)
                 .imageSrc("ssafyGroupImage")
                 .build();
    }

    @Test
    void 내먹그룹조회_성공(){
    }
}