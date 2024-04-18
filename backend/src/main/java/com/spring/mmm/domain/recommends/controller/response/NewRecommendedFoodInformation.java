package com.spring.mmm.domain.recommends.controller.response;

import com.spring.mmm.domain.recommends.domain.FoodEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NewRecommendedFoodInformation {
    private Integer id;
    private String name;
    private String image;
    private Integer categoryId;

    public static NewRecommendedFoodInformation create(FoodEntity food){
        return NewRecommendedFoodInformation.builder()
                .id(food.getFoodId())
                .name(food.getName())
                .image(food.getImage())
                .categoryId(food.getFoodCategoryEntity().getFoodCategoryId())
                .build();
    }
}
