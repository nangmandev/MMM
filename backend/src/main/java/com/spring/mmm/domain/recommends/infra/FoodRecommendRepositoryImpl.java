package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import com.spring.mmm.domain.recommends.exception.RecommendErrorCode;
import com.spring.mmm.domain.recommends.exception.RecommendException;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class FoodRecommendRepositoryImpl implements FoodRecommendRepository {

    private final FoodRecommendJpaRepository foodRecommendJpaRepository;

    @Override
    public Optional<FoodRecommendEntity> findRecentRecommendByMukgroupId(LocalDate date, Long mukgroupId) {
        return foodRecommendJpaRepository.findRecentRecommendByMukgroupId(date, mukgroupId);
    }

    @Override
    public void saveFoodRecommend(FoodRecommendEntity foodRecommendEntity) {
        foodRecommendJpaRepository.save(foodRecommendEntity);
    }

    @Override
    public Optional<FoodRecommendEntity> findByDateAndGroupId(LocalDate date, Long mukgroupId) {
        return foodRecommendJpaRepository.findByRecommendDateAndMukgroupEntity_MukgroupId(date, mukgroupId);
    }


}
