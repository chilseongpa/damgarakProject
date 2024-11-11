package com.kh.damgarak.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.damgarak.post.service.ManagerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommonController {
	
	@GetMapping("/map")
	public String mapPage() {
		return "damgarak/map";
	}
	@GetMapping("/main")
	public String mainPage() {
		return "/index.html";
	}
}
