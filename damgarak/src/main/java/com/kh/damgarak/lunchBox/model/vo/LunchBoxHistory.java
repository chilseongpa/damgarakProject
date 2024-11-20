package com.kh.damgarak.lunchBox.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LunchBoxHistory {
	private int reservationNo;
	private String usersId;
	private Date reservationDate;
	private String reservationStatus;
}
