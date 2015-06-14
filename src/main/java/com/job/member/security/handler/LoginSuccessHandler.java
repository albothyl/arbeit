package com.job.member.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.job.common.response.BaseResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.member.security.userdetails.CustomUserDetails;
import com.job.member.security.userdetails.UserDetailsVO;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			ObjectMapper mapper = new ObjectMapper();
			BaseResponse baseResponse = new BaseResponse();
			CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
			UserDetailsVO user = (UserDetailsVO)principal.getUserVO();
			baseResponse.setData(user);
			baseResponse.setResponseOK();
			response.setCharacterEncoding("UTF-8");
			mapper.writeValue(response.getWriter(), baseResponse);
		}
	}

}
