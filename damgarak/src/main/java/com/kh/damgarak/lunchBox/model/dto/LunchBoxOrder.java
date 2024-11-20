package com.kh.damgarak.lunchBox.model.dto;

import java.util.List;

import com.kh.damgarak.menu.model.vo.Menu;

import lombok.Data;

@Data
public class LunchBoxOrder {
	private String userId;

	// ORDER_DETAILS
	private List<Integer> menuList;
	
	// LUNCH_BOX
	private String lunchBoxType;
	private int amount;
}
