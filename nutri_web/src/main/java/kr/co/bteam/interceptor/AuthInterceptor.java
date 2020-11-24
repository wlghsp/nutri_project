package kr.co.bteam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{
			
	private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
	
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		uri = uri.substring(contextPath.length());
		
		log.info("uri ========>" + uri );
		
		String query = request.getQueryString(); // 물음표 다음 쿼리를 얻어옴.
		
		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if (request.getMethod().equals("GET")) {
			log.info("dest:  " +  (uri + query) );
			
			request.getSession().setAttribute("dest", uri + query);
		}
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath(); // 들어 온 경로 찾음. 
		
		if (session.getAttribute("login") == null) {
			log.info("current user is not logined");
			
			saveDest(request);
			
			
			response.sendRedirect(contextPath + "/member/login");
			return false;
		}
		
		return true;
		
	}


	
	
}
