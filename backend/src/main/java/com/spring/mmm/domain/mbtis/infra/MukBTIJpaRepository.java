package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MukBTIJpaRepository extends JpaRepository<MukBTIEntity, Integer> {

    @Query("select m from MukBTIEntity m where m.type=:mukBTIType")
    Optional<MukBTIEntity> findMukBTIByMukBTIType(MukBTIType mukBTIType);

    @Query("select m from MukBTIEntity m")
    List<MukBTIEntity> findAllMukBTI();
}
