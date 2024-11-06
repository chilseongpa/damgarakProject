package com.kh.damgarak.lunchBoxMenu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LunchBoxMenuController {

	@GetMapping("/lunchBoxMenu")
	public String choiceLunchBoxPage(){
		return "lunch-box/lunchBoxMenu";
	}
	
	
}
