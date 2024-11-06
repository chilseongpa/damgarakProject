package com.kh.damgarak.post.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
public class PostController {
	
	@Autowired
	private final PostService pService;
	
	public PostController(PostService pService) {
		this.pService = pService;
	}
	
	@GetMapping("/notice")
	public String noticePage(HttpSession session) {
		List<Post> list = pService.noticeMenu();
		if(list != null) {
			session.setAttribute("post",list);
		}
		
		return "post/notice";
	}
	
	@PostMapping("/updatePost")
    public String updatePost(
        @RequestParam("postNo") int postNo,
        @RequestParam("title") String title,
        @RequestParam("content") String content) {

        pService.updatePost(postNo, title, content);
        return "redirect:/post/notice"; // 업데이트 후 리다이렉트할 페이지 경로 설정
    }
	
	@PostMapping("/list")
	public String boardList(@RequestParam(value="cpage", defaultValue="1") int currentPage
							, Model model) {
		
		// 전체 게시글 수 조회
		int listCount = pService.selectListCount();
		return "post/postList";
	}
}