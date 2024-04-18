package com.spring.mmm.domain.mukus.controller.response;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {
    private String name;
    private String img;

    public static Food create(RecommendedFoodEntity recommend) {
        return Food.builder()
                .name(recommend.getFoodEntity().getName())
                .img(recommend.getFoodEntity().getImage())
                .build();
    }
}
