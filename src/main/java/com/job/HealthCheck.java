package com.job;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HealthCheck {
	@RequestMapping("/arbeit/hello")
	public ModelAndView Check() {
		return new ModelAndView("healthCheck");
	}

	@RequestMapping("/hello")
	public ModelAndView healthCheck() {
		return new ModelAndView("healthCheck");
	}
}
