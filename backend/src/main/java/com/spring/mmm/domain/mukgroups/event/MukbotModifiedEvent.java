package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractBiFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukbotModifiedEvent extends AbstractBiFunctionMuklogEvent {
    public MukbotModifiedEvent(String source, String target, Long mukgroupId) {
        super(source, target, MuklogEventType.MUKBOT_MODIFIED, mukgroupId);
    }
}
