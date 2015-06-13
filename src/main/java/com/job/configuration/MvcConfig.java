package com.job.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("/healthCheck");
		registry.addViewController("/").setViewName("/healthCheck");
		registry.addViewController("/hello").setViewName("/member/loginComplete");
		registry.addViewController("/login").setViewName("/member/loginForm");
	}

}