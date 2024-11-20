package com.kh.damgarak.reservation.model.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	private int reservationNo;
	private String usersId;	
	private String reservationDate;
=======
public class Reservation {
	
	private String reservationNo;
	private String usersId;
	private Date reservationDate;
>>>>>>> damgarak
	private String reservationStatus;
}
