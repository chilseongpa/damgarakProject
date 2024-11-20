package com.kh.damgarak.payment.model;

import lombok.Data;

@Data
public class Payment {

    private int paymentNo;
    private int reservationNo;
    private int totalPrice;
    private String paymentDate;
    private String paymentMethod;
    
}
