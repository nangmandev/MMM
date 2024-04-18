package com.spring.mmm.domain.muklogs.controller;


import com.spring.mmm.domain.muklogs.event.MuklogEvent;
import com.spring.mmm.domain.muklogs.facade.MuklogHandleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MuklogEventHandler {

    private final MuklogHandleFacade muklogHandleFacade;
    @Async
    @EventListener(classes = MuklogEvent.class)
    public void handleMuklogEvent(MuklogEvent muklogEvent){
        muklogHandleFacade.handleMuklogEvent(muklogEvent);
    }
}
