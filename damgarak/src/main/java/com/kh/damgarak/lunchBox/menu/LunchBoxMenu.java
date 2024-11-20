package com.kh.damgarak.lunchBox.menu;

import java.sql.Date;

import lombok.Data;

@Data
public class LunchBoxMenu {
	
	private  int reservationNo;
	private  String usersId;
	private  Date reservationDate;
	private  String lunchboxDetails;
	
}
