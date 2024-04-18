package com.spring.mmm.domain.recommends.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FoodCategory {
    KOREA("한식"), JAPAN("일식"), CHINA("중식"), WESTERN("양식")

    ;

    private final String koreanName;
}
