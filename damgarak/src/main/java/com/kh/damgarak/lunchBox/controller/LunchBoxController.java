package com.kh.damgarak.lunchBox.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.damgarak.lunchBox.model.dto.LunchBoxMenuList;
import com.kh.damgarak.lunchBox.service.LunchBoxService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LunchBoxController {

    private final LunchBoxService  lunchBoxService;

    public LunchBoxController(LunchBoxService lunchBoxService) {
        this.lunchBoxService = lunchBoxService;
    }
    
    

    @GetMapping("/lunchBoxMenu")
    public String lunchBoxMenuPage() {
        // 경로를 "lunch-Box/lunchBoxMenu"로 변경
        return "lunch-box/lunchBoxMenu";
        
    }
    
    @GetMapping("/choiceLunchBox")
    public String choiceLunchBoxPage() {
        return "lunch-box/choiceLunchBox";  // 이 경로에 해당하는 템플릿 파일이 존재해야 합니다.
    }
    
    
    // -------------------------------------
    @ResponseBody
    @GetMapping(value="/lunchBoxMenuList", produces="application/json;charset=utf-8")
    public String lunchBoxMenuList() {
        LunchBoxMenuList allMenuList = new LunchBoxMenuList();
        allMenuList.setMain(lunchBoxService.getMenuByCategory("01"));
        allMenuList.setSoup(lunchBoxService.getMenuByCategory("02"));
        allMenuList.setSide( lunchBoxService.getMenuByCategory("06"));
        
        return new Gson().toJson(allMenuList);
    }
}
