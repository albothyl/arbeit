package com.job.member.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.common.response.BaseResponse;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setResponseNG();
			baseResponse.setMessage("로그인 실패");
			mapper.writeValue(response.getWriter(), baseResponse);
		}
	}

}
