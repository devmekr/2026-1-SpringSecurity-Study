package com.gdghongik.springsecurity.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    REGULAR("ROLE_REGULAR"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}
