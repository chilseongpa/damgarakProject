package com.kh.damgarak.reservation.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.damgarak.common.model.vo.PageInfo;
import com.kh.damgarak.reservation.model.mapper.ReservationMapper;
import com.kh.damgarak.reservation.pastReservationSearch.model.dto.PastReservationSearch;
import com.kh.damgarak.tableReservation.selectReservationLunchBox.model.dto.ReservationLunchBoxDTO;
import com.kh.damgarak.tableReservation.selectReservationTable.model.dto.SelectReservationTableDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	private final ReservationMapper mapper; 
	public List<PastReservationSearch> searTableList(HashMap<String, String> map, PageInfo pi) {
		 int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit(); 
		 int limit = pi.getBoardLimit();
		return mapper.searTableList(map, offset, limit);
	}
	public List<PastReservationSearch> searchLuchList(HashMap<String, String> map, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit(); 
		 int limit = pi.getBoardLimit();
		return mapper.searchLuchList(map, offset, limit);
	}
	public int getCountTable(String usersId) {
		return mapper.getCountTable(usersId);
	}
	public int getCountLunchBox(String usersId) {
		return mapper.getCountLunchBox(usersId);
	}
	public int getLunchCount(String userId) {
		
		return mapper.getLunchCount(userId);
	}
	public List<ReservationLunchBoxDTO> reservationInquiryLunchBox(String userId, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit(); 
		 int limit = pi.getBoardLimit();
		return mapper.reservationInquiryLunchBox(userId, offset, limit);
	}

}
