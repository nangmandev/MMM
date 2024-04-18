package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface EatenMukboJpaRepository extends JpaRepository<EatenMukboEntity, Long> {
    @Modifying
    @Query("DELETE FROM EatenMukboEntity em" +
    " WHERE em.foodRecommendEntity.recommendDate = :date" +
    " AND em.mukboEntity.mukgroupEntity.mukgroupId = :groupId")
    void deleteAllByDateAndGroupId(LocalDate date, Long groupId);

    @Query("SELECT CASE WHEN COUNT(em) > 0 THEN TRUE ELSE FALSE END" +
    " FROM EatenMukboEntity em" +
    " JOIN mukboEntity m" +
    " WHERE em.foodRecommendEntity.recommendDate = :date" +
    " AND m.mukgroupEntity.mukgroupId = :groupId")
    Boolean existsByDateAndGroupId(LocalDate date, Long groupId);
}
