package com.job.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.job.member.security.handler.LoginFailureHandler;
import com.job.member.security.LoginProcessManager;
import com.job.member.security.handler.LoginSuccessHandler;
import com.job.member.security.CustomAuthenticationProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

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
		//filter.setRequiresAuthenticationRequestMatcher(new CsrfSecurityRequestMatcher());
		filter.setAuthenticationFailureHandler(authenticationFailureHandler());
		return filter;
	}

//	public class CsrfSecurityRequestMatcher implements RequestMatcher {
//		private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
//		private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/unprotected", null);
//
//		@Override
//		public boolean matches(HttpServletRequest request) {
//			if(allowedMethods.matcher(request.getMethod()).matches()){
//				return false;
//			}
//
//			return !unprotectedMatcher.matches(request);
//		}
//	}

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
