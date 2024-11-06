package com.kh.damgarak.lunchBox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LunchBoxController {
	
	@GetMapping("/choiceLunchBox")
	public String choiceLunchBoxPage(){
		return "lunch-box/choiceLunchBox";
	}
	
	

}
