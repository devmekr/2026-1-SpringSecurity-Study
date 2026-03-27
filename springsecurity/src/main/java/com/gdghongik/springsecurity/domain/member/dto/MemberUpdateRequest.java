package com.gdghongik.springsecurity.domain.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberUpdateRequest {

    private final String username;
}
