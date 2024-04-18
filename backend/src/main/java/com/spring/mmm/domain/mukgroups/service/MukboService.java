package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukbotCreateRequest;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;

import java.util.List;

public interface MukboService {

    List<MukboResponse> findAllMukboResponsesByGroupId(Long groupId);

    List<MukboResponse> findAllMukbotResponsesByGroupId(Long groupId);

    void inviteMukbo(String email, Long groupId, MukboInviteRequest mukboInviteRequest);

    void modifyMukbot(String email, Long mukbotId, MBTI mbti, String name);

    void modifyMokbo(Long userId, String name);

    void saveMukbot(String email, MukbotCreateRequest mukbotCreateRequest);

    void deleteMukbo(Long mukbotId);
}
