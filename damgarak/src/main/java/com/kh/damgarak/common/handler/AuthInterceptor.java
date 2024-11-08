package com.kh.damgarak.common.handler;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		Object user = session.getAttribute("user");

		if (user == null) {

			response.sendRedirect("/loginPage");

			return false;
		} else {

			return true;
		}
	}
}
