package com.spring.mmm.domain.mukus.controller.request;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendFoodChoiceRequest {
    private Long recommendFoodId;
}
