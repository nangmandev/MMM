package com.spring.mmm.domain.mukgroups.exception;

import com.spring.mmm.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MukboErrorCode implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 먹보를 발견하지 못했습니다."),
    ANOTHER_GROUP(HttpStatus.FORBIDDEN, "타 먹그룹에 있는 먹보를 수정할 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public String getErrorName() {
        return this.name();
    }
}
