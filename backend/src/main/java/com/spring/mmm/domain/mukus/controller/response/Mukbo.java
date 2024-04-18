package com.spring.mmm.domain.mukus.controller.response;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Mukbo {
    private String nickname;

    public static Mukbo create(String name) {
        return Mukbo.builder()
                .nickname(name)
                .build();
    }
}
