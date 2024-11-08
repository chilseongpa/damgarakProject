package com.kh.damgarak.post.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.service.ManagerService;
import com.kh.damgarak.users.model.vo.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
	
	private final ManagerService mService;

    @GetMapping("/suggestDe")
    public String adminPage(Model model,Post post) {
        // ManagerService를 통해 추천된 포스트 목록 가져오기
    	List<SuggestionDTO> p = mService.selSuggest(post);
    	
    	log.info("post list size: {}",p.size());
    	
    	if (p.size() > 0) {
    		log.info("post[0]: {}", p.get(0));
    	}
        // 모델에 가져온 포스트 목록 추가
        model.addAttribute("postList", p);

        return "post/board/manager/suggestDe";
    }
	@GetMapping("/empInfo")
	public String empInfoPage(Model model, Users user) {
		List<SuggestionDTO> u = mService.selEmp(user);
		
		log.info("post list size: {}",u.size());
    	
    	if (u.size() > 0) {
    		log.info("post[0]: {}", u.get(0));
    	}
		
		model.addAttribute("usersList",u);
		
		return "post/board/manager/empInfo";
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
	@GetMapping("/suggestDetail")
	public String suggestDetailPage() {
		return "post/board/manager/suggestDetail";
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
}