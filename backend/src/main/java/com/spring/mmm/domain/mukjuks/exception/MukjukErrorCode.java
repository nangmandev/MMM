package com.spring.mmm.domain.mukjuks.exception;

import com.spring.mmm.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MukjukErrorCode implements ErrorCode {
    
    RECOMMENDED_FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "추천받은 음식이 존재하지 않습니다."),
    MUKJUK_NOT_MATCHED(HttpStatus.BAD_REQUEST, "매칭되는 먹적이 없습니다.")
    ;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public String getErrorName() {
        return this.name();
    }
}
