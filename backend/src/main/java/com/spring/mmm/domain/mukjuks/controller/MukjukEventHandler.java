package com.spring.mmm.domain.mukjuks.controller;

import com.spring.mmm.domain.mukjuks.event.FoodRecordedEvent;
import com.spring.mmm.domain.mukjuks.event.MukBTICalculatedEvent;
import com.spring.mmm.domain.mukjuks.service.MukjukService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MukjukEventHandler {

    private final MukjukService mukjukService;

    @Async
    @EventListener
    public void handleFoodRecordedEvent(FoodRecordedEvent event){
        mukjukService.handleFoodRecordedEvent(event);
    }
    @Async
    @EventListener
    public void handleMukBTICalculatedEvent(MukBTICalculatedEvent event){
        mukjukService.handleMukBTICalculatedEvent(event);
    }
}
