package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukgroupMukjukSelectedEvent  extends AbstractFunctionMuklogEvent {
	public MukgroupMukjukSelectedEvent(String source, Long mukgroupId) {
		super(source, MuklogEventType.REP_MUKJUK_CHANGED, mukgroupId);
	}
}
