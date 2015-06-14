package com.job.member.security;

import java.util.Arrays;
import java.util.Collection;

import com.job.member.domain.MemberEntity;
import com.job.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	MemberRepository memberRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userEmail = authentication.getName();
		String password = authentication.getCredentials().toString();
		boolean enabled = true;
		String rolePrefix = "ROLE_";
		String roleId = "";

		// DB
		MemberEntity memberEntity = memberRepository.findByEmail(userEmail);

		if (memberEntity == null) {
			throw new BadCredentialsException("권한이 없습니다.");
		}

		roleId = rolePrefix + memberEntity.getRoleId();

		// 다중권한
		//List<UserLoginVO> userAuthorities = authSVC.getAuthorities(userDetailsVO);

		Collection<? extends GrantedAuthority> authorities =
			Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority(roleId) });

		User user = new SecurityUser(userEmail, password, enabled, true, true, true, authorities, memberEntity);

		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}
