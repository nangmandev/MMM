package com.spring.mmm.domain.recommends.controller.request;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodRecommendCreateRequest {

    private Long mukgroupId;
    private Instant date;
    private List<RecommendedFoodRequest> foods;
    private Boolean hasValue;
}
