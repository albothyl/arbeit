package com.job.common.springSecurity.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;

import com.job.common.springSecurity.domain.UserDetail;

@Validated
public interface AuthenticationService extends UserDetailsService {
	@Override
	UserDetail loadUserByUsername(String username);

	void saveUser(
		@NotNull(message = "{validate.authenticate.saveUser}") @Valid UserDetail principal, String newPassword);
}
