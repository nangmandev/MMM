package com.spring.mmm.domain.recommends.exception;


import com.spring.mmm.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum RecommendErrorCode implements ErrorCode {
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 음식을 찾지 못했습니다."),
    RECOMMEND_NOT_FOUND(HttpStatus.NOT_FOUND, "처리되지 않은 최근 추천 메뉴가 없습니다."),
    FOOD_RECOMMEND_NOT_FOUND(HttpStatus.NOT_FOUND, "추천받은 음식 데이터가 없습니다."),
    RECOMMENDED_NOT_FOUND(HttpStatus.NOT_FOUND, "추천받은 음식 데이터에 해당 음식이 존재하지 않습니다."),
    LOCATION_NOT_MATCHED(HttpStatus.BAD_REQUEST, "사용자의 위치가 적절하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public String getErrorName() {
        return this.name();
    }
}