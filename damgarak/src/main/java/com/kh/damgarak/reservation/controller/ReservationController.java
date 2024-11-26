package com.kh.damgarak.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.damgarak.common.model.vo.PageInfo;
import com.kh.damgarak.common.template.Pagination;
import com.kh.damgarak.reservation.pastReservationSearch.model.dto.PastReservationSearch;
import com.kh.damgarak.reservation.service.ReservationService;
import com.kh.damgarak.tableReservation.selectReservationLunchBox.model.dto.ReservationLunchBoxDTO;
import com.kh.damgarak.tableReservation.selectReservationTable.model.dto.SelectReservationTableDTO;
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
			HttpSession session,
			@RequestParam(value = "page", defaultValue = "1")
			 int currentPage
			){
	
		HashMap<String, String> map = new HashMap<String, String>();
		
		UsersLoginDTO user = (UsersLoginDTO)session.getAttribute("userLogin");
	
		String usersId = user.getUsersId();
	
		List<PastReservationSearch> searchList = new ArrayList();
		
		map.put("usersId", usersId);
		map.put("year", year);
		map.put("month", month);
		
		int listCount = 0;
		
		if(!searchType.equals("도시락")){
			listCount = reservationService.getCountTable(usersId);
		}else {
			listCount = reservationService.getCountLunchBox(usersId);
		}
		
		int pageLimit = 10; 
		int boardLimit = 6;
		
		 PageInfo pageInfo = Pagination.getPageInfo(listCount, currentPage, 
				 pageLimit, boardLimit);
		
		   if (currentPage > pageInfo.getMaxPage()) {
		        currentPage = pageInfo.getMaxPage();
		        pageInfo = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		    }
		   
		if(searchType.equals("식당")){	
			searchList = reservationService.searTableList(map, pageInfo);
		}else {
			searchList = reservationService.searchLuchList(map, pageInfo);
		}
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("sList",searchList);
		
		return "reservation/reservation-inquiry/pastReservationPage";
	}
	
	@GetMapping("/lunchBoxInquiryPage")
	public String lunchBoxInquiryPage(HttpSession session,
			 @RequestParam(value = "page", defaultValue = "1")
			 int currentPage){
		
		UsersLoginDTO users = (UsersLoginDTO)session.getAttribute("userLogin");
		
		if(users == null) {
			return "redirect:/";
		}
		
		String userId = users.getUsersId();
		
		int listCount = reservationService.getLunchCount(userId);
		
		
		int pageLimit = 10; 
		int boardLimit = 6;
		
		 PageInfo pageInfo = Pagination.getPageInfo(listCount, currentPage, 
				 pageLimit, boardLimit);
		 
		 
		  if (currentPage > pageInfo.getMaxPage()) {
		        currentPage = pageInfo.getMaxPage();
		        pageInfo = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		    }
		
		  List<ReservationLunchBoxDTO> reservationList = reservationService.reservationInquiryLunchBox(userId, 
					pageInfo);
				
			session.setAttribute("pageInfo", pageInfo);
			if(reservationList != null){
				session.setAttribute("rList", reservationList);
				return "reservation/reservation-inquiry/lunchBoxInquiryPage";	
			}
			
			return "reservation/reservation-inquiry/lunchBoxInquiryPage";
		}
	
	
	
	
}
