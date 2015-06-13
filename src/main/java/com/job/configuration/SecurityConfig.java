package com.job.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2015-06-13.
 */
public class SecurityConfig {

	@Configuration
	@EnableWebMvcSecurity
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.antMatchers("/", "/home").permitAll()
					.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")
					.permitAll()
				.and()
					.logout()
				.permitAll();
		}

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
		}
	}

	@Configuration
	public static class MvcConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/hello").setViewName("/healthCheck");
			registry.addViewController("/").setViewName("/healthCheck");
			registry.addViewController("/main").setViewName("/member/loginComplete");
			registry.addViewController("/login").setViewName("/member/loginForm");
		}

	}
}
