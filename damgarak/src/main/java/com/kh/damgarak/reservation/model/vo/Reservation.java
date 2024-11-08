package com.kh.damgarak.reservation.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
	private String reservationNumber;
	private String usersId;
	private Date reservationDate;
	private String reservationStatus;
}
