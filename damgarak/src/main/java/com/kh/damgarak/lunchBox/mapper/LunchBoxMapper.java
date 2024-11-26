package com.kh.damgarak.lunchBox.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.lunchBox.model.vo.LunchBoxHistory;
import com.kh.damgarak.menu.model.vo.Menu;
import com.kh.damgarak.order.model.vo.OrderHistory;
import com.kh.damgarak.reservation.model.vo.Reservation;

@Mapper
public interface LunchBoxMapper {
    
    List<Menu> selectMenuByCategory(String categoryCode);

    
    int insertReservation(Reservation resv);
    int insertOrderHistory(OrderHistory oh);
    int insertOrderDetails(int orderHistoryNo, int menuNo);
    int insertLunchBoxReservation(int reservationNo, int amount, String lunchBoxType);


	void insertReservation(LunchBoxHistory resv);
        
}



