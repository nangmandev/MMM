package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.RecommendCategory;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.exception.RecommendErrorCode;
import com.spring.mmm.domain.recommends.exception.RecommendException;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class RecommendedFoodRepositoryImpl implements RecommendedFoodRepository {

    private final RecommendedFoodJpaRepository recommendedFoodJpaRepository;

    @Override
    public Optional<RecommendedFoodEntity> findByRecommendedFoodId(Long recommendedFoodId) {
        return recommendedFoodJpaRepository.findByRecommendedFoodId(recommendedFoodId);
    }

    @Override
    public List<RecommendedFoodEntity> findRecommendedFoodByYearAndMonth(Long mukgroupId, Integer year, Integer month) {
        return recommendedFoodJpaRepository.findRecommendedFoodByYearAndMonth(mukgroupId, year, month);
    }

    @Override
    public Optional<RecommendedFoodEntity> findRecommendedFoodByDate(Long mukgroupId, Integer year, Integer month, Integer day) {
        return recommendedFoodJpaRepository.findRecommendedFoodByDate(mukgroupId, year, month, day);
    }

    @Override
    public List<Integer> findAllFoodIdByMukgroupId(Long mukgroupId) {
        return recommendedFoodJpaRepository.findAllFoodIdByMukgroupId(mukgroupId);
    }

    @Override
    public Boolean existsByDateAndGroupId(LocalDate date, Long groupId) {
        return recommendedFoodJpaRepository.existsByDateAndGroupId(date, groupId);
    }

    @Override
    public Optional<RecommendedFoodEntity> findByDateAndGroupIdAndCategory(LocalDate date, Long groupId, RecommendCategory category) {
        return recommendedFoodJpaRepository.findByDateAndGroupIdAndCategory(date, groupId, category);
    }


    @Override
    public void deleteAllNormalByDateAndGroupId(LocalDate date, Long groupId, RecommendCategory category) {
        recommendedFoodJpaRepository.deleteAllNormalByDateAndGroupId(date, groupId, category);
    }

    @Override
    public void save(RecommendedFoodEntity recommendedFoodEntity) {
        recommendedFoodJpaRepository.save(recommendedFoodEntity);
    }

}
