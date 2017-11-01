package com._520it.wms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.UserContext;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Employee e = UserContext.getCurrentUser();
		if(e==null){
			response.sendRedirect("/login.jsp");
			//阻止
			return false;
		}
		//放行
		return true;
	}
}
