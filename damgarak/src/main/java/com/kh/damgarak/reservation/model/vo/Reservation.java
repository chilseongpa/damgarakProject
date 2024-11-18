package com.kh.damgarak.reservation.model.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Reservation {
	
	private String reservationNo;
	private String usersId;
	private Date reservationDate;
	private String reservationStatus;
}
