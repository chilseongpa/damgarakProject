package com.kh.damgarak.menu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.damgarak.menu.model.vo.Menu;
import com.kh.damgarak.menu.service.MenuService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {
	private final MenuService mService;
	
	
	
	@GetMapping("/lunchMenu")
	public String lunchMenuPage(Menu menu, HttpSession session){
		
		List<Menu> list = mService.lunchBoxTopMenu(); 
		System.out.println("menuTop: " + list);
		if(list != null){
			session.setAttribute("menuTop", list);	
		}
		
		return"menu/lunchBoxMenu";
	}
	
	
	@GetMapping("/menuList")
	@ResponseBody
	public List<Menu> getMenuList(
			  @RequestParam(value = "selectMenu", required = false) List<String> categories,
	          @RequestParam(value = "suggestStatus", required = false) String suggestStatus)
			{
		List<Menu> menuList = mService.getFilterMenu(categories, suggestStatus);
				
		return menuList;
	}

}
