package com.spring.mmm.domain.mukjuks.event;

import com.spring.mmm.domain.muklogs.event.AbstractFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukjukAchievedEvent extends AbstractFunctionMuklogEvent {
    public MukjukAchievedEvent(String source,  Long mukgroupId) {
        super(source, MuklogEventType.MUKJUK_ACHIEVE, mukgroupId);
    }
}
