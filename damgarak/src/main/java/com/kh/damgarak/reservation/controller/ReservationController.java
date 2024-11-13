package com.kh.damgarak.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.damgarak.reservation.pastReservationSearch.model.dto.PastReservationSearch;
import com.kh.damgarak.reservation.service.ReservationService;
import com.kh.damgarak.users.model.vo.Users;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService reservationService;
	
	@GetMapping("/pastReservation")
	public String pastReservationPage(){
		return "reservation/reservation-inquiry/pastReservationPage";
	}
	
	@GetMapping("/pastReservationSearch")
	public String pastReservationSearch(
			@RequestParam("select-list-type") String searchType,
            @RequestParam("year") String year,
            @RequestParam("month") String month,
			Model model	,
			HttpSession session
			){
	
		HashMap<String, String> map = new HashMap<String, String>();
		
		UsersLoginDTO user = (UsersLoginDTO)session.getAttribute("userLogin");
	
		String usersId = user.getUsers().getUsersId();
	
		List<PastReservationSearch> searchList = new ArrayList();
		
		map.put("usersId", usersId);
		map.put("year", year);
		map.put("month", month);
		
		
		if(searchType.equals("식당")){
			searchList = reservationService.searTableList(map);
		}else {
			searchList = reservationService.searchLuchList(map);
		}
		
		model.addAttribute("sList",searchList);
		
		return "reservation/reservation-inquiry/pastReservationPage";
	}
	
}
