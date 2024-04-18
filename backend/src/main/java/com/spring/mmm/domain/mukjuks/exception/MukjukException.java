package com.spring.mmm.domain.mukjuks.exception;

import com.spring.mmm.common.exception.CustomException;
import com.spring.mmm.common.exception.ErrorCode;

public class MukjukException extends CustomException {
    public MukjukException(ErrorCode errorCode) {
        super(errorCode);
    }
}
