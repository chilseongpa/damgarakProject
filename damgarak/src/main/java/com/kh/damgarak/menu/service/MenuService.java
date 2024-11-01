package com.kh.damgarak.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.damgarak.menu.menusearch.model.dto.MenuSearchDTO;
import com.kh.damgarak.menu.model.mapper.MenuMapper;
import com.kh.damgarak.menu.model.vo.Menu;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
	private final MenuMapper menuMapper;
	
	public List<Menu> lunchBoxTopMenu(){
		return menuMapper.lunchBoxTopMenu();
	}
	
	public List<Menu> restaurantTopMenu(){
		return menuMapper.restaurantTopMenu();
	}
	
	public List<Menu> getFilterMenu(List<String> categories, String suggestStatus) {
		
		  if (suggestStatus.equals("best")) {
	            return menuMapper.selectBestMenus(categories);
	        } else if (suggestStatus.equals("suggest")) {
	            return menuMapper.selectSuggest(categories);
	        } else {	        	
	        	return menuMapper.selectMenusCategories(categories);
	        }
	}

	public List<MenuSearchDTO> getRestaurantMenu(List<String> categories, String suggestStatus) {
	
		  if (suggestStatus.equals("best")) {
	            return menuMapper.selectRestaurantBestMenus(categories);
	        } else if (suggestStatus.equals("suggest")) {
	            return menuMapper.selectRestaurantSuggest(categories);
	        } else {	        	
	        	return menuMapper.selectMenuRestaurantCategories(categories);
	        }
	}
	

	
}
	


