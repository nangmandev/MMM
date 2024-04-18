package com.spring.mmm.domain.users.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserModifyRequest {
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "새 비밀번호를 입력해주세요.")
    private String newPassword;
    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String newPasswordConfirm;
}
