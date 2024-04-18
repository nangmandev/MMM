package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.mbtis.exception.MukBTIErrorCode;
import com.spring.mmm.domain.mbtis.exception.MukBTIException;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MukBTIRepositoryImpl implements MukBTIRepository {
    private final MukBTIJpaRepository mukBTIJpaRepository;
    @Override
    public List<MukBTIEntity> findAllMukBTI() {
        return mukBTIJpaRepository.findAllMukBTI();
    }
}
