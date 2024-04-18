package com.spring.mmm.domain.mbtis.service.port;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;

import java.util.List;

public interface MukBTIResultRepository {
    List<MukBTIResultEntity> findAllMukBTIResultByMukboId(Long mukboId);

    List<MukBTIResultEntity> findAllMukBTIResultByMukboIdAndMukBTIType(List<Long> mukboId, MukBTIType mukBTIType);

    void saveAll(List<MukBTIResultEntity> mukBTIResultEntities);

    void deleteAll(List<MukBTIResultEntity> mukBTIResultEntities);
}
