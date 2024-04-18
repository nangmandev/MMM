package com.spring.mmm.domain.mukus.controller.response;

import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukusDetailResponse {
    private Food food;
    private List<Mukbo> mukbos;

    public static MukusDetailResponse create(RecommendedFoodEntity recommend) {
        return MukusDetailResponse.builder()
                .food(Food.create(recommend))
                .mukbos(recommend.getFoodRecommendEntity().getEatenMukboEntities().stream().map(
                        eatenMukbo -> {
                            String mukboName = eatenMukbo.getMukboEntity().getName();
                            return Mukbo.create(mukboName);
                        }
                ).collect(Collectors.toList()))
                .build();
    }
}
