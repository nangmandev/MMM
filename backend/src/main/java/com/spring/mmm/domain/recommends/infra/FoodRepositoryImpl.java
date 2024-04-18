package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.controller.response.WeatherDTO;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepository {

    private final FoodJpaRepository foodJpaRepository;

    @Override
    public Optional<FoodEntity> findByName(String name) {
        return foodJpaRepository.findByName(name);
    }

    @Override
    public List<FoodEntity> findAll() {
        return foodJpaRepository.findAll();
    }

    @Override
    public List<FoodEntity> findByWeatherId(Integer weatherId) {
        return foodJpaRepository.findByWeatherId(weatherId);
    }
}
