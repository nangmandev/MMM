package com.spring.mmm.domain.recommends.controller.request;

import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodRequest {

    private String name;
    private String imgPath;
    private FoodCategoryEntity foodCategory;
}
