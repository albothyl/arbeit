package com.job.member.security.provider;

import java.util.Arrays;
import java.util.Collection;

import com.job.member.domain.MemberRepository;
import com.job.member.security.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.job.member.security.userdetails.UserDetailsVO;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	MemberRepository memberRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UserDetailsVO userDetailsVO = new UserDetailsVO();

		String userid = authentication.getName();
		String password = authentication.getCredentials().toString();
		boolean enabled = true;
		String rolePrefix = "ROLE_";
		String roleId = "";

		userDetailsVO.setUserId(userid);

		// DB
		memberRepository.findByEmail(userid);

		if (userDetailsVO == null) {
			throw new BadCredentialsException("권한이 없습니다.");
		}

		roleId = rolePrefix + userDetailsVO.getRoleId();

		// 다중권한
		//List<UserDetailsVO> userAuthorities = authSVC.getAuthorities(userDetailsVO);

		Collection<? extends GrantedAuthority> authorities =
			Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority(roleId) });

		User user = new CustomUserDetails(userid, password, enabled, true, true, true, authorities, userDetailsVO);

		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}
