package com.spring.mmm.domain.muklogs.event;

import com.spring.mmm.common.event.Event;
import lombok.Getter;


public interface MuklogEvent extends Event {


    Long getMukgroupId();
    MuklogEventType getMuklogEventType();
}
