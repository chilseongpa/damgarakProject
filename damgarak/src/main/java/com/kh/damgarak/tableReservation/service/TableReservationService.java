package com.kh.damgarak.tableReservation.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import com.kh.damgarak.tableReservation.choiceTableReservation.model.dto.ChoiceTableReservationDTO;
import com.kh.damgarak.tableReservation.model.mapper.TableReservationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableReservationService {
		
	private final TableReservationMapper mapper;
	
	public int createTableReservation(ChoiceTableReservationDTO choiceReservation, String userId) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("usersId", userId);
		map.put("reservationDate",choiceReservation.getReservation().getReservationDate());
		map.put("reservationStatus",choiceReservation.getReservation().getReservationStatus());
		
		int rResult = mapper.createReservation(map);	
		int tResult = mapper.createTableReservation(choiceReservation.getTableReservation());
		
		 if(rResult > 0 && tResult > 0) {
			 return 1;
		 }else {
			 return 0;			 
		 }
	}

	public List<String> searchReservationTable(String reservationSearch) {
			
		return mapper.searchReservationTable(reservationSearch);
	}
	
	

	
	
}
