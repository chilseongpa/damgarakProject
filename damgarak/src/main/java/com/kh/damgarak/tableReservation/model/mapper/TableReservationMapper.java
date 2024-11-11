package com.kh.damgarak.tableReservation.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

import com.kh.damgarak.tableReservation.model.vo.TableReservation;
import com.kh.damgarak.tableReservation.selectReservationTable.model.dto.SelectReservationTableDTO;

@Mapper
public interface TableReservationMapper {
	
	int createReservation(HashMap<String, Object> map);
	int createTableReservation(TableReservation tableReservation);
	List<String> searchReservationTable(String reservationSearch);
	List<SelectReservationTableDTO> reservationInquiry(String userId, int offset, int limit);
	int getReservationCount(String userId);
	int deleteResultvation(int reservationNo);
	int deleteTableReservation(int reservationNo);
	int updateReservation(HashMap<String, Object> map);
	int updateTableReservation(HashMap<String, Object> tableMap);
}

