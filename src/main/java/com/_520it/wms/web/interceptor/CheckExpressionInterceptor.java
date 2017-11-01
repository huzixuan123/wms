package com._520it.wms.web.interceptor;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.util.UserContext;
import com._520it.wms.web.exception.SystemException;

public class CheckExpressionInterceptor extends HandlerInterceptorAdapter{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Method handlerMethod = ((HandlerMethod)handler).getMethod();
		//1.如果是超级管理员,放行
		if(UserContext.getCurrentUser().isAdmin()){
			return true;
		}
		//2.如果方法上没有贴@RequiredPermission注解,放行
		if(!handlerMethod.isAnnotationPresent(RequiredPermission.class)){
			return true;
		}
		//3.获取controller方法的权限表达式
		String exp = handlerMethod.getDeclaringClass().getName()+":"+handlerMethod.getName();
		//4.获取该用户拥有的权限表达式
		List<String> exps = UserContext.getPermissionExpressions();
		//4.判断exp是否在exps中
		if(exps.contains(exp)){
			return true;
		}
		throw new SystemException("亲,您没有权限,请联系管理员!");
	}
}
