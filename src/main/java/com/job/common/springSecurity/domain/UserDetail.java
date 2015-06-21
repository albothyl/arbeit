package com.job.common.springSecurity.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "arbeit", name = "user")
@SuppressWarnings({ "PMD.UnusedPrivateField" })
public class UserDetail implements UserDetails {

	@Id
	private String email;
	private String password;
	private String authority;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Long memberId;
	@LastModifiedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedAt;
	@CreatedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return parseAuthority();
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	private static final String DELIMITER = ",";

	private List<GrantedAuthority> parseAuthority() {
		List roles = Arrays.asList(authority.split(DELIMITER));
		return FluentIterable.from(roles).transform(new Function<String, GrantedAuthority>() {
			@Override
			public GrantedAuthority apply(String input) {
				return new userAuthority(input);
			}
		}).toList();
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class userAuthority implements GrantedAuthority {
		private String authority;

		@Override
		public String getAuthority() {
			return authority;
		}
	}
}
