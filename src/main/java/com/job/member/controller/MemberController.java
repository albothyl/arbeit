package com.job.member.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/arbeit")
public class MemberController {

	@RequestMapping(value = "/registMember")
	public ModelAndView registMember(MemberDTO memberDTO) {
		ModelAndView modelAndView = new ModelAndView("/member/registForm");
		return modelAndView;
	}

	@RequestMapping(value = "/list")
	public ModelAndView listtestMember(MemberDTO memberDTO) {
		ModelAndView modelAndView = new ModelAndView("/member/list");
		return modelAndView;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detailtestMember(MemberDTO memberDTO) {
		ModelAndView modelAndView = new ModelAndView("/member/detail");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/updateMember")
	public ModelAndView updateMember(MemberDTO memberDTO) {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping(value = "/admin/secedeMember")
	public ModelAndView secedeMember(@RequestParam Long memberId) {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping(value = "/admin/viewMemberDetail")
	public ModelAndView viewMemberDetail(@RequestParam Long memberId) {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping(value = "/admin/getMemberList")
	public ModelAndView getMemberList(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
}
