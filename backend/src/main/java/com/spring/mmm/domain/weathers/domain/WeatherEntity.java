package com.spring.mmm.domain.weathers.domain;

import com.spring.mmm.domain.recommends.controller.response.FoodInformation;
import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.domain.FoodMBTIEntity;
import com.spring.mmm.domain.recommends.domain.FoodWeatherEntity;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "weather")
@Entity
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private Integer weatherId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "weatherEntity", cascade = CascadeType.REMOVE)
    private List<FoodWeatherEntity> foodWeatherEntities;

}
