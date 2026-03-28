package com.gdghongik.springsecurity.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        HttpStatus httpStatus = e.getErrorCode().getHttpStatus();
        ErrorResponse errorResponse = ErrorResponse.from(e.getErrorCode());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
