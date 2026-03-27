package com.gdghongik.springsecurity.global.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String errorCodeName;

    private final String errorMessage;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCodeName = errorCode.name();
        this.errorMessage = errorCode.getErrorMessage();
    }
}
