package com.spring.mmm.domain.weathers.infra;

import com.spring.mmm.domain.weathers.domain.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherJpaRepository extends JpaRepository<WeatherEntity, Integer> {

    WeatherEntity findByWeatherId(Integer weatherId);
}
