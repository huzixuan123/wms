package com._520it.wms.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com._520it.wms.domain.Employee;

//操作当前登陆用户的会话信息
public class UserContext {

	public static final String USER_IN_SESSION = "user_in_session";
	public static final String EXPRESSION_IN_SESSION = "expression_in_session";

	//获取session对象
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getSession();
	}

	//将登陆信息存储到session中
	public static void setCurrentUser(Employee e) {
		if (e != null) {
			getSession().setAttribute(USER_IN_SESSION, e);
		} else {
			getSession().invalidate();
		}
	}

	//获取当前登陆用户信息
	public static Employee getCurrentUser() {
		Employee e = (Employee) getSession().getAttribute(USER_IN_SESSION);
		return e;
	}

	//将当前用户的权限表达式存储到session中
	public static void setPermissionExpressions(List<String> exps) {
			getSession().setAttribute(EXPRESSION_IN_SESSION, exps);
	}
	
	//获取当前用户的权限表达式
	public static List<String> getPermissionExpressions() {
		List<String> exps = (List<String>) getSession().getAttribute(EXPRESSION_IN_SESSION);
		return exps;
	}
}
