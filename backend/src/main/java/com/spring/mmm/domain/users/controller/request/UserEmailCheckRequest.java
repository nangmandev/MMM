package com.spring.mmm.domain.users.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserEmailCheckRequest {
    @Email
    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;

    @NotBlank(message = "인증 번호를 입력해 주세요")
    private String authNum;

}