package com.spring.mmm.domain.muklogs.exception;

import com.spring.mmm.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MuklogErrorCode implements ErrorCode {

    MUKGROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "먹그룹이 존재하지 않습니다."),
    INVALID_MUKLOG_ACCESSED(HttpStatus.BAD_REQUEST, "양식에 맞지 않는 접근입니다.")

    ;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}