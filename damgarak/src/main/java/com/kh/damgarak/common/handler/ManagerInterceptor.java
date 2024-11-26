//package com.kh.damgarak.common.handler;
//
//import java.io.IOException;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@Component
//public class ManagerInterceptor implements HandlerInterceptor {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		HttpSession session = request.getSession();
//
//		UsersLoginDTO user = (UsersLoginDTO) session.getAttribute("userLogin");
//
//		// b check = user.getUsers().getemployeeType(직원 고객).equals("관리자")
//		if (user == null) {
//			System.out.println("로그인되지 않은 사용자: 세션이 null입니다.");
//			response.sendRedirect("/loginPage");
//			return false;
//
//		} else {
//			
//			if (user.getEmployeeType().equals("관리자")) {
//				System.out.println("관리자 접근 허용: " + user);
//				return true;
//
//			} else {
//
//				System.out.println("고객,직원 사용자 접근 제한: " + user);
//				response.sendRedirect("/main");
//
//				return false;
//			}
//
//		}	
//	}
//
//}
