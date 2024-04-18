package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractBiFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.AbstractFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukboExitedEvent  extends AbstractFunctionMuklogEvent {

    public MukboExitedEvent(String source, Long mukgroupId) {
        super(source, MuklogEventType.MUKBO_EXITED, mukgroupId);
    }
}