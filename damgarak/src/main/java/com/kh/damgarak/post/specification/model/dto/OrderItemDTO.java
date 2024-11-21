package com.kh.damgarak.post.specification.model.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
	private String menuName;
    private int menuCount;
    private String paymentMethod;
    private int price;
}
