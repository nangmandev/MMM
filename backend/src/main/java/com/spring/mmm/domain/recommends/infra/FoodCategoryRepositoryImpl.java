package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.service.port.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FoodCategoryRepositoryImpl implements FoodCategoryRepository {

    private final FoodCategoryJpaRepository foodCategoryJpaRepository;

    @Override
    public Optional<FoodCategoryEntity> findByFoodId(Integer foodId) {
        return foodCategoryJpaRepository.findByFoodId(foodId);
    }

}
