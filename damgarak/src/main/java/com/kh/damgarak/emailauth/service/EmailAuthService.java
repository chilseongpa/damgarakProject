package com.kh.damgarak.emailauth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailAuthService {
	
	private final JavaMailSender sender;
	private Map<String, String> authInfo = new HashMap<>();
	
	
	public void sendMail(String subject, String text, String[] to) {
		// 메일 제목, 메일 내용, 수신자
		SimpleMailMessage message = new SimpleMailMessage();

		// 메일 내용을 채워주는 역할을 한다.
		message.setSubject(subject);
		message.setText(text);
		message.setTo(to);

		// JavaMailSender를 통해 메일 전송
		sender.send(message);
	}
	public void sendCode(String email) throws MessagingException {
		// 랜덤 코드 생성
				String code = makeRandom("");

				String subject = "[Damgarak] 인증 코드 : ";
				String text = "<div style=\"font-family: Arial, sans-serif; color: #333; padding: 20px;\">"
			            + "  <h2 style=\"color: #4CAF50; text-align: center;\">[KH] 인증 코드</h2>"
			            + "  <p style=\"font-size: 16px; line-height: 1.5;\">안녕하세요!</p>"
			            + "  <p style=\"font-size: 16px; line-height: 1.5;\">"
			            + "    KH 서비스 인증을 위해 아래의 인증 코드를 입력해 주세요.<br>"
			            + "    인증 코드는 <strong>3분간 유효</strong>합니다."
			            + "  </p>"
			            + "  <div style=\"text-align: center; margin: 20px 0;\">"
			            + "    <span style=\"font-size: 24px; font-weight: bold; color: #4CAF50; padding: 10px 20px; "
			            + "                 border: 1px solid #4CAF50; border-radius: 5px;\">"
			            +        code
			            + "    </span>"
			            + "  </div>"
			            + "  <p style=\"font-size: 14px; color: #777; text-align: center;\">이 메시지를 요청하지 않았다면, 본 메일을 무시하셔도 됩니다.</p>"
			            + "</div>";
				
				String[] to = { email };
				// 이메일에 생성된 코드값을 Map에 저장
				authInfo.put(email, code);
				
				/*
				Date date = new Date(); 
				date.getMinutes()  + date 
				*/
				sendHTMLMail(subject, text, to);
				// 이메일 전송
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
		helper.setText(text, true); // true로 지정을 하면 html 형식으로 보내지게 된다. 
		helper.setTo(to);
		
		sender.send(mm);
	}
	public boolean checkCode(String email, String code) {
			
			// Map에서 email에 해당하는 코드를 찾아 
			// 전달된 code값과 동일한지 확인하여 결과를 반환
			String authCode= authInfo.get(email);
			
			if(authCode == null) {
				return false; 	// 이메일에 대한 발급코드가 없을 경우 => false
			}
			boolean result = authCode.equals(code);			
			
			if(result) {
				authInfo.remove(email);
			}
			return result;
		}
}