package com.kh.damgarak.tableReservation.selectReservationTable.model.dto;

import lombok.Data;

@Data
public class SelectReservationTableDTO {
	private int reservationNo;
	private String usersId;
	private String reservationDate;
	private String reservationStatus;
	private String tableNo;
}
