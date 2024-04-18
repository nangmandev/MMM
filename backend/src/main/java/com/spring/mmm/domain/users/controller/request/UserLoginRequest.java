package com.spring.mmm.domain.users.controller.request;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class UserLoginRequest {

    private final String email;
    private final String password;

}
