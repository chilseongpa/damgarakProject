package com.kh.damgarak.payment.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.kh.damgarak.payment.dto.PaymentDto;
import com.kh.damgarak.payment.service.PaymentService;

import java.util.Date;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment")
    public String paymentPage() {
        return "lunch-box/payment";  
    }

    @GetMapping("/LunchBoxyeongsujeung")
    public String paymentSuccessPage(
            @RequestParam("orderId") String orderId,
            @RequestParam("amount") String amount,
            @RequestParam(value = "menuInfo", required = false) String menuInfo, // JSON 형태로 전달
            Model model) {
        // 메뉴 정보 디코딩
        System.out.println("Received orderId: " + orderId);
        System.out.println("Received amount: " + amount);
        System.out.println("Received menuInfo: " + menuInfo);

        model.addAttribute("orderId", orderId);
        model.addAttribute("amount", amount);
        model.addAttribute("menuInfo", menuInfo); // View로 전달
        return "lunch-box/LunchBoxyeongsujeung";
    }

    
    @PostMapping("/api/checkPayment")
    public ResponseEntity<String> checkPayment(
            @RequestParam String orderId, 
            @RequestParam String paymentKey, 
            @RequestParam int amount) {
        
        String url = "https://api.tosspayments.com/v1/payments/confirm";

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq", ""); // Toss Payments의 Secret Key
        headers.set("Content-Type", "application/json");

        // 요청 바디에 필요한 데이터 설정
        String body = String.format(
            "{\"paymentKey\":\"%s\", \"orderId\":\"%s\", \"amount\":%d}", paymentKey, orderId, amount
        );

        // HTTP 요청 엔티티 생성
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            // 결제 상태 조회 요청
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            System.out.println("결제 api 확인하는 코드 "+response);
            
            // 결제가 성공적으로 확인된 경우 결제 정보를 저장
            if (response.getStatusCode().is2xxSuccessful()) {
                PaymentDto paymentDto = new PaymentDto();
                // TODO: Service 통해서 저장하기
              //  PaymentDto dto = paymentService.insertPayment();
            	
                
            }

            return response; // 조회 결과 반환

        } catch (Exception e) {
            // 조회 실패 시 오류 반환
            return ResponseEntity.status(500).body("결제 확인 중 오류 발생: " + e.getMessage());
        }
    
    }
    
}