package com.spring.mmm.domain.recommends.controller.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommandRandomFood {
    private List<FoodInformation> foods;
}
