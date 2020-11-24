package kr.co.bteam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
		
	private static final String LOGIN = "login";
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	
	// model은 페이지 지정 못함. modelandview 는 상황에 따른 페이지 지정 및 분기 가능 (redirect가능함.) 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		
		//컨트롤러에서 담은거 받아옴. 
		Object memInfo = modelMap.get("memInfo");
		
		
		
		if (memInfo != null) {
			log.info("new login success");
			session.setAttribute(LOGIN, memInfo);
			
			//세션 유지 시간 1시간 			
			session.setMaxInactiveInterval(60*60) ;
			
			Object dest = session.getAttribute("dest");
			
			if (dest == null) {
				dest = "/"; // 돌아 갈 메인페이지 넣어주기 
			}
			log.info("LoginInterceptor Dest  :  " + dest);
			
			modelAndView.setViewName("redirect:" + (String) dest);
		} 
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(LOGIN) != null) {
			log.info("clear login data before");
			
			session.removeAttribute(LOGIN);
		}
		
		return true;
		
	}
	
	
}
