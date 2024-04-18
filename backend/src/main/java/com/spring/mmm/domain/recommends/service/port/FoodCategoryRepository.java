package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;

import java.util.Optional;

public interface FoodCategoryRepository {
    Optional<FoodCategoryEntity> findByFoodId(Integer foodId);
}
