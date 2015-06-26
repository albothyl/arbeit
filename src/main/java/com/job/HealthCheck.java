package com.job;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HealthCheck {
	@RequestMapping("/arbeit/hello")
	public ModelAndView userCheck() {
		return new ModelAndView("healthCheck");
	}

	@RequestMapping("/admin/hello")
	public ModelAndView adminCheck() {
		return new ModelAndView("healthCheck");
	}

	@RequestMapping("/hello")
	public ModelAndView healthCheck() {
		return new ModelAndView("healthCheck");
	}
}
