package com.job.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

import com.job.common.springSecurity.service.AuthenticationService;

@Configuration
@EnableWebMvcSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthenticationService authenticationService;

	@Bean
	protected SessionRegistry sessionRegistryImpl() {
		return new SessionRegistryImpl();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.userDetailsService(authenticationService)
				.passwordEncoder(new ShaPasswordEncoder(256))
			.and()
				.eraseCredentials(true);
	}

	@Override
	public void configure(WebSecurity security) {
		security.ignoring().antMatchers("/resource/**", "/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security
			.authorizeRequests()
				.antMatchers("/hello/**").anonymous().anyRequest().authenticated()
				.antMatchers("/session/list").hasAuthority("VIEW_USER_SESSIONS").anyRequest().authenticated() //세션에 있는건 권한체크없이 바로 통과인데..나중에 좀 살펴볼것.
				.antMatchers(actuatorAdminEndpoints()).access("hasRole('ADMIN')")
				.antMatchers(actuatorUserEndpoints()).access("hasRole('USER')")
			.and().formLogin()
				.loginPage("/member/loginForm").failureUrl("/login?loginFailed")
				.defaultSuccessUrl("/hello")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
			.and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.permitAll()
			.and().sessionManagement()
				.sessionFixation().changeSessionId()
				.maximumSessions(1).maxSessionsPreventsLogin(true)
				.sessionRegistry(this.sessionRegistryImpl())
			.and().and().csrf()
				.disable();
	}

	private String[] actuatorAdminEndpoints() {
		return new String[]{"/admin/**"};
	}

	private String[] actuatorUserEndpoints() {
		return new String[]{"/arbeit/**"};
	}
}
