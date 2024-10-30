package com.kh.damgarak.menu.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.menu.model.vo.Menu;

@Mapper
public interface MenuMapper {
	 
		List<Menu> lunchBoxTopMenu();
		
		List<Menu> selectBestMenus(List<String> categories);
		List<Menu> selectSuggest(List<String> categories);
		List<Menu> selectMenusCategories(List<String> categories);
		
}
