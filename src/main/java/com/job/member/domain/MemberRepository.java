package com.job.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
	MemberEntity findByEmail(String email);
}
