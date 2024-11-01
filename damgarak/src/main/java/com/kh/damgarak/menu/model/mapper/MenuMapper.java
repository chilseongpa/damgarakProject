package com.kh.damgarak.menu.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.menu.model.vo.Menu;
import com.kh.damgarak.menusearch.model.dto.MenuSearchDTO;

@Mapper
public interface MenuMapper {
	 	
		List<Menu> lunchBoxTopMenu();
		List<Menu> restaurantTopMenu();
		
		List<Menu> selectBestMenus(List<String> categories);
		List<Menu> selectSuggest(List<String> categories);
		List<Menu> selectMenusCategories(List<String> categories);
		
		
		List<MenuSearchDTO>selectRestaurantBestMenus(List<String> categories);
		List<MenuSearchDTO>selectRestaurantSuggest(List<String> categories);
		List<MenuSearchDTO>selectMenuRestaurantCategories(List<String> categories);
	
		
}
