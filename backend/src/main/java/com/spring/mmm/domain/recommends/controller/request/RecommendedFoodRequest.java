package com.spring.mmm.domain.recommends.controller.request;

import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.domain.RecommendCategory;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendedFoodRequest {

    private Long foodRecommendId;
    private FoodEntity food;
    private Boolean eaten;
    private RecommendCategory recommendCategory;
}
