package com.spring.mmm.domain.users.controller.response;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(force = true)
public class UserInfoResponse {

    private final Long id;
    private final String email;
    private final String nickname;

    public static UserInfoResponse of(Long id, String email, String nickname) {

        return new UserInfoResponse(id, email, nickname);
    }

}
