package com.kh.damgarak.lunchBox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.damgarak.lunchBox.model.dto.LunchBoxMenuList;
import com.kh.damgarak.lunchBox.model.dto.LunchBoxOrder;
import com.kh.damgarak.lunchBox.service.LunchBoxService;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LunchBoxController {

    private final LunchBoxService lunchBoxService;

    public LunchBoxController(LunchBoxService lunchBoxService) {
        this.lunchBoxService = lunchBoxService;
    }

    @GetMapping("/lunchBoxMenu")
    public String lunchBoxMenuPage() {
        return "lunch-box/lunchBoxMenu";
    }

    @GetMapping("/choiceLunchBox")
    public String choiceLunchBoxPage() {
        return "lunch-box/choiceLunchBox";
    }

    @GetMapping("/LunchBoxReservation")
    public String lunchBoxReservationPage() {
        return "lunch-box/LunchBoxReservation";
    }

    @ResponseBody
    @GetMapping(value = "/lunchBoxMenuList", produces = "application/json;charset=utf-8")
    public String lunchBoxMenuList() {
        LunchBoxMenuList allMenuList = new LunchBoxMenuList();
        allMenuList.setMain(lunchBoxService.getMenuByCategory("01"));
        allMenuList.setSoup(lunchBoxService.getMenuByCategory("02"));
        allMenuList.setSide(lunchBoxService.getMenuByCategory("06"));
        return new Gson().toJson(allMenuList);
    }

    @PostMapping("/insertLunchBox")
    @ResponseBody
    public String insertLunchBox(@RequestBody LunchBoxOrder lunchBoxOrder
    							, HttpSession session) {
        try {
            log.info("Inserting lunchbox reservation: {}", lunchBoxOrder);
            UsersLoginDTO user = (UsersLoginDTO)session.getAttribute("userLogin");
            
            log.info("{}",user);
            
            lunchBoxOrder.setUserId(user.getUsersId());
            // Service method to insert lunchbox data
            log.info("----");
            int result = lunchBoxService.addLunchBoxOrder(lunchBoxOrder);
            if (result > 0) {
            	return "success";
            } else {
            	return "failed";
            }
        } catch (Exception e) {
            log.error("Error while inserting lunchbox reservation: {}", e.getMessage());
            return "Reservation failed: " + e.getMessage();
        }
    }
}
