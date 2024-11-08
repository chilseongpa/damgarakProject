package com.kh.damgarak.post.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.service.ManagerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
	
	private final ManagerService mService;

    @GetMapping("/suggestDe")
    public String adminPage(Model model) {
        // ManagerService를 통해 추천된 포스트 목록 가져오기

        // 모델에 가져온 포스트 목록 추가
        model.addAttribute("post", "post");

        return "post/board/manager/suggestDe";
    }

	@GetMapping("/saleSheet")
	public String salePage() {
		return "post/board/manager/saleSheet";
	}
	@GetMapping("/bentoRv")
	public String bentoRvPage() {
		return "post/board/manager/bentoRv";
	}
	@GetMapping("/rv")
	public String rvPage() {
		return "post/board/manager/rv";
	}
	@GetMapping("/recommend")
	public String recommendPage() {
		return "post/board/manager/recommend";
	}
	@GetMapping("/empInfo")
	public String empInfoPage() {
		return "post/board/manager/empInfo";
	}
	@GetMapping("/notice")
	public String noticePage() {
		return "post/board/manager/notice";
	}
	@GetMapping("/infoChange")
	public String InfoChangePage() {
		return "post/board/manager/infoChange";
	}
	@GetMapping("/noticeWrite")
	public String noticeWritePage() {
		return "post/board/manager/noticeWrite";
	}
	@GetMapping("/pass")
	public String passPage() {
		return "post/board/manager/pass";
	}
	@GetMapping("/jgInfo")
	public String jgInfoPage() {
		return "post/board/manager/jgInfo";
	}
	@GetMapping("/mySuggest")
	public String mySuggestPage() {
		return "post/board/emp/mySuggest";
	}
	
	
}