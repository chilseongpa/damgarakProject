package com.kh.damgarak.menu.menusearch.model.dto;

import com.kh.damgarak.menu.model.vo.Menu;
import com.kh.damgarak.menucategory.model.vo.MenuCategory;
import com.kh.damgarak.suggestmenu.model.vo.SuggestMenu;

import lombok.Data;

@Data
public class MenuSearchDTO {
	
    private Menu menu;
    private MenuCategory menuCategory;
    private SuggestMenu suggestMenu;
    
 
}
