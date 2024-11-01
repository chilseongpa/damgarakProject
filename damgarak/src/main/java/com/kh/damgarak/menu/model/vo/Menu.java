package com.kh.damgarak.menu.model.vo;

import lombok.Data;

@Data
public class Menu {
	
	private int menuNo;
	private String menuName;
	private int price;
	private String menuType;
	private String menuImage;
	private String menuDescription;
	private int calorie;
	private String categoryCode;
	private int menuCount;
}
