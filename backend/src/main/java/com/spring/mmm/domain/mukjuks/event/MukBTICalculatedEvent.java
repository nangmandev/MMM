package com.spring.mmm.domain.mukjuks.event;

public interface MukBTICalculatedEvent extends MukjukEvent{
    Long getGroupId();
    Integer getEi();
    Integer getNs();
    Integer getTf();
    Integer getJp();
    Integer getPine();
    Integer getDie();
    Integer getMint();

}
