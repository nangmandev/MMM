package com.spring.mmm.domain.muklogs.service;

import com.spring.mmm.domain.muklogs.event.AbstractBiFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.AbstractFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEvent;
import com.spring.mmm.domain.muklogs.exception.InvalidMuklogAccessException;
import org.springframework.stereotype.Component;

@Component
public class MuklogEventParserImpl implements MuklogEventParser {
    @Override
    public String parseEvent(MuklogEvent muklogEvent) {
        return switch (muklogEvent) {
            case AbstractFunctionMuklogEvent functionEvent -> parseFunctionMuklogEvent(functionEvent);
            case AbstractBiFunctionMuklogEvent biFunctionEvent -> parseBiFunctionMuklogEvent(biFunctionEvent);
            default -> throw new InvalidMuklogAccessException();
        };
    }

    private String parseFunctionMuklogEvent(AbstractFunctionMuklogEvent event) {
        StringBuilder sb = new StringBuilder();
        switch (event.getMuklogEventType()) {
            case MUKBO_EXITED -> sb.append(event.getSource())
                    .append("님이 그룹을 나갔습니다.");
            case MUKJUK_ACHIEVE -> sb
                    .append("먹적 [")
                    .append(event.getSource())
                    .append("] 을 획득했습니다.");
            case REP_MUKJUK_CHANGED -> sb.append("대표 먹적이 [")
                    .append(event.getSource())
                    .append("] 로 변경되었습니다.");
            case GROUP_IMAGE_CHANGED ->  sb
                    .append(event.getSource())
                    .append("님이 먹그룹 이미지를\n 변경했습니다.");
            default -> throw new InvalidMuklogAccessException();
        }
        return sb.toString();
    }

    private String parseBiFunctionMuklogEvent(AbstractBiFunctionMuklogEvent event) {
        StringBuilder sb = new StringBuilder();
        switch (event.getMuklogEventType()) {
            case MUKBO_INVITED -> sb
                    .append(event.getSource())
                    .append("님이 ")
                    .append(event.getTarget())
                    .append("님을\n 초대했습니다.");
            case GROUP_NAME_CHANGED -> sb.
                    append(event.getSource())
                    .append("님이 ")
                    .append("먹그룹의 이름을\n[")
                    .append(event.getTarget())
                    .append("]로 변경했습니다.");
            case MUKBOT_CREATED -> sb
                    .append(event.getSource())
                    .append("님이 먹봇 [")
                    .append(event.getTarget())
                    .append("] 을\n 생성했습니다.");
            case MUKBOT_DELETED -> sb
                    .append(event.getSource())
                    .append("님이 먹봇 [")
                    .append(event.getTarget())
                    .append("] 을\n 삭제했습니다.");
            case MUKBOT_MODIFIED -> sb
                    .append(event.getSource())
                    .append("님이 먹봇 [")
                    .append(event.getTarget())
                    .append("] 의 정보를\n 수정했습니다.");
            case MUKBO_KICKED -> sb
                    .append(event.getSource())
                    .append("님이 먹보 [")
                    .append(event.getTarget())
                    .append("] 님을\n 추방했습니다.");
            default -> throw new InvalidMuklogAccessException();
        }
        return sb.toString();
    }
}
