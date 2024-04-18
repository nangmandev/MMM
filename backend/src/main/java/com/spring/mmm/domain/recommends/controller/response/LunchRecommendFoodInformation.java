package com.spring.mmm.domain.recommends.controller.response;

import com.spring.mmm.domain.recommends.domain.FoodEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LunchRecommendFoodInformation {
    private Integer id;
    private String name;
    private String image;
    private Integer categoryId;

    public static LunchRecommendFoodInformation create(FoodEntity foodEntity){
        return LunchRecommendFoodInformation.builder()
                .id(foodEntity.getFoodId())
                .name(foodEntity.getName())
                .image(foodEntity.getImage())
                .categoryId(foodEntity.getFoodCategoryEntity().getFoodCategoryId())
                .build();
    }
}
