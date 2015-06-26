package com.job.common.springSecurity.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.job.common.springSecurity.domain.UserDetail;
import com.job.common.springSecurity.domain.UserDetailRepository;

@Slf4j
@Service
public class DefaultAuthenticationService implements AuthenticationService {

	@Autowired
	UserDetailRepository userDetailRepository;

	@Override
	public UserDetail loadUserByUsername(String email) {
		UserDetail userDetail = userDetailRepository.getByEmail(email);
		userDetail.getAuthorities().size();
		userDetail.getPassword();

		return userDetail;
	}

	@Override
	public void saveUser(@NotNull(message = "{validate.authenticate.saveUser}") @Valid UserDetail userDetail, String newPassword) {
		if (!StringUtils.isEmpty(newPassword)) {
			ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
			userDetail.setPassword(shaPasswordEncoder.encodePassword(newPassword, new ReflectionSaltSource()));
		}
		userDetailRepository.save(userDetail);
	}
}
