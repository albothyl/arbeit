package com.job.member.access.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.member.domain.MemberEntity;

public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
	UserDetail getByEmail(String email);
}
