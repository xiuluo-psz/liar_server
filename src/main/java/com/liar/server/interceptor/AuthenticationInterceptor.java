package com.liar.server.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.liar.server.annotation.Authentication;
import com.liar.server.constant.Constants;
import com.liar.server.constant.Status;
import com.liar.server.service.TokenService;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {
		// 如果不是映射到方法直接通过
		if (!(object instanceof HandlerMethod)) {
			return true;
		}

		// 从请求头中取出 token
		String token = httpServletRequest.getHeader(Constants.AUTHORIZATION);
		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();

		// 检查是否有passToken注释，有则跳过认证
//		if (method.isAnnotationPresent(PassToken.class)) {
//			PassToken passToken = method.getAnnotation(PassToken.class);
//			if (passToken.required()) {
//				return true;
//			}
//		}

		boolean verify = true;
		// 检查有没有需要用户权限的注解
		if (method.isAnnotationPresent(Authentication.class)) {
			Authentication authentication = method.getAnnotation(Authentication.class);
			if (authentication.required()) {
				verify = tokenService.verifyToken(token);
			}
		}
		if (!verify) {
			throw new RuntimeException(Status.MSG_UNAUTHORIZED);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}
}
