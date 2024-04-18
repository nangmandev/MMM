package com.spring.mmm.domain.mukus.service;

import com.spring.mmm.domain.mukus.controller.response.*;
import com.spring.mmm.domain.recommends.domain.FoodCategory;
import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.exception.RecommendErrorCode;
import com.spring.mmm.domain.recommends.exception.RecommendException;
import com.spring.mmm.domain.recommends.service.port.FoodCategoryRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MukusServiceImpl implements MukusService {

    private final RecommendedFoodRepository recommendedFoodRepository;
    private final FoodRecommendRepository foodRecommendRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public void selectRecentMukus(Long recommendFoodId) {

        RecommendedFoodEntity recommendedFoodEntity =
                recommendedFoodRepository.findByRecommendedFoodId(recommendFoodId)
                        .orElseThrow(() -> new RecommendException(RecommendErrorCode.RECOMMEND_NOT_FOUND));

        recommendedFoodEntity.eat();
    }

    @Override
    @Transactional(readOnly = true)
    public MukusCalendarResponse getMukusMonth(Long groupId, Integer year, Integer month) {
        List<RecommendedFoodEntity> recommendedFoodEntities =
                recommendedFoodRepository.findRecommendedFoodByYearAndMonth(groupId, year, month);

        List<MukusDayResponse> mukusMonthResponse =
                recommendedFoodEntities.stream().map(MukusDayResponse::create).collect(Collectors.toList());

        return MukusCalendarResponse.create(mukusMonthResponse);
    }

    @Override
    @Transactional
    public MukusRecentResponse getRecentMukus(Long groupId) {

        FoodRecommendEntity foodRecommendEntity = foodRecommendRepository.findRecentRecommendByMukgroupId(LocalDate.now(), groupId)
                .orElseThrow(() -> new RecommendException(RecommendErrorCode.FOOD_RECOMMEND_NOT_FOUND));

        List<RecommendedFoodEntity> recommendedFoodEntities = foodRecommendEntity.getRecommendedFoodEntities();

        List<RecommendFood> recommendFoods = recommendedFoodEntities.stream().map(food -> {
            FoodCategoryResponse foodCategoryResponse = FoodCategoryResponse.create(
                    food.getFoodEntity().getFoodCategoryEntity().getName().getKoreanName(),
                    food.getFoodEntity().getFoodCategoryEntity().getColor()
            );
            return RecommendFood.create(foodCategoryResponse, food);
        }).collect(Collectors.toList());

        LocalDate date = foodRecommendEntity.getRecommendDate();

        MukusRecentResponse mukusRecentResponse = MukusRecentResponse.create(RecommendData.create(date, recommendFoods), foodRecommendEntity.getHasValue());

        foodRecommendEntity.check();

        return mukusRecentResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public MukusDetailResponse getMukusDetail(Long groupId, Integer year, Integer month, Integer day) {
        RecommendedFoodEntity recommendedFoodEntity =
                recommendedFoodRepository.findRecommendedFoodByDate(groupId, year, month, day)
                        .orElseThrow(() -> new RecommendException(RecommendErrorCode.RECOMMENDED_NOT_FOUND));

        return MukusDetailResponse.create(recommendedFoodEntity);
    }

}
