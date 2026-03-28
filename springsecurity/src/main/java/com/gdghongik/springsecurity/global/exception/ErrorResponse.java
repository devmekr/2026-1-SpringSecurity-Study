package com.gdghongik.springsecurity.global.exception;

public record ErrorResponse(
        String errorCodeName,
        String errorMessage
) {
    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.name(), errorCode.getErrorMessage());
    }
}