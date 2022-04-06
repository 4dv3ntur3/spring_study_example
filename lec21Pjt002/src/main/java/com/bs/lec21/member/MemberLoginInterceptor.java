package com.bs.lec21.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// ��ӹ���
// HandlerInterceptor �������̽��� ������ Ŭ���� HandlerInterceptorAdapter
// �� ��ӹ޴´� 
// Ŭ������ ��������� ������ ���� ���Ͽ� �����
// servlet-context.xml

public class MemberLoginInterceptor extends HandlerInterceptorAdapter {

	// ���� ���� ����
	// ��Ʈ�ѷ� �۵� �� 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession(false);
		// ���� Ȯ�� 
		if(session != null) {
			Object obj = session.getAttribute("member");
			if(obj != null) 
				return true; // ���� ���� 
		}
		
		// �����̷�Ʈ
		response.sendRedirect(request.getContextPath() + "/");
		return false; // ���� ����
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