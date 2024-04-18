package com.spring.mmm.domain.mukgroups.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;

import java.util.List;
import java.util.Optional;

public interface MukboRepository {
    void save(MukboEntity mukboEntity);

    void saveAndFlush(MukboEntity mukboEntity);

    Optional<MukboEntity> findByUserId(Long userId);

    List<MukboEntity> findAllMukboByGroupId(Long groupId);

    Optional<MukboEntity> findByMukboId(Long mukboId);

    void delete(MukboEntity mukboEntity);
}
