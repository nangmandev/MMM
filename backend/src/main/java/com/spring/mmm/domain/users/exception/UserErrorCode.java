package com.spring.mmm.domain.users.exception;

import com.spring.mmm.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCode {

    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "이메일이 존재하지 않습니다."),
    NICKNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "닉네임이 존재하지 않습니다."),
    EXIST_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    EXIST_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다."),
    PASSWORD_CONFLICT(HttpStatus.CONFLICT, "기존 패스워드와 일치하지 않습니다."),
    PASSWORD_CONFIRM_CONFLICT(HttpStatus.CONFLICT, "비밀번호 확인이 실패했습니다."),
    NO_INPUT_FOR_MODIFY_USER_INFO(HttpStatus.BAD_REQUEST, "수정할 회원 정보가 없습니다."),
    INVALID_USER(HttpStatus.FORBIDDEN, "권한이 없는 사용자입니다."),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST, "이메일 또는 패스워드가 올바르지 않습니다. 정확한 값을 입력해주세요"),
    CREATE_RANDOM_CODE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "코드 생성에 실패했습니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다."),
    CODE_NOT_SAME_ERROR(HttpStatus.BAD_REQUEST, "인증 코드가 일치하지 않습니다."),
    IS_AUTHENTICATED(HttpStatus.FORBIDDEN, "이미 로그인한 사용자입니다."),
    TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;


    public String getErrorName() { return this.name(); }
}
