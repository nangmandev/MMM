package com.spring.mmm.domain.mukgroups.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.recommends.domain.FoodCategory;

import java.util.Optional;

public interface MukgroupRepository {
    void save(MukgroupEntity mukgroupEntity);
    Optional<MukgroupEntity> findByMukgroupId(Long mukgroupId);
    void delete(MukgroupEntity mukgroupEntity);
    Integer countAllMukboByMukgroupId(Long mukgroupId);

    Integer countMukusByFoodCategory(Long mukgroupId, FoodCategory foodCategory);
}
