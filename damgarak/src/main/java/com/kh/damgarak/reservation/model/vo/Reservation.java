package com.kh.damgarak.reservation.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {

	private String reservationNo;
	private String usersId;
	private Date reservationDate;
	private String reservationStatus;
}
