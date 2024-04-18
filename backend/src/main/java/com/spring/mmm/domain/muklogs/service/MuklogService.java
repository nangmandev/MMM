package com.spring.mmm.domain.muklogs.service;

import com.spring.mmm.domain.muklogs.controller.response.MuklogsResponse;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.springframework.data.domain.Pageable;

public interface MuklogService {
    MuklogsResponse findAllMuklogByGroupId(Long groupId, Pageable pageable, UserEntity userEntity);

    void saveLog(Long mukgroupId, String content);
}
