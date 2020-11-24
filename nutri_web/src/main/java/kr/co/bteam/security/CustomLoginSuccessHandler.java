package kr.co.bteam.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	private static final Logger log = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		String contextPath = request.getContextPath();
		log.info("contextPath---->" + contextPath);
		log.warn("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		
		auth.getAuthorities().forEach(authority-> {
			roleNames.add(authority.getAuthority());
		});
		
		log.warn("ROLE NAMES: " + roleNames);
		
//		if (roleNames.contains("ROLE_ADMIN")) {
//			response.sendRedirect(contextPath+"/admin/index");
//			return;
//		}
//		if (roleNames.contains("ROLE_MANAGER")) {
//			response.sendRedirect(contextPath+"/member/mypage");
//			return;
//		}
		
		response.sendRedirect(contextPath+"/admin/index");
	}
}
