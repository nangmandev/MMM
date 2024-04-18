package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukgroupImageChangedEvent extends AbstractFunctionMuklogEvent {
    public MukgroupImageChangedEvent(String source, Long mukgroupId) {
        super(source, MuklogEventType.GROUP_IMAGE_CHANGED, mukgroupId);
    }
}
