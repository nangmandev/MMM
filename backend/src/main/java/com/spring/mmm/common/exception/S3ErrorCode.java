package com.spring.mmm.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum S3ErrorCode implements ErrorCode{

    NOT_IMAGE_FORMAT(HttpStatus.BAD_REQUEST, "파일이 이미지 형식이 아닙니다."),
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "파일이 올바르지 않습니다.");


    private final HttpStatus httpStatus;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}
