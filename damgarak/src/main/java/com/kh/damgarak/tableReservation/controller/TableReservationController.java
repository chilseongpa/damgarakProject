package com.kh.damgarak.tableReservation.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.damgarak.common.model.vo.PageInfo;
import com.kh.damgarak.common.template.Pagination;
import com.kh.damgarak.tableReservation.choiceTableReservation.model.dto.ChoiceTableReservationDTO;
import com.kh.damgarak.tableReservation.searchTable.model.dto.SearchTableDTO;
import com.kh.damgarak.tableReservation.selectReservationTable.model.dto.SelectReservationTableDTO;
import com.kh.damgarak.tableReservation.service.TableReservationService;
import com.kh.damgarak.users.model.vo.Users;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;

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
	
	@GetMapping("/updateReservation")
	public String updateTableReservation(
			@RequestParam("reservationNo")
			int reservationNo,
			Model model){
	
		model.addAttribute("reservationNo", reservationNo);
		
		return "/reservation/table-reservation/reservationTableTimeChoice";
	}
	@PostMapping(value = "/tableReservation")
	@ResponseBody
	public String tableReservation(
			HttpSession session,
					@RequestBody ChoiceTableReservationDTO choiceReservation
					){
		
		UsersLoginDTO users = (UsersLoginDTO)session.getAttribute("userLogin");
	
		
		if(users == null) {
			return "로그인이 필요합니다";
		}

		int result = tableReservationService.createTableReservation(choiceReservation, users.getUsers().getUsersId());
	
		if(result > 0) {
			return "ok";
		}else 
		return "not";
	}
	
	@PutMapping("/updateTable")
	@ResponseBody
	public String updateTableReservation(
			@RequestBody ChoiceTableReservationDTO choiceReservation,
			HttpSession session
			){
		UsersLoginDTO user = (UsersLoginDTO)session.getAttribute("userLogin"); 
		
		if(user == null){
			return "로그인이 필요합니다.";
		}
		
		String reservationNo = choiceReservation.getReservation().getReservationNo(); 
		if(reservationNo == null){
			return "수정할 수 없습니다.";
		}
		
		int result = tableReservationService.updateTableReservation(choiceReservation, reservationNo);
		
		if(result > 0) {
			return "ok";
		}else {
			return "filed"; 
		}
		
	}
	
	
	@GetMapping("/getSearchTableState")
	@ResponseBody
	public List<String> getSearchTableState(@ModelAttribute SearchTableDTO searchDto){
		
		String reservationSearch = 
				searchDto.getDate() 
							+ " " + 
								searchDto.getTime();
		
		List<String> reservation = tableReservationService.searchReservationTable(reservationSearch);
		return reservation;
	}
	
	
	@GetMapping("/reservationInquiry")
	public String reservationInquiryPage(HttpSession session,
			 @RequestParam(value = "page", defaultValue = "1")
			 int currentPage) 
			{
	
		UsersLoginDTO users = (UsersLoginDTO)session.getAttribute("userLogin");
	
		if(users == null) {
			return "redirect:/";
		}
	
		String userId = users.getUsers().getUsersId();
		
		int listCount = tableReservationService.getReservationCount(userId);
		
		int pageLimit = 10; 
		int boardLimit = 6;
		
		 PageInfo pageInfo = Pagination.getPageInfo(listCount, currentPage, 
				 pageLimit, boardLimit);
		 
		 
		    if (currentPage > pageInfo.getMaxPage()) {
		        currentPage = pageInfo.getMaxPage();
		        pageInfo = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		    }
		 
		
		List<SelectReservationTableDTO> reservationList = tableReservationService.reservationInquiry(userId, 
				pageInfo);
			
		session.setAttribute("pageInfo", pageInfo);
		if(reservationList != null){
			session.setAttribute("rList", reservationList);
			return "reservation/reservation-inquiry/reservationInquiryPage";	
		}
		return "reservation/reservation-inquiry/reservationInquiryPage";
	}
	
	@PostMapping("/cancelReservation")
	@ResponseBody
	public String reservationCancel(int reservationNo){
		
		
		int tresult = tableReservationService.deleteTableReservation(reservationNo);
		int result = tableReservationService.deleteReservation(reservationNo);
		
		if(result <= 0 || tresult <= 0) {
			return "no";
		}
		return "ok"; 
	}
	
	
	
}
