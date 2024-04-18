package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodCategoryResponse {
    private String name;
    private String color;

    public static FoodCategoryResponse create(String name, String color) {

        return FoodCategoryResponse.builder()
                .name(name)
                .color(color)
                .build();
    }
}
