package com.job.member.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.common.response.BaseResponse;
import com.job.member.domain.MemberEntity;
import com.job.member.security.SecurityUser;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			ObjectMapper mapper = new ObjectMapper();
			BaseResponse baseResponse = new BaseResponse();
			SecurityUser principal = (SecurityUser) authentication.getPrincipal();
			MemberEntity user = (MemberEntity)principal.getUserVO();
			baseResponse.setData(user);
			baseResponse.setResponseOK();
			response.setCharacterEncoding("UTF-8");
			mapper.writeValue(response.getWriter(), baseResponse);
		}
	}

}
