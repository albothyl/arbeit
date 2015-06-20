package com.job.member.domain;

import com.job.member.MemberGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

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
public class MemberEntity {
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
	private String roleId;
	@LastModifiedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedAt;
	@CreatedDate
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime registedAt;
}
