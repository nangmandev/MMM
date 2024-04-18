package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FoodCategoryJpaRepository extends JpaRepository<FoodCategoryEntity, Integer> {

    @Query("SELECT fc FROM FoodCategoryEntity fc INNER JOIN fc.foods f WHERE f.foodId = :foodId")
    Optional<FoodCategoryEntity> findByFoodId(Integer foodId);
}
