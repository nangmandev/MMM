package com.spring.mmm.domain.recommends.controller.response;

import com.spring.mmm.domain.weathers.domain.WeatherEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class WeatherFoodInfo {
    private String weatherName;
    private FoodInformation foodInformation;

    public static WeatherFoodInfo create(WeatherEntity weatherEntity, FoodInformation foodInformation) {
        return WeatherFoodInfo.builder()
                .weatherName(weatherEntity.getName())
                .foodInformation(foodInformation)
                .build();
    }
}
