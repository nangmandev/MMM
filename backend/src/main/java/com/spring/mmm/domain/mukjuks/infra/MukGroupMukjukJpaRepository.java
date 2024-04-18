package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukjuks.domain.MukgroupMukjukEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MukGroupMukjukJpaRepository extends JpaRepository<MukgroupMukjukEntity, Long> {
}
