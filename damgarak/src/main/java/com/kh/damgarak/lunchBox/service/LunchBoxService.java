package com.kh.damgarak.lunchBox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.damgarak.lunchBox.mapper.LunchBoxMapper;
import com.kh.damgarak.menu.model.vo.Menu;

@Service
public class LunchBoxService {

    private final LunchBoxMapper lunchBoxMapper;

    @Autowired
    public LunchBoxService(LunchBoxMapper lunchBoxMapper) {
        this.lunchBoxMapper = lunchBoxMapper;
    }

    // 카테고리별 메뉴 가져오기
    public List<Menu> getMenuByCategory(String categoryCode) {
        return lunchBoxMapper.selectMenuByCategory(categoryCode);
    }
}
