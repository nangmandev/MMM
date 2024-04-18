package com.spring.mmm.domain.recommends.domain;

import com.spring.mmm.domain.weathers.domain.WeatherEntity;
import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food_weather")
@Entity
public class FoodWeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_weather_id")
    private Integer foodWeatherId;

    @OneToOne
    @JoinColumn(name = "food_id")
    private FoodEntity foodEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id")
    private WeatherEntity weatherEntity;
}
