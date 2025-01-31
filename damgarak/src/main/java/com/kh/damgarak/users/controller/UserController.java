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
import com.kh.damgarak.employee.model.vo.Employee;
import com.kh.damgarak.users.model.vo.Users;
import com.kh.damgarak.users.service.UserService;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;
import com.kh.damgarak.users.userMemvership.model.dto.userMemvershipDTO;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final EmailAuthService emailAuthService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("/changeInfoMyPage")
	public String changeInfoMyPage() {
		return "users/userChangeInfo";
	}
	
	@PostMapping("/checkPassword")
	@ResponseBody
	public String checkPassword(HttpSession session, String password) {
		UsersLoginDTO dto = (UsersLoginDTO) session.getAttribute("userLogin");	
		System.out.println(dto);		
		if (dto == null) {
			return "false";
		}
		String usersId = dto.getUsersId();
		UsersLoginDTO users = userService.checkPassword(usersId);
		
		boolean isTrue = passwordEncoder.matches(password, users.getUsersPassword());
		
		if (users != null  &&  isTrue ) {
			return "true";
		} else {
			return "false";
		}
	}
	@PostMapping("/updatePassword")
	@ResponseBody
	public String updateUserPassword(HttpSession session, String password) {
		UsersLoginDTO dto = (UsersLoginDTO) session.getAttribute("userLogin");
		String usersId = dto.getUsersId();
		
		String encoderPassword = passwordEncoder.encode(password);
		
		int result = userService.updateUserPassword(usersId, encoderPassword);
		if (result > 0) {
			dto.setUsersPassword(encoderPassword);
			return "success";
		} else {
			return "filed";
		}
	}
	
	@GetMapping("/userslogout")
	public String usersLogout(HttpSession session) {
		session.removeAttribute("userLogin");
		return "redirect:/";
	}

	@GetMapping("/goToMain")
	public String goMain() {
		return "redirect:/";
	}

	@GetMapping("/checkEmail")
	@ResponseBody
	public String emailCheck(String email) {
		Users emailCheck = userService.emailCheck(email);
		if (emailCheck != null) {
			return "사용불가";
		}
		return "사용가능";
	}

	@PostMapping("/findPwd")
	@ResponseBody
	public ResponseEntity<String> findPwd(String userName, String userId, String userMail) throws MessagingException {
		
		boolean checkUser = userService.findUserPwd(userName, userId, userMail);
		

		if (!checkUser) {
			return ResponseEntity.ok("사용자 정보를 찾을 수 없습니다.");
		}
		
		String tempPassword = emailAuthService.generateTemporaryPassword();
		emailAuthService.sendTemporaryPassword(userMail, tempPassword);
		String encoderPassword = passwordEncoder.encode(tempPassword);
		int updateCheck = userService.updateUserPassword(userId, encoderPassword);
		
		if (0 > updateCheck) {
			return ResponseEntity.ok("전송 시 문제가 생겼습니다.");
		}
		return ResponseEntity.ok("임시 비밀번호가 전달되었습니다.");
	}

	
	@GetMapping("/findUserId")
	public ResponseEntity<String> findUserId(String userName, String userMail) {
		String userId = userService.findUserId(userName, userMail);
		if (userId == null || userId.isEmpty()) {
			return ResponseEntity.ok("");
		}
		{
			return ResponseEntity.ok(userId);
		}
	}

	@GetMapping("/loginPage")
	public String userLoginPage(HttpSession session) {
		return "users/loginOrSignup";
	}

	@PostMapping("/enrollform")
	public String userLogin(String usersId, String usersPassword, HttpSession session,
			RedirectAttributes redirectAttributes) {

		UsersLoginDTO userLogin = userService.userLogin(usersId);

		if (userLogin != null && passwordEncoder.matches(usersPassword, userLogin.getUsersPassword())) {
			session.setAttribute("userLogin", userLogin);
			redirectAttributes.addFlashAttribute("successMsg", usersId + "님 환영합니다.");
			return "redirect:/";
		} else {
			redirectAttributes.addFlashAttribute("errorMsg", "아이디 또는 비밀번호가 틀렸습니다");
			return "redirect:/loginPage";
		}
	}
	
	@PostMapping("/signupForm") 
	public String userSignp(Users user, Model model, Employee employee, RedirectAttributes redirectAttributes) {

		String encoderPassword = passwordEncoder.encode(user.getUsersPassword());
		user.setUsersPassword(encoderPassword);
		
		String empId = user.getUsersId();
		
		
		int singup = userService.userSingup(user);
		
		boolean userType = user.getUsersType().equals("직원");
		
		int empResult = 1;
		
		if(userType){		
		
			empResult = userService.empSingup(empId);
		
		}
		
		if (singup > 0 && empResult > 0) {
			redirectAttributes.addFlashAttribute("successMsg", "회원가입에 성공했습니다");
			return "redirect:/loginPage";
		} else {
			redirectAttributes.addFlashAttribute("errorMsg", "회원가입에 실패했습니다");
			return "redirect:/loginPage";
		}
	}
	
	@GetMapping("/idCheck.me")
	public ResponseEntity<String> UserIdCheck(String usersId) {
		int count = userService.idCheck(usersId);
		if (count > 0) {
			return ResponseEntity.ok("false");
		} else {
			return ResponseEntity.ok("true");
		}
	}
	@GetMapping("/usersMyPage")
	public String userMyPage(HttpSession session, Model model) {
		UsersLoginDTO dto = (UsersLoginDTO)session.getAttribute("userLogin");
		String usersId = dto.getUsersId();
				
		userMemvershipDTO userDto = userService.userMyPageInfo(usersId);
		System.out.println(userDto);
		
		if(userDto != null){
			model.addAttribute("d",userDto);
			return "users/userMyPage";			
		}	
			return "users/userMyPage"; 
	}
	@GetMapping("/index.html")
    public String index() {
        return "index"; // index.html 파일을 반환
    }

	@PostMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(HttpSession session){
		UsersLoginDTO dto = (UsersLoginDTO)session.getAttribute("userLogin");
		String usersId = dto.getUsersId();
		
		int deleteCheck = userService.deleteUser(usersId);
		if(deleteCheck > 0){
			return "success";
		}else {
			return "failed";
		}
	}

}
