package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.controller.response.WeatherDTO;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodJpaRepository extends JpaRepository<FoodEntity, Long> {

    Optional<FoodEntity> findByName(String name);

    List<FoodEntity> findAll();

    @Query(
            "SELECT f " +
                    "FROM FoodEntity f join fetch f.foodWeatherEntity fwe " +
                    "WHERE fwe.weatherEntity.weatherId = :weatherId")
    List<FoodEntity> findByWeatherId(Integer weatherId);

}
