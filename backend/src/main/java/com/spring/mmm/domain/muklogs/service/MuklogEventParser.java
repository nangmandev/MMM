package com.spring.mmm.domain.muklogs.service;

import com.spring.mmm.domain.muklogs.event.MuklogEvent;

public interface MuklogEventParser {

    String parseEvent(MuklogEvent muklogEvent);
}
