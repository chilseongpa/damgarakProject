package com.kh.damgarak.lunchBox.model.dto;

import java.util.List;

import com.kh.damgarak.menu.model.vo.Menu;

import lombok.Data;

@Data
public class LunchBoxMenuList {
	private List<Menu> main;
	private List<Menu> soup;
	private List<Menu> side;
}
