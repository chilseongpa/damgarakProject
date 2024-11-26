package com.kh.damgarak.lunchboxyeongsujeung.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunchBoxyeongsujeungController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/generateOrderId")
    @ResponseBody
    public String generateOrderId() {
        String sql = "SELECT RESERVATION_SEQ.NEXTVAL FROM dual";  // 시퀀스에서 다음 값 가져오기
        Long orderId = jdbcTemplate.queryForObject(sql, Long.class);
        return String.valueOf(orderId);
    }
}
