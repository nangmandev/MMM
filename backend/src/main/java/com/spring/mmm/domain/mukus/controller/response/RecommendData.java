package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendData {
    private LocalDate date;
    private List<RecommendFood> foods;

    public static RecommendData create(LocalDate date, List<RecommendFood> foods) {
        return RecommendData.builder()
                .date(date)
                .foods(foods)
                .build();
    }
}
