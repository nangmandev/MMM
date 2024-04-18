package com.spring.mmm.domain.mukgroups.exception;


import com.spring.mmm.common.exception.CustomException;
import com.spring.mmm.common.exception.ErrorCode;


public class MukGroupException extends CustomException {
    public MukGroupException(ErrorCode errorCode) {
        super(errorCode);
    }
}
