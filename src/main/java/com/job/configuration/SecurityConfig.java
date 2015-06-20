package com.job.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class SecurityConfig {

	@Configuration
	@EnableWebMvcSecurity
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
			builder
				.inMemoryAuthentication()
				.withUser("user")
				.password("password")
				.roles("USER");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.antMatchers("/", "/hello", "/home").permitAll()
					.anyRequest().authenticated()
				.and().formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/hello")
					.usernameParameter("username")
					.passwordParameter("password")
					.permitAll()
				.and()
					.logout().invalidateHttpSession(true).permitAll()
				.and()
					.csrf().disable();

			CharacterEncodingFilter filter = new CharacterEncodingFilter();
			filter.setEncoding("UTF-8");
			filter.setForceEncoding(true);
			http.addFilterBefore(filter,CsrfFilter.class);
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
