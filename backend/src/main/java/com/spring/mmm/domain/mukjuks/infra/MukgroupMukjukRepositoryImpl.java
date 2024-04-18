package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukjuks.domain.MukgroupMukjukEntity;
import com.spring.mmm.domain.mukjuks.service.port.MukGroupMukjukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MukgroupMukjukRepositoryImpl implements MukGroupMukjukRepository {

    private final MukGroupMukjukJpaRepository mukGroupMukjukJpaRepository;

    public void save(MukgroupMukjukEntity mukgroupMukjuk) {
        mukGroupMukjukJpaRepository.save(mukgroupMukjuk);
    }
}
