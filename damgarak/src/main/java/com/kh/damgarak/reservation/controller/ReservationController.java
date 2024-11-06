package com.kh.damgarak.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
	
	@GetMapping("/reservationTableTimeChoice")
	public String TableTimeChoicePage(){
		return "reservation/table-reservation/reservationTableTimeChoice";
	}
	
	@GetMapping("/reservation/table-reservation/tableChoicePage")
	public String reservationTableState(@RequestParam("date") String date,
            @RequestParam("time") String time,
            Model model){
		
	     model.addAttribute("date", date);
	     model.addAttribute("time", time);
	 
		return "/reservation/table-reservation/tableChoicePage";
	}
	
	@GetMapping("/tableChoicePage")
	public String tableChoicePage(){
		return "reservation/table-reservation/tableChoicePage";
	}
	
}
