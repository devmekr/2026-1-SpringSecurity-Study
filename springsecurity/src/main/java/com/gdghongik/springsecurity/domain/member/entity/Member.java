package com.gdghongik.springsecurity.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}
