package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import com.spring.mmm.domain.mukjuks.service.port.MukjukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MukjukRepositoryImpl implements MukjukRepository {

    private final MukjukJpaRepository mukjukJpaRepository;
    @Override
    public List<Badge> findAllBadges(Long groupId) {
        return mukjukJpaRepository.findAllMukjuks(groupId);
    }

    @Override
    public List<MukjukEntity> findUnclearedMukjuk(Long groupId) {
        return mukjukJpaRepository.findUnclearedMukjuk(groupId);
    }

    @Override
    public Long getMukgetIdIfUncleared(Long groupId, String mukjukTitle) {
        return mukjukJpaRepository.getMukgetIdIfUncleared(groupId, mukjukTitle);
    }
}
