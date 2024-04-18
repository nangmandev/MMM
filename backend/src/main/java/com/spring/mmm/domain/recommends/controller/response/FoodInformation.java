package com.spring.mmm.domain.recommends.controller.response;

import com.spring.mmm.domain.recommends.domain.FoodEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodInformation {
    private Integer foodId;
    private String name;
    private String imageSrc;

    public static FoodInformation createByFoodEntity(FoodEntity foodEntity){
        return FoodInformation.builder()
                .foodId(foodEntity.getFoodId())
                .name(foodEntity.getName())
                .imageSrc(foodEntity.getImage())
                .build();
    }
}
