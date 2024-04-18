package com.spring.mmm.domain.users.controller.response;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class TokenResponse {

    private final String accessToken;
    private final String refreshToken;

    public static TokenResponse create(String accessToken, String refreshToken) {
        return new TokenResponse(accessToken, refreshToken);
    }
}