package com.spring.mmm.domain.users.controller.request;

import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJoinRequest {

    private String email;
    private String nickname;
    private String password;

}
