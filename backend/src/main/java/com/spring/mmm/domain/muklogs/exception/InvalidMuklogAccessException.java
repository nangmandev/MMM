package com.spring.mmm.domain.muklogs.exception;

import com.spring.mmm.common.exception.CustomException;
import com.spring.mmm.common.exception.ErrorCode;

public class InvalidMuklogAccessException extends CustomException {
    public InvalidMuklogAccessException() {
        super(MuklogErrorCode.INVALID_MUKLOG_ACCESSED);
    }
}
