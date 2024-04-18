package com.spring.mmm.domain.users.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

	private final String accessToken;
	private final String refreshToken;
	private final Boolean isRecorded;


}
