package com.kh.damgarak.emailauth.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kh.damgarak.common.emailauth.model.vo.CodeInfo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailAuthService {

	private final JavaMailSender sender;
	private Map<String, CodeInfo> authInfo = new HashMap<>();
	
	
	public void sendMail(String subject, String text, String[] to) {
		
		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject(subject);
		message.setText(text);
		message.setTo(to);

		sender.send(message);
	}
	public void sendCode(String email) throws MessagingException {
		// 랜덤 코드 생성
				String code = makeRandom("");

				String subject = "[Damgarak] 인증 코드 : ";
				String text = "<div style=\"font-family: Arial, sans-serif; color: #333; padding: 20px; background-color: #fff3e0; border: 1px solid #ff4500; border-radius: 10px;\">"
				        + "  <h2 style=\"color: #ff4500; text-align: center; font-size: 26px; margin-top: 0;\">[KH] 인증 코드</h2>"
				        + "  <p style=\"font-size: 16px; color: #333; text-align: center; line-height: 1.5;\">안녕하세요!</p>"
				        + "  <p style=\"font-size: 16px; color: #333; text-align: center; line-height: 1.5;\">"
				        + "    KH 서비스 인증을 위해 아래의 인증 코드를 입력해 주세요.<br>"
				        + "    인증 코드는 <strong>3분간 유효</strong>합니다."
				        + "  </p>"
				        + "  <div style=\"text-align: center; margin: 20px 0;\">"
				        + "    <span style=\"display: inline-block; background-color: #ff4500; color: #ffffff; font-size: 24px; font-weight: bold; padding: 15px 30px; "
				        + "                 border-radius: 8px; letter-spacing: 2px;\">"
				        +        code
				        + "    </span>"
				        + "  </div>"
				        + "  <p style=\"font-size: 14px; color: #777; text-align: center;\">이 메시지를 요청하지 않았다면, 본 메일을 무시하셔도 됩니다.</p>"
				        + "</div>";
				
				String[] to = { email };
				
				authInfo.put(email, new CodeInfo(code, LocalDateTime.now()));
				
				sendHTMLMail(subject, text, to);
				
	}
	private String makeRandom(String code) {
		// code 길이가 6이면 그대로 반환
		if (code.length() == 6) {
			return code;
			// 재귀함수 끝나는 시점
		} else {
			int random = (int) (Math.random() * 10);

			if (code.length() % 2 == 0) {
				// 짝수일 때 소문자 추가
				int ran2 = (int) (Math.random() * ('z' - 'a' + 1) + 'a');
				code += (char) ran2;
			} else {
				// 홀수일 때 숫자 추가
				code += random;
			}
		}
		return makeRandom(code);
	}
	
	public void sendHTMLMail(String subject, String text, String[] to) throws MessagingException{
		MimeMessage mm = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mm, true);
		
		helper.setSubject(subject);
		helper.setText(text, true);  
		helper.setTo(to);
		
		sender.send(mm);
	}
	public boolean checkCode(String email, String code) {
			
			CodeInfo codeInfo = authInfo.get(email);
			
			if(codeInfo == null){
				return false;
			}
			
			LocalDateTime createdTime = codeInfo.getCreateTime();
			
			if (Duration.between(createdTime,
				LocalDateTime.now()).toMinutes() >= 3) {
		         authInfo.remove(email);  
		          return false;
		        }
			
			boolean result = codeInfo.getCode().equals(code);		
			
			if(result) {
				authInfo.remove(email);
			}
			
			return result;
		}
	public String generateTemporaryPassword() {
		return makeRandom("");
	}
	
	public void sendTemporaryPassword(String email, String tempPassword) throws MessagingException {
		String subject = "[Damgarak] 임시 비밀번호 안내";

		String text = "<div style=\"font-family: Arial, sans-serif; color: #333; padding: 20px; border: 1px solid #ddd; border-radius: 8px;\">"
		            + "  <h2 style=\"color: #4CAF50; text-align: center;\">임시 비밀번호 안내</h2>"
		            + "  <p style=\"font-size: 16px; line-height: 1.5;\">안녕하세요! Damgarak 서비스를 이용해 주셔서 감사합니다.</p>"
		            + "  <p style=\"font-size: 16px; line-height: 1.5;\">"
		            + "    요청하신 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해 주세요."
		            + "  </p>"
		            + "  <div style=\"text-align: center; margin: 20px 0;\">"
		            + "    <span style=\"font-size: 24px; font-weight: bold; color: #4CAF50; background-color: #f4f4f4; padding: 10px 20px; "
		            + "                 border: 1px solid #4CAF50; border-radius: 5px;\">"
		            +        tempPassword
		            + "    </span>"
		            + "  </div>"
		            + "  <p style=\"font-size: 14px; color: #777; text-align: center;\">본 이메일은 요청에 따라 자동 발송되었습니다. "
		            + "    비밀번호를 요청하지 않으셨다면, 이 메시지를 무시하셔도 됩니다.</p>"
		            + "  <hr style=\"border: none; border-top: 1px solid #ddd; margin: 20px 0;\">"
		            + "  <p style=\"font-size: 12px; color: #999; text-align: center;\">© Damgarak. All rights reserved.</p>"
		            + "</div>";
	        String[] to = { email };

	        sendHTMLMail(subject, text, to);
	    }
}
