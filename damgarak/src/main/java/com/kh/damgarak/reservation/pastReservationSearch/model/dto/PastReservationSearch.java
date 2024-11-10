package com.kh.damgarak.reservation.pastReservationSearch.model.dto;

import lombok.Data;

@Data
public class PastReservationSearch {
    private int reservationNo;        
    private String reservationDate;   
    private String reservationTime;   
    private String lunchBoxDetails;   
    private String tableNo;           
}
