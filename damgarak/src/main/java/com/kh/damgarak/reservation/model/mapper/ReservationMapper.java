package com.kh.damgarak.reservation.model.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.reservation.pastReservationSearch.model.dto.PastReservationSearch;
import com.kh.damgarak.tableReservation.selectReservationLunchBox.model.dto.ReservationLunchBoxDTO;
@Mapper
public interface ReservationMapper {
	List<PastReservationSearch> searTableList(HashMap<String, String> map, int offset, int limit);
	List<PastReservationSearch> searchLuchList(HashMap<String, String> map, int offset, int limit);
	int getCountTable(String usersId);
	int getCountLunchBox(String usersId);
	int getLunchCount(String userId);
	List<ReservationLunchBoxDTO> reservationInquiryLunchBox(String userId, int offset, int limit);
}

