package com.spring.mmm.domain.users.event;

import com.spring.mmm.common.event.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDeletedEvent implements Event {
    private final Long userId;

    public static UserDeletedEvent create(Long userId) { return new UserDeletedEvent(userId); }
}
