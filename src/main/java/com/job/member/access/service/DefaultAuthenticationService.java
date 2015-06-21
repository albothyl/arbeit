package com.job.member.access.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.job.member.access.domain.UserDetail;
import com.job.member.access.domain.UserDetailRepository;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

	@Autowired
	SaltSource saltSource;

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
			shaPasswordEncoder.encodePassword(newPassword, saltSource);
		}
		userDetailRepository.save(userDetail);
	}
}
