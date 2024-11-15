// PaymentService.java
package com.kh.damgarak.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.damgarak.payment.model.mapper.PaymentMapper;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

}