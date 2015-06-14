package com.job.member.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.job.member.security.handler.LoginFailureHandler;
import com.job.member.security.handler.LoginProcessManager;
import com.job.member.security.handler.LoginSuccessHandler;
import com.job.member.security.provider.CustomAuthenticationProvider;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(actuatorEndpoints()).access("hasRole('ROLE_PUSER')")
			.and()
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
		http.addFilterBefore(authenticationProcessFilter(), UsernamePasswordAuthenticationFilter.class);

		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
	}

	private String[] actuatorEndpoints() {
		return new String[] { "/admin/**" };
	}

	@Bean
	public LoginUrlAuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/authMgt/accessDenied");
	}

	@Bean
	public LoginProcessManager authenticationProcessFilter() throws Exception {
		LoginProcessManager filter = new LoginProcessManager();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		filter.setFilterProcessesUrl("/");
		filter.setAuthenticationFailureHandler(authenticationFailureHandler());
		return filter;
	}

	@Bean
	public LoginSuccessHandler authenticationSuccessHandler() {
		return new LoginSuccessHandler();
	}

	@Bean
	public LoginFailureHandler authenticationFailureHandler() {
		return new LoginFailureHandler();
	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
}
