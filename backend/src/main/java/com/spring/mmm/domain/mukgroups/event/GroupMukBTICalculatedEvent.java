package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukjuks.event.MukBTICalculatedEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.min;


@Getter
public class GroupMukBTICalculatedEvent implements MukBTICalculatedEvent {

    private final Long groupId;
    private final Integer ei;
    private final Integer ns;
    private final Integer tf;
    private final Integer jp;
    private final Integer pine;
    private final Integer die;
    private final Integer mint;

    public GroupMukBTICalculatedEvent(Long groupId, MBTI mbti) {
        this.groupId = groupId;
        ei = mbti.getEi();
        ns = mbti.getNs();
        tf = mbti.getTf();
        jp = mbti.getJp();
        pine = mbti.getPine();
        die = mbti.getDie();
        mint = mbti.getMint();
    }
}
