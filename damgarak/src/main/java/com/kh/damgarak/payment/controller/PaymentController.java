package com.kh.damgarak.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @GetMapping("/payment")
    public String paymentPage() {
        return "lunch-box/payment";  
    }

    @GetMapping("/LunchBoxyeongsujeung")
    public String paymentSuccessPage(
            @RequestParam("orderId") String orderId,
            @RequestParam("amount") String amount,
            Model model) {
        // View에 결제 정보를 전달
        model.addAttribute("orderId", orderId);
        model.addAttribute("amount", amount);
        return "lunch-box/LunchBoxyeongsujeung";
    }
}
