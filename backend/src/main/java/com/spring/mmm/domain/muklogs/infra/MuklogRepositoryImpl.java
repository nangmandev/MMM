package com.spring.mmm.domain.muklogs.infra;

import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import com.spring.mmm.domain.muklogs.service.port.MuklogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MuklogRepositoryImpl implements MuklogRepository {
    private final MuklogJpaRepository muklogJpaRepository;
    @Override
    public Page<MuklogEntity> findAllMuklogByGroupId(Long groupId, Pageable pageable) {
        return muklogJpaRepository.findAllMuklogByGroupId(groupId, pageable);
    }

    @Override
    public void save(MuklogEntity muklogEntity) {
        muklogJpaRepository.save(muklogEntity);
    }
}
