package com.kh.damgarak.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
	
	
	@GetMapping("/pastReservation")
	public String pastReservationPage(){
		return "reservation/reservation-inquiry/pastReservationPage";
	}

}
