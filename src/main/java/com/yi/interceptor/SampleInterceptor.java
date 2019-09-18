package com.yi.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handler----------------------------");
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		System.out.println("Bean : "+ method.getBean());
		System.out.println("Method : "+ methodObj);
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("post handler----------------------------");
		
		Object result=modelAndView.getModel().get("result"); //result key에 담긴값 나옴
		
		System.out.println(result);
		if(result!=null) { //result key가 있으면 redirect
			request.getSession().setAttribute("test", "test test test");
			response.sendRedirect("doA");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("after handler----------------------------");
		super.afterCompletion(request, response, handler, ex);
	}
	
	
}
