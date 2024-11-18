package com.kh.damgarak.payment.dto;

import java.util.Date;
import java.util.List;


import lombok.Data;

@Data
public class PaymentDto {
    private String paymentKey;
    private String orderId;
    private int amount;
    private String paymentMethod;
    private Date paymentDate;
    
  
}
