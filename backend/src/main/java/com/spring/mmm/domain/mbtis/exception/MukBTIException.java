package com.spring.mmm.domain.mbtis.exception;

import com.spring.mmm.common.exception.CustomException;
import com.spring.mmm.common.exception.ErrorCode;

public class MukBTIException extends CustomException {
    public MukBTIException(ErrorCode errorCode) {
        super(errorCode);
    }
}
