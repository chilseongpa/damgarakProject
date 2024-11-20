package com.kh.damgarak.common.pusher.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.damgarak.common.pusher.service.PusherService;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/chat")
public class ChatController {

    private final PusherService pusherService;

    public ChatController(PusherService pusherService) {
        this.pusherService = pusherService;
    }


    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestParam String message, HttpSession session) {
        // Retrieve user from session
        UsersLoginDTO user = (UsersLoginDTO) session.getAttribute("userLogin");
        if (user == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(401).body(errorResponse);
        }

        // Prepare payload with user ID and message
        Map<String, String> messagePayload = new HashMap<>();
        messagePayload.put("userId", user.getUsersId()); // User ID
        messagePayload.put("message", message);         // Message

        // Send message via Pusher
        pusherService.sendMessage("my-channel", "my-event", messagePayload);
        System.out.println("전송 데이터: " + messagePayload);
        // Return a success response
        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("status", "success");
        successResponse.put("message", "Message sent successfully!");
        return ResponseEntity.ok(successResponse);
    }
    
}