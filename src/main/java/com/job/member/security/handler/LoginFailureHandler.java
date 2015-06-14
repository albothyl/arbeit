package com.job.member.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.common.response.ResponseWarpper;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			ResponseWarpper responseWarpper = new ResponseWarpper();
			responseWarpper.setResponseNG();
			responseWarpper.setMessage("로그인 실패");
			mapper.writeValue(response.getWriter(), responseWarpper);
		}
	}

}
