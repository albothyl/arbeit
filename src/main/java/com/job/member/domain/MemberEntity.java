package com.job.member.domain;

import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Lists;
import com.job.member.MemberGrade;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Administrator on 2015-05-31.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "arbeit", name = "member")
@SuppressWarnings({ "PMD.UnusedPrivateField" })
public class MemberEntity implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;
	private String password;
	private String name;
	private String nickName;
	@Enumerated(EnumType.STRING)
	@Column(name = "GRADE")
	private MemberGrade memberGrade;
	//private boolean authenticated;
	@LastModifiedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedAt;
	@CreatedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime registedAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Lists.newArrayList();
	}

	@Override public String getUsername() {
		return email;
	}

	@Override public boolean isAccountNonExpired() {
		return true;
	}

	@Override public boolean isAccountNonLocked() {
		return true;
	}

	@Override public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override public boolean isEnabled() {
		return true;
	}
}
