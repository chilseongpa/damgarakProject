package com.kh.damgarak.reservation.model.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.reservation.pastReservationSearch.model.dto.PastReservationSearch;
@Mapper
public interface ReservationMapper {
	List<PastReservationSearch> searTableList(HashMap<String, String> map, int offset, int limit);
	List<PastReservationSearch> searchLuchList(HashMap<String, String> map, int offset, int limit);
	int getCountTable(String usersId);
	int getCountLunchBox(String usersId);
}

