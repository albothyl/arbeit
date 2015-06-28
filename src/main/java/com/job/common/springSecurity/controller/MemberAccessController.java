package com.job.common.springSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberAccessController {

	@RequestMapping("/loginForm")
	public ModelAndView loginForm() {
		return new ModelAndView("/member/loginForm");
	}

	@RequestMapping("/loginSuccess")
	public ModelAndView loginSuccess() {
		return new ModelAndView("/member/loginSuccess");
	}
}
