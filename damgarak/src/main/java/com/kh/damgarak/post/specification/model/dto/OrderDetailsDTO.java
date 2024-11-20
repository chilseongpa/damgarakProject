package com.kh.damgarak.post.specification.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetailsDTO {
	private int orderNo;
    private String usersName;
    private String memberLevel;
    private String orderDate;
    private List<OrderItemDTO> items;
	
}
