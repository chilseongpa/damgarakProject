package com.kh.damgarak.reservation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
//	private int reservationNo;
//	private String usersId;
//	private String reservationDate;
//	private String reservationStatus;

	// String
	private String reservationNo;
	
	private String reservationDate;
	private String reservationStatus;
}
