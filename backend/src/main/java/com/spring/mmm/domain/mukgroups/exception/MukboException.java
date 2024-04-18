package com.spring.mmm.domain.mukgroups.exception;

import com.spring.mmm.common.exception.CustomException;
import com.spring.mmm.common.exception.ErrorCode;

public class MukboException extends CustomException {
    public MukboException(ErrorCode errorCode) {
        super(errorCode);
    }
}
