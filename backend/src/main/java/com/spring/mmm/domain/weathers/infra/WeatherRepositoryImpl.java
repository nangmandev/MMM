package com.spring.mmm.domain.weathers.infra;

import com.spring.mmm.domain.weathers.domain.WeatherEntity;
import com.spring.mmm.domain.weathers.service.port.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherJpaRepository weatherJpaRepository;

    @Override
    public WeatherEntity findByWeatherId(Integer weatherId) {
        return weatherJpaRepository.findByWeatherId(weatherId);
    }
}
