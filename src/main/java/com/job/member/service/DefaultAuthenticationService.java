package com.job.member.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.job.member.domain.MemberEntity;
import com.job.member.domain.MemberRepository;

@Slf4j
@Service
public class DefaultAuthenticationService implements AuthenticationService {

	@Autowired
	SaltSource saltSource;

	@Autowired
	MemberRepository memberRepository;

	@Override
	public MemberEntity loadUserByUsername(String email) {
		MemberEntity memberEntity = memberRepository.getByEmail(email);
		memberEntity.getAuthorities().size();
		memberEntity.getPassword();
		return memberEntity;
	}

	@Override
	public void saveUser(@NotNull(message = "{validate.authenticate.saveUser}") @Valid MemberEntity memberEntity, String newPassword) {
		if (newPassword != null && newPassword.length() > 0) {
			ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
			shaPasswordEncoder.encodePassword(newPassword, saltSource);
		}
		this.memberRepository.save(memberEntity);
	}
}
