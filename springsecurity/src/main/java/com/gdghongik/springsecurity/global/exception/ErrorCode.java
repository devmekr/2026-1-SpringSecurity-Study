package com.gdghongik.springsecurity.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    MEMBER_USERNAME_DUPLICATE(CONFLICT, "이미 존재하는 유저네임입니다."),
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 멤버가 존재하지 않습니다.");

    private final HttpStatus httpStatus;

    private final String errorMessage;
}
