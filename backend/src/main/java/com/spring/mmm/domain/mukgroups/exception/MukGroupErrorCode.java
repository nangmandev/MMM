package com.spring.mmm.domain.mukgroups.exception;

import com.spring.mmm.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MukGroupErrorCode implements ErrorCode {
    IMAGE_INVALID(HttpStatus.BAD_REQUEST, "이미지 파일이 올바르지 않습니다."),
    NOT_UPLOADED(HttpStatus.BAD_REQUEST, "이미지 업로드에 실패했습니다."),
    DUPLICATE_ERROR(HttpStatus.CONFLICT, "이미 속한 먹그룹이 있습니다."),
    SOLO_CANT_EXIT(HttpStatus.BAD_REQUEST, "솔로 먹그룹은 나갈 수 없습니다."),
    SOLO_CANT_ACCESS_MUKJUK(HttpStatus.FORBIDDEN, "솔로는 먹적 기능을 이용할 수 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "이 먹그룹에 접근할 권한이 없습니다."),
    ANOTHER_MUKGROUP(HttpStatus.FORBIDDEN, "다른 먹그룹에 속한 먹보입니다."),
    SOLOGROUP_CANT_INVITE(HttpStatus.BAD_REQUEST, "솔로 먹그룹에 먹보를 추가할 수 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 먹그룹을 찾을 수 없습니다."),
    SELECTED_NOT_ACHIEVE_MUKJUK(HttpStatus.BAD_REQUEST, "획득하지 못한 먹적을 선택했습니다."),
    ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 현재 먹그룹에 속하는 먹보입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public String getErrorName() {
        return this.name();
    }
}
