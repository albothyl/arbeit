package com.job.member.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job.common.util.encryption.Algorithm;
import com.job.common.util.encryption.SHA;
import com.job.member.domain.MemberEntity;
import com.job.member.domain.MemberRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
public class DefaultAuthenticationService implements AuthenticationService {

	@Autowired
	private SHA sha;

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
		if(newPassword != null && newPassword.length() > 0) {
//			ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
//			shaPasswordEncoder.encodePassword(newPassword);

			String encrypedPassword = sha.encryption(newPassword, Algorithm.SHA256.stringValue());
			memberEntity.setPassword(encrypedPassword);
		}
		this.memberRepository.save(memberEntity);
	}
}
