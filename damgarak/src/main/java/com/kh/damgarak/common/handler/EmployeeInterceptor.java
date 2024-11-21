package com.kh.damgarak.common.handler;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class EmployeeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		UsersLoginDTO user = (UsersLoginDTO) session.getAttribute("userLogin");

		// b check = user.getUsers().getemployeeType(직원 고객).equals("관리자")
		if (user == null) {
			System.out.println("로그인되지 않은 사용자: 세션이 null입니다.");
			response.sendRedirect("/loginPage");
			return false;

		} else {
			
			if(user.getEmployeeType().equals("직원")) {
				
			}
			
			return true;
		}	
	}

}
