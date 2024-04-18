package com.spring.mmm.domain.muklogs.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class AbstractBiFunctionMuklogEvent  implements MuklogEvent{

    private final String source;
    private final String target;
    private final MuklogEventType muklogEventType;
    private final Long mukgroupId;
}
