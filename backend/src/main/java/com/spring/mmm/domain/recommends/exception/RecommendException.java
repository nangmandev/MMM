package com.spring.mmm.domain.recommends.exception;

import com.spring.mmm.common.exception.CustomException;
import com.spring.mmm.common.exception.ErrorCode;


public class RecommendException extends CustomException {
    public RecommendException(ErrorCode errorCode) {
        super(errorCode);
    }
}

