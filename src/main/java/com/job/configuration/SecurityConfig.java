package com.job.configuration;

import com.job.member.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthenticationService authenticationService;

	@Bean
	protected SessionRegistry sessionRegistryImpl() {
		return new SessionRegistryImpl();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) {
		builder.authenticationProvider(this.authenticationService);
	}

	@Override
	public void configure(WebSecurity security) {
		security.ignoring().antMatchers("/resource/**", "/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security
			.authorizeRequests()
			.anyRequest().authenticated()
			.and().formLogin()
				.loginPage("/login").failureUrl("/login?loginFailed")
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
				.requireCsrfProtectionMatcher((r) -> {
					String m = r.getMethod();
					return !r.getServletPath().startsWith("/services/") &&
						("POST".equals(m) || "PUT".equals(m) ||
							"DELETE".equals(m) || "PATCH".equals(m));
				});
	}
}
