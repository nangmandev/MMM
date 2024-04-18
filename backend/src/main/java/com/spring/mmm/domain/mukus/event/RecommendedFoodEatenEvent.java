package com.spring.mmm.domain.mukus.event;

import com.spring.mmm.domain.mukjuks.event.FoodRecordedEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class RecommendedFoodEatenEvent implements FoodRecordedEvent {
    private final Long foodRecommendedId;
}
