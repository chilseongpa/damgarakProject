package com.kh.damgarak.common.pusher.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PusherPageController {
    @GetMapping("/pusher")
    public String pusherPage(HttpSession session, 
    		RedirectAttributes redirectAttributes,
    		Model model) {	
    	UsersLoginDTO dto = (UsersLoginDTO)session.getAttribute("userLogin");
    	
    	if(dto == null){
    		redirectAttributes.addFlashAttribute("errorMsg", "로그인 이후 이용이 가능합니다.");
    		return "redirect:/";
    	}
    	
    	
    	model.addAttribute("userId", dto.getUsersId());
    	return "/pusher/pusher";
    }
}
