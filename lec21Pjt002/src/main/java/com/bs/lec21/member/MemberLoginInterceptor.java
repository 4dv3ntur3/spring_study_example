package com.bs.lec21.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 상속받음
// HandlerInterceptor 인터페이스를 구현한 클래스 HandlerInterceptorAdapter
// 를 상속받는다 
// 클래스를 만들었으니 스프링 설정 파일에 등록함
// servlet-context.xml

public class MemberLoginInterceptor extends HandlerInterceptorAdapter {

	// 제일 자주 쓰임
	// 컨트롤러 작동 전 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession(false);
		// 세션 확인 
		if(session != null) {
			Object obj = session.getAttribute("member");
			if(obj != null) 
				return true; // 세션 있음 
		}
		
		// 리다이렉트
		response.sendRedirect(request.getContextPath() + "/");
		return false; // 세션 없음
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}
}