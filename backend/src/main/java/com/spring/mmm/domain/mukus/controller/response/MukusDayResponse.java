package com.spring.mmm.domain.mukus.controller.response;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukusDayResponse {

    private LocalDate date;
    private String colorCode;
    private String category;

    public static MukusDayResponse create(RecommendedFoodEntity recommend) {
        return MukusDayResponse.builder()
                .date(recommend.getFoodRecommendEntity().getRecommendDate())
                .colorCode(recommend.getFoodEntity().getFoodCategoryEntity().getColor())
                .category(recommend.getFoodEntity().getFoodCategoryEntity().getName().getKoreanName())
                .build();
    }
}
