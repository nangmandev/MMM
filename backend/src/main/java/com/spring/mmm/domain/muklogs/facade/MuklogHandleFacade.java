package com.spring.mmm.domain.muklogs.facade;

import com.spring.mmm.domain.muklogs.event.MuklogEvent;

public interface MuklogHandleFacade {

    void handleMuklogEvent(MuklogEvent muklogEvent);
}
