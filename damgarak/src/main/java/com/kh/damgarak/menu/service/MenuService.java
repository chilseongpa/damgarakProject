package com.kh.damgarak.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

	public List<Menu> getFilterMenu(List<String> categories, String suggestStatus) {
		
		  if ("best".equals(suggestStatus)) {
	            return menuMapper.selectBestMenus(categories);
	        } else if ("suggest".equals(suggestStatus)) {
	            return menuMapper.selectSuggest(categories);
	        } else {	        	
	        	return null;
	        }
	}
}
	


