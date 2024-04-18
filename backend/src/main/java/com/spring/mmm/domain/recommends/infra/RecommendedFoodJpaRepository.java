package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.RecommendCategory;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecommendedFoodJpaRepository extends JpaRepository<RecommendedFoodEntity, Long> {

    Optional<RecommendedFoodEntity> findByRecommendedFoodId(Long recommendedFoodId);

    @Query("SELECT r FROM RecommendedFoodEntity r " +
            "JOIN FETCH r.foodRecommendEntity f " +
            "WHERE r.eaten = true " +
            "and f.mukgroupEntity.mukgroupId = :mukgroupId " +
            "and YEAR(f.recommendDate) = :year AND " +
            "MONTH(f.recommendDate) = :month")
    List<RecommendedFoodEntity> findRecommendedFoodByYearAndMonth(Long mukgroupId, Integer year, Integer month);

    @Query("SELECT r FROM RecommendedFoodEntity r " +
            "JOIN FETCH r.foodRecommendEntity f " +
            "WHERE r.eaten = true " +
            "AND f.mukgroupEntity.mukgroupId = :mukgroupId " +
            "AND YEAR(f.recommendDate) = :year " +
            "AND MONTH(f.recommendDate) = :month " +
            "AND DAY(f.recommendDate) = :day")
    Optional<RecommendedFoodEntity> findRecommendedFoodByDate(Long mukgroupId, Integer year, Integer month, Integer day);

    @Query("select fre.foodEntity.foodId from RecommendedFoodEntity fre where fre.eaten = true and fre.foodRecommendEntity.mukgroupEntity.mukgroupId=:mukgroupId")
    List<Integer> findAllFoodIdByMukgroupId(Long mukgroupId);

    @Query("SELECT CASE WHEN COUNT(rf) > 0 THEN TRUE ELSE FALSE END" +
            " FROM RecommendedFoodEntity rf" +
            " JOIN rf.foodRecommendEntity fr" +
            " WHERE fr.recommendDate = :date" +
            " AND fr.mukgroupEntity.mukgroupId = :groupId")
    Boolean existsByDateAndGroupId(LocalDate date, Long groupId);

    @Query("SELECT rf FROM RecommendedFoodEntity rf" +
            " JOIN rf.foodRecommendEntity fr" +
            " WHERE fr.recommendDate = :date" +
            " AND fr.mukgroupEntity.mukgroupId = :groupId" +
            " AND rf.category = :category")
    Optional<RecommendedFoodEntity> findByDateAndGroupIdAndCategory(LocalDate date, Long groupId, RecommendCategory category);

    @Modifying
    @Query("DELETE FROM RecommendedFoodEntity rf" +
            " WHERE rf.foodRecommendEntity.recommendDate = :date" +
            " AND rf.foodRecommendEntity.mukgroupEntity.mukgroupId = :groupId" +
            " AND rf.category = :category")
    void deleteAllNormalByDateAndGroupId(LocalDate date, Long groupId, RecommendCategory category);
}
