package com.spring.mmm.domain.muklogs.service.port;

import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MuklogRepository {
    Page<MuklogEntity> findAllMuklogByGroupId(Long groupId, Pageable pageable);

    void save(MuklogEntity muklogEntity);
}
