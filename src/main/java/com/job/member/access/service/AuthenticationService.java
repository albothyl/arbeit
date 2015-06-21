package com.job.member.access.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;

import com.job.member.access.domain.UserDetail;
import com.job.member.domain.MemberEntity;

@Validated
public interface AuthenticationService extends UserDetailsService {
	@Override
	UserDetail loadUserByUsername(String username);

	void saveUser(
		@NotNull(message = "{validate.authenticate.saveUser}") @Valid UserDetail principal, String newPassword);
}