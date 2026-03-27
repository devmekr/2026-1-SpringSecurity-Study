package com.gdghongik.springsecurity.domain.member.repository;

import com.gdghongik.springsecurity.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(String username);

    Member findByUsername(String username);
}