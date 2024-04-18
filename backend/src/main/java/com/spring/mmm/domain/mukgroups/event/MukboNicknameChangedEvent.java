package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukboNicknameChangedEvent extends AbstractFunctionMuklogEvent {
    public MukboNicknameChangedEvent(String source, Long mukgroupId) {
        super(source, MuklogEventType.MUKBO_NICKNAME_CHANGED, mukgroupId);
    }
}
