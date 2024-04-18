package com.spring.mmm.domain.muklogs.facade;

import com.spring.mmm.domain.muklogs.event.MuklogEvent;
import com.spring.mmm.domain.muklogs.service.MuklogEventParser;
import com.spring.mmm.domain.muklogs.service.MuklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MuklogHandleFacadeImpl implements MuklogHandleFacade {

    private final MuklogEventParser muklogEventParser;
    private final MuklogService muklogService;
    @Override
    public void handleMuklogEvent(MuklogEvent muklogEvent) {
        muklogService.saveLog(muklogEvent.getMukgroupId(), muklogEventParser.parseEvent(muklogEvent));
    }
}
