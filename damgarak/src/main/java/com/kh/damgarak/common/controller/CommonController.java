package com.kh.damgarak.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	@GetMapping("/partnerCompany")
	public String partnerCompanyPage(){
		return "damgarak/partnerCompany";
	}
}
