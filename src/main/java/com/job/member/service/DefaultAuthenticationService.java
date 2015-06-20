package com.job.member.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job.common.util.encryption.Algorithm;
import com.job.common.util.encryption.SHA;
import com.job.member.domain.MemberEntity;
import com.job.member.domain.MemberRepository;

@Slf4j
@Service
public class DefaultAuthenticationService implements AuthenticationService {

	@Autowired
	private SHA sha;

	@Autowired
	MemberRepository memberRepository;

	@Override
	@Transactional
	public MemberEntity authenticate(Authentication authentication) {
		UsernamePasswordAuthenticationToken credentials =
			(UsernamePasswordAuthenticationToken) authentication;
		String email = credentials.getPrincipal().toString();
		credentials.eraseCredentials();

		MemberEntity memberEntity = this.memberRepository.getByEmail(email);
		if (memberEntity == null) {
			log.warn("Authentication failed for non-existent user {}.", email);
			return null;
		}

		memberEntity.setAuthenticated(true);
		log.debug("User {} successfully authenticated.", email);

		return memberEntity;
	}

	@Override
	public boolean supports(Class<?> c) {
		return c == UsernamePasswordAuthenticationToken.class;
	}

	@Override
	@Transactional
	public void saveUser(MemberEntity memberEntity, String newPassword) {
		if (newPassword != null && newPassword.length() > 0) {
			String encryptionPassword = sha.encryption(newPassword, Algorithm.SHA256.stringValue());
			memberEntity.setPassword(encryptionPassword);
		}
		this.memberRepository.save(memberEntity);
	}
}
