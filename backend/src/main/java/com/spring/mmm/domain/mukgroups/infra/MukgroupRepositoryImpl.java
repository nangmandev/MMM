package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.exception.MukboErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukboException;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.recommends.domain.FoodCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MukgroupRepositoryImpl implements MukgroupRepository {
    private final MukgroupJpaRepository mukgroupJpaRepository;
    
    @Override
    public void save(MukgroupEntity mukgroupEntity) {
        mukgroupJpaRepository.save(mukgroupEntity);
    }

    @Override
    public Optional<MukgroupEntity> findByMukgroupId(Long mukgroupId) {
        return mukgroupJpaRepository.findByMukgroupId(mukgroupId);
    }

    @Override
    public void delete(MukgroupEntity mukgroupEntity) {
        mukgroupJpaRepository.delete(mukgroupEntity);
    }

    @Override
    public Integer countAllMukboByMukgroupId(Long mukgroupId) {
        return mukgroupJpaRepository.countAllMukboByMukgroupId(mukgroupId);
    }

    @Override
    public Integer countMukusByFoodCategory(Long mukgroupId, FoodCategory foodCategory) {
        return mukgroupJpaRepository.countMukusByFoodCategory(mukgroupId, foodCategory);
    }
}
