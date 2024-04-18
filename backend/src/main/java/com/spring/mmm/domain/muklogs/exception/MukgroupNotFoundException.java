package com.spring.mmm.domain.muklogs.exception;

import com.spring.mmm.common.exception.CustomException;
import com.spring.mmm.common.exception.ErrorCode;

public class MukgroupNotFoundException extends CustomException {
    public MukgroupNotFoundException() {
        super(MuklogErrorCode.MUKGROUP_NOT_FOUND);
    }
}
