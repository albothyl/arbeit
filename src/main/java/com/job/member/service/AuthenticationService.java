package com.job.member.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;

import com.job.member.domain.MemberEntity;

@Validated
public interface AuthenticationService extends AuthenticationProvider
{
	@Override
	MemberEntity authenticate(Authentication authentication);

	void saveUser(
		@NotNull(message = "{validate.authenticate.saveUser}") @Valid MemberEntity principal,
		String newPassword
		);
}
