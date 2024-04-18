package com.spring.mmm.domain.users.exception;

import com.spring.mmm.common.exception.CustomException;

public class UserException extends CustomException {

    public UserException(UserErrorCode errorCode) { super(errorCode); }
}
