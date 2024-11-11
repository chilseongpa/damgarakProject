package com.kh.damgarak.lunchBox.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.menu.model.vo.Menu;

@Mapper
public interface LunchBoxMapper {
    
    List<Menu> selectMenuByCategory(String categoryCode);
}
