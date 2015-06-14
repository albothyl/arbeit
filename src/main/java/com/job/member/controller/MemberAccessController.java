package com.job.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberAccessController {

    @RequestMapping("/loginForm")
    public ModelAndView Login() {
        return new ModelAndView("/member/loginForm");
    }
}
