package com.kh.damgarak.users.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.damgarak.users.model.vo.Users;
import com.kh.damgarak.users.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/loginPage")
	public String UserLoginPage(){
		
		return "users/loginOrSingup";
	}
	
	@PostMapping("/inrollform")
	public String userLogin(Users user, HttpSession session){
			
		Users userLogin = userService.userLogin(user);
		
		if(userLogin != null) {
			session.setAttribute("userLogin", userLogin);
		}else{
			
		}
		return "redirect:/";
	}
	
	@PostMapping("/signupForm")	// Model : org.springframework.ui.Model; import하기! 
	public String userSingup(Users user, Model model ){
		
		
		int singup =  userService.userSingup(user);
		
		if(singup > 0) {
			model.addAttribute("message", "회원가입이 완료 되었습니다" );
			return "redirect:/login/loginOrSingup";
		}else {
			model.addAttribute("errMessage", "회원가입이 실패했습니다" );
			return "redirect:/"; 
		}
		/*
		 * 리다이렉트(redirect) vs 뷰 이름 반환:
		return "redirect:/login/loginOrLogup";: 
		리다이렉트는 서버에 새로운 요청을 보내서 URL을 변경하고, 
		모델에 추가된 데이터(message, errMessage)는 리다이렉트 이후의 뷰에 전달되지 않습니다.
		
		return "login/loginOrLogup";: 뷰 이름을 직접 반환하는 경우, 
		리다이렉트 없이 현재 요청에서 뷰를 렌더링합니다. 이때,
		모델에 추가된 데이터가 그대로 전달되어 에러 메시지와 같은 정보가 뷰에서 유지됩니다.
		실패 시 메시지 유지 여부:

		redirect:/login/loginOrLogup을 사용하면 실패 시 모델에 추가된 errMessage가 뷰에 전달되지 않아서,
		 회원가입 실패 메시지를 표시할 수 없습니다.
		login/loginOrLogup로 뷰 이름을 반환하면, 
		실패 메시지가 뷰에 전달되어 사용자가 회원가입 실패 이유를 확인할 수 있습니다.
		즉, 리다이렉트를 사용하면 URL이 새로고침되지만 모델 속성(message, errMessage)이 유지되지 않고,
		 뷰 이름을 반환하면 모델 속성이 그대로 유지됩니다.
		 * 
		 * */	
	}
	@GetMapping("/idCheck.me")
	public ResponseEntity<String> UserIdCheck(String usersId){
		
		int count = userService.idCheck(usersId);
		
		if(count > 0){	
			return ResponseEntity.ok("false");
		}else {
			return ResponseEntity.ok("true");
		}				
	}
}
