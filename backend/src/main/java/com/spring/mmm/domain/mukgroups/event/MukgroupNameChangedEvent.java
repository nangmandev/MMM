package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractBiFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.AbstractFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukgroupNameChangedEvent extends AbstractBiFunctionMuklogEvent {
    public MukgroupNameChangedEvent(String source, String target,  Long mukgroupId) {
        super(source, target, MuklogEventType.GROUP_NAME_CHANGED, mukgroupId);
    }
}