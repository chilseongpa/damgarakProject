package com.kh.damgarak.tableReservation.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

import com.kh.damgarak.tableReservation.model.vo.TableReservation;

@Mapper
public interface TableReservationMapper {
	
	int createReservation(HashMap<String, Object> map);
	int createTableReservation(TableReservation tableReservation);
	List<String> searchReservationTable(String reservationSearch);
	
	
	
	
	

}
