package com.kh.damgarak.emailauth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.damgarak.emailauth.model.vo.MailRequest;
import com.kh.damgarak.emailauth.service.EmailAuthService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmailAuthController {
	
	private final EmailAuthService emailAuthService;
	
	@PostMapping("mail")
	public String sendAuth(String email){
		
		try {
			emailAuthService.sendCode(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	       return "인증번호 발송되었습니다";
	    }
	
	@PostMapping("/check")
	public String checkCode(@RequestBody MailRequest request ) throws Exception {
		String email = request.getEmail();
		String code = request.getCode();
		
		if(email == null && code == null) {
			throw new Exception("필수 데이터가 전달되지 않았습니다. ");
		}
		
		log.info("*email {}, code {}, " + email, code);
		
		boolean result = emailAuthService.checkCode(email, code);
		
		if(result){
			return "success";
		}else {
			
			return "failed";
		}
	}	
}
	
	
	



	
	


