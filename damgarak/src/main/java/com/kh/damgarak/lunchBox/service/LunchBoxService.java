package com.kh.damgarak.lunchBox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.damgarak.lunchBox.mapper.LunchBoxMapper;
import com.kh.damgarak.lunchBox.model.dto.LunchBoxOrder;
import com.kh.damgarak.lunchBox.model.vo.LunchBoxHistory;
import com.kh.damgarak.menu.model.vo.Menu;
import com.kh.damgarak.order.model.vo.OrderHistory;
import com.kh.damgarak.reservation.model.vo.Reservation;

@Service
public class LunchBoxService {

    private final LunchBoxMapper lunchBoxMapper;

    @Autowired
    public LunchBoxService(LunchBoxMapper lunchBoxMapper) {
        this.lunchBoxMapper = lunchBoxMapper;
    }

    // 카테고리별 메뉴 가져오기
    public List<Menu> getMenuByCategory(String categoryCode) {
        return lunchBoxMapper.selectMenuByCategory(categoryCode);
    }
    
    // DML(insert/update/delete) -> int
    public int addLunchBoxOrder(LunchBoxOrder lunchBoxOrder) {
    	// 예약 저장
    	LunchBoxHistory resv = new LunchBoxHistory();
    	resv.setUsersId(lunchBoxOrder.getUserId());
    	lunchBoxMapper.insertReservation(resv);
    	
    	System.out.println(resv);
    	OrderHistory oh = new OrderHistory();
 
    	oh.setReservationNo(resv.getReservationNo());  
    	lunchBoxMapper.insertOrderHistory(oh);
    	
    	List<Integer> list = lunchBoxOrder.getMenuList();
    	for(Integer menu : list) {
    		lunchBoxMapper.insertOrderDetails(oh.getOrderNo(), menu);
    	}
    	
    	// 도시락 예약 저장
    	lunchBoxMapper.insertLunchBoxReservation(lunchBoxOrder.getAmount(), lunchBoxOrder.getLunchBoxType());
    
    	return 1;
    }

}
