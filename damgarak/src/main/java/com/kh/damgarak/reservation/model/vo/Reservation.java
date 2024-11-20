package com.kh.damgarak.reservation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	private String reservationNo;
	private String usersId;
	private String reservationDate;
	private String reservationStatus;
}
