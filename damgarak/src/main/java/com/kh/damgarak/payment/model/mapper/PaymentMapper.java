package com.kh.damgarak.payment.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.payment.model.Payment;

@Mapper
public interface PaymentMapper {
    void insertPayment(Payment payment); 
}