package com.spring.mmm.domain.mbtis.exception;

import com.spring.mmm.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MukBTIErrorCode implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 MukBTI를 발견하지 못했습니다."),
    NOT_FOUND_QUESTION(HttpStatus.NOT_FOUND, "해당 질문을 발견하지 못했습니다."),
    NOT_FOUND_ANSWER(HttpStatus.NOT_FOUND, "해당 답변을 발견하지 못했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "입력 값이 올바른 형식을 따르지 않았습니다."),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "먹비티아이 기록이 없습니다. 한번 해보세요.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public String getErrorName() {
        return this.name();
    }
}
