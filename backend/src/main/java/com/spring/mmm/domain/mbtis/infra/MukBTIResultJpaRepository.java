package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MukBTIResultJpaRepository extends JpaRepository<MukBTIResultEntity, Integer> {

    @Query("select mr from MukBTIResultEntity mr where mr.mukboEntity.mukboId=:mukboId")
    List<MukBTIResultEntity> findAllMukBTIResultByMukboId(@Param("mukboId") Long mukboId);

    @Query("select mr from MukBTIResultEntity mr where mr.mukboEntity.mukboId in :mukboId and mr.mukBTIEntity.type=:mukBTIType")
    List<MukBTIResultEntity> findAllMukBTIResultByMukboIdAndMukBTIType(@Param("mukboId") List<Long> mukboId, @Param("mukBTIType")MukBTIType mukBTIType);
}
