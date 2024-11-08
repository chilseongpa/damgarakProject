package com.kh.damgarak.tableReservation.choiceTableReservation.model.dto;
import com.kh.damgarak.reservation.model.vo.Reservation;
import com.kh.damgarak.tableReservation.model.vo.TableReservation;
import com.kh.damgarak.users.model.vo.Users;

import lombok.Data;

@Data
public class ChoiceTableReservationDTO {
	
	private TableReservation tableReservation;
	private Reservation reservation;

}
