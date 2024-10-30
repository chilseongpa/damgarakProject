package com.kh.damgarak.board.controller.boardController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kh.damgarak.board.service.boardService.BoardService;
import com.kh.damgarak.emailauth.service.EmailAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class boardController {
	private final BoardService boardService; 
	
	@GetMapping("/c2")
	public String showCi2Page() {
		return "ci2"; 
	}
	@GetMapping("/c3")
	public String showCi3Page() {
		return "ci3";
	}
	@GetMapping("/c4")
	public String showCi4Page() {
		return "ci4";
	}
	@GetMapping("/c5")
	public String showCi5Page() {
		return "ci5";
	}
	@GetMapping("/c6")
	public String showCi6Page() {
		return "ci6";
	}
	@GetMapping("/c7")
	public String showCi7Page() {
		return "ci7";
	}
}
