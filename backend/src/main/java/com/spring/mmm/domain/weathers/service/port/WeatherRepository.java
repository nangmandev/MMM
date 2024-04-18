package com.spring.mmm.domain.weathers.service.port;

import com.spring.mmm.domain.weathers.domain.WeatherEntity;

public interface WeatherRepository {

    WeatherEntity findByWeatherId(Integer weatherId);
}
