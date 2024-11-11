package com.kh.damgarak.reservation.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.damgarak.reservation.model.mapper.ReservationMapper;
import com.kh.damgarak.reservation.pastReservationSearch.model.dto.PastReservationSearch;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	private final ReservationMapper mapper; 
	public List<PastReservationSearch> searTableList(HashMap<String, String> map) {
		return mapper.searTableList(map);
	}
	public List<PastReservationSearch> searchLuchList(HashMap<String, String> map) {
		return mapper.searchLuchList(map);
	}

}
