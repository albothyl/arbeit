package com.job.member.security;

import java.io.BufferedReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginProcessManager extends UsernamePasswordAuthenticationFilter {
	private String jsonUsername;
	private String jsonPassword;

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		String password = null;
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			password = this.jsonPassword;
		} else {
			password = super.obtainPassword(request);
		}
		return password;
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String username = null;
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			username = this.jsonUsername;
		} else {
			username = super.obtainUsername(request);
		}
		return username;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			try {
				StringBuffer sb = new StringBuffer();
				String line = null;

				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> params = mapper.readValue(sb.toString(), Map.class);

				this.jsonUsername = params.get("j_username");
				this.jsonPassword = params.get("j_password");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.attemptAuthentication(request, response);
	}
}
