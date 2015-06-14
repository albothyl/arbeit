package com.job.member.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
	/**
	 * User 클래스의 생성자 Override
	 * @param username 사용자계정
	 * @param password 사용자 패스워드
	 * @param enabled 사용자계정 사용여부
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * @param userVO 사용자 VO객체
	 * @throws IllegalArgumentException
	 */
	public SecurityUser(String username, String password, boolean enabled,
		boolean accountNonExpired, boolean credentialsNonExpired,
		boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
		Object userVO) throws IllegalArgumentException {

		super(username, password, enabled, accountNonExpired,
			credentialsNonExpired, accountNonLocked, authorities);

		this.userVO = userVO;
	}

	public SecurityUser(String username, String password, boolean enabled,
		Object userVO) throws IllegalArgumentException {

		this(username, password, enabled, true, true, true,
			Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority("HOLDER") }), userVO);
	}

	private Object userVO;

	public Object getUserVO() {
		return userVO;
	}

	public void setUserVO(Object userVO) {
		this.userVO = userVO;
	}
}