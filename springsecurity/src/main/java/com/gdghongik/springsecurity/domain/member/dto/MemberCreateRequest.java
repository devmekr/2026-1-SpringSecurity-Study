package com.gdghongik.springsecurity.domain.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateRequest {

    private final String username;

    private final String password;
}
