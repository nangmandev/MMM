package com.spring.mmm.domain.mukus.controller.response;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.recommends.controller.request.FoodRecommendCreateRequest;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukusRecentResponse {
    private Boolean hasValue;
    private RecommendData data;

    public static MukusRecentResponse create(RecommendData data, Boolean hasValue) {
        return MukusRecentResponse.builder()
                .hasValue(hasValue)
                .data(data)
                .build();
    }
}
