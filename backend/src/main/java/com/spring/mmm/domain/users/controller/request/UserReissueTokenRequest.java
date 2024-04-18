package com.spring.mmm.domain.users.controller.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class UserReissueTokenRequest {
    private String refreshToken;

    public UserReissueTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
