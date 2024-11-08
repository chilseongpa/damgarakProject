package com.kh.damgarak.tableReservation.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.damgarak.tableReservation.choiceTableReservation.model.dto.ChoiceTableReservationDTO;
import com.kh.damgarak.users.model.vo.Users;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TableReservationController {
	
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
	
	@GetMapping("/tableReservation")
	@ResponseBody
	public String tableReservation(HttpSession session, String date, String time, String tableNo){
		
		Users users = (Users)session.getAttribute("userLogin");
		HashMap<String, ChoiceTableReservationDTO> tableReservationDTO = new HashMap<>();
		
		
		
		
		return "";
	}
	
	
	
	
}
