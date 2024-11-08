package com.kh.damgarak.tableReservation.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.damgarak.tableReservation.choiceTableReservation.model.dto.ChoiceTableReservationDTO;
import com.kh.damgarak.tableReservation.searchTable.model.dto.SearchTableDTO;
import com.kh.damgarak.tableReservation.service.TableReservationService;
import com.kh.damgarak.users.model.vo.Users;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TableReservationController {
	
	private final TableReservationService tableReservationService;
	
	@GetMapping("/reservationTableTimeChoice")
	public String TableTimeChoicePage(){
		return "reservation/table-reservation/reservationTableTimeChoice";
	}
	@GetMapping("/reservation/table-reservation/tableChoicePage")
	public String reservationTableState(
			@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") 
			String date,
            @RequestParam("time") String time,
            Model model){
		
	     model.addAttribute("date", date);
	     model.addAttribute("time", time);
		return "/reservation/table-reservation/tableChoicePage";
	}
	@PostMapping(value = "/tableReservation")
	@ResponseBody
	public String tableReservation(
			HttpSession session,
					@RequestBody ChoiceTableReservationDTO choiceReservation
					){
		
		Users users = (Users)session.getAttribute("userLogin");
		if(users == null) {
			return "로그인이 필요합니다";
		}

		int result = tableReservationService.createTableReservation(choiceReservation, users.getUsersId());
	
		if(result > 0) {
			return "ok";
		}else 
		return "not";
	}
	
	@GetMapping("/getSearchTableState")
	@ResponseBody
	public List<String> getSearchTableState(@RequestBody SearchTableDTO searchDto){
		
		String reservationSearch = 
				searchDto.getData() 
							+ " " + 
								searchDto.getTime();
		
		List<String> reservation = tableReservationService.searchReservationTable(reservationSearch);
	
		return reservation;
	}
	
	
	
	
	
	
}
