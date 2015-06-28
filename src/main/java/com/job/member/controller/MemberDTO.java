package com.job.member.controller;

import org.joda.time.DateTime;

import com.job.member.MemberGrade;

/**
 * Created by Administrator on 2015-06-26.
 */
public class MemberDTO {
	private Long id;
	private String email;
	private String name;
	private String nickName;
	private MemberGrade memberGrade;
	private DateTime updatedAt;
	private DateTime registedAt;
}
