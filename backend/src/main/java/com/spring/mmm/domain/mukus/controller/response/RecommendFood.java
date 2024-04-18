package com.spring.mmm.domain.mukus.controller.response;

import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.domain.RecommendCategory;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendFood {
    private Long recommendedFoodId;
    private String name;
    private String img;
    private RecommendCategory recCategory;
    private FoodCategoryResponse foodCategoryResponse;

    public static RecommendFood create(FoodCategoryResponse foodCategoryResponse, RecommendedFoodEntity food) {
        return RecommendFood.builder()
                .recommendedFoodId(food.getRecommendedFoodId())
                .name(food.getFoodEntity().getName())
                .img(food.getFoodEntity().getImage())
                .recCategory(food.getCategory())
                .foodCategoryResponse(foodCategoryResponse)
                .build();

    }
}
