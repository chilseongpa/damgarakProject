package com.kh.damgarak.users.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.damgarak.emailauth.service.EmailAuthService;
import com.kh.damgarak.users.model.vo.Users;
import com.kh.damgarak.users.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final EmailAuthService emailAuthService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/checkEmail")
	@ResponseBody
	public String emailCheck(String email){
		Users emailCheck = userService.emailCheck(email);
		if(emailCheck != null) {
			return "사용불가"; 
		}
		return "사용가능";
	}
	
	
	@PostMapping("/findPwd")
	@ResponseBody
	public ResponseEntity<String> findPwd(String userName, String userId, String userMail) throws MessagingException{
		
		boolean checkUser = userService.findUserPwd(userName, userId, userMail);
		System.out.println(checkUser);
		
		if(!checkUser){
			return ResponseEntity.ok("사용자 정보를 찾을 수 없습니다.");
		}
		
		String tempPassword = emailAuthService.generateTemporaryPassword();
		
		emailAuthService.sendTemporaryPassword(userMail, tempPassword);
		
		int updateCheck = userService.updateUserPassword(userId, tempPassword);
		System.out.println("Update check result: " + updateCheck); // 디버그용
		if(0 > updateCheck){
			return ResponseEntity.ok("전송 시 문제가 생겼습니다.");
		}
		 return ResponseEntity.ok("임시 비밀번호가 전달되었습니다.");	
	}
	
	@GetMapping("/findUserId")
	public ResponseEntity<String> findUserId(String userName, String userMail){
		String userId = userService.findUserId(userName, userMail);		
		if(userId == null || userId.isEmpty()){
			return ResponseEntity.ok("");
		}{
			return ResponseEntity.ok(userId); 
		}		
	} 
	@GetMapping("/loginPage")
	public String userLoginPage(HttpSession session){
		return "users/loginOrSingup";
	}
	
	@PostMapping("/inrollform")
	public String userLogin(Users user, HttpSession session, Model model, 
			RedirectAttributes redirectAttributes){
		
		Users userLogin = userService.userLogin(user);
		
		if(userLogin != null && passwordEncoder.matches(user.getUsersPassword(), userLogin.getUsersPassword())) {
			session.setAttribute("userLogin", userLogin);
			redirectAttributes.addFlashAttribute("successMsg", user.getUsersId() + "님 환영합니다.");
			return "redirect:/";
		}else{
	        redirectAttributes.addFlashAttribute("errorMsg", "아이디 또는 비밀번호가 틀렸습니다");
			return "redirect:/loginPage"; 
		}
	}

	@PostMapping("/signupForm")	// Model : org.springframework.ui.Model; import하기! 
	public String userSignp(Users user, Model model,
			RedirectAttributes redirectAttributes){
		
		String encoderPassword = passwordEncoder.encode(user.getUsersPassword());
		user.setUsersPassword(encoderPassword);
		
		int singup =  userService.userSingup(user);
		
		if(singup > 0) {
			redirectAttributes.addFlashAttribute("successMsg", "회원가입에 성공했습니다");	
			return "redirect:/loginPage";
		}else {
			redirectAttributes.addFlashAttribute("errorMsg", "회원가입에 실패했습니다");
			return "redirect:/loginPage"; 
		}
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
	@GetMapping("/UsersMyPage")
    public String userMyPage(){
    	return "users/usersMyPage";
    }
	
}
