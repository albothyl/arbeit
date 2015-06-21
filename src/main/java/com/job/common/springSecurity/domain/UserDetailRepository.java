package com.job.common.springSecurity.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
	UserDetail getByEmail(String email);
}
