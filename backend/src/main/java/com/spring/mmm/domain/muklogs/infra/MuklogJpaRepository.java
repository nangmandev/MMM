package com.spring.mmm.domain.muklogs.infra;

import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MuklogJpaRepository extends JpaRepository<MuklogEntity, Long> {

    @Query("select ml from MuklogEntity ml where ml.mukgroupEntity.mukgroupId=:groupId")
    Page<MuklogEntity> findAllMuklogByGroupId(@Param("groupId") Long groupId, Pageable pageable);
}
