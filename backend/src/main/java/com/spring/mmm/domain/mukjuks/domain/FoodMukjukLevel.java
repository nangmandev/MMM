package com.spring.mmm.domain.mukjuks.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum FoodMukjukLevel {

    FIRST("입문자", 3 ), SECOND("초심자", 10), THIRD("애호가", 30), LAST("중독자", 100)
    ;
    private final String title;
    private final int condition;
}
