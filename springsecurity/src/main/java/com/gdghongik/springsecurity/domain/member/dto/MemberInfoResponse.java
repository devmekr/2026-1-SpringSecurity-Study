package com.gdghongik.springsecurity.domain.member.dto;

import com.gdghongik.springsecurity.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberInfoResponse {

    private final Long memberId;

    private final String username;

    public MemberInfoResponse(Member member) {
        this.memberId = member.getId();
        this.username = member.getUsername();
    }
}
