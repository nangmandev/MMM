package com.spring.mmm.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getHttpStatus();

    String getErrorName();

    String getErrorMessage();
}
