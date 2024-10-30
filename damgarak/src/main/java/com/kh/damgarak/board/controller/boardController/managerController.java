package com.kh.damgarak.board.controller.boardController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kh.damgarak.board.service.boardService.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class managerController {
	@GetMapping("/ui2")
	public String showUi2Page() {
		return "ui2";
	}
	@GetMapping("/ui3")
	public String showUi3Page() {
		return "ui3";
	}
	@GetMapping("/ui4")
	public String showUi4Page() {
		return "ui4";
	}
	@GetMapping("/ui5")
	public String showUi5Page() {
		return "ui5";
	}
	@GetMapping("/ui6")
	public String showUi6Page() {
		return "ui6";
	}
	@GetMapping("/ui7")
	public String showUi7Page() {
		return "ui7";
	}
	@GetMapping("/ui8")
	public String showUi8Page() {
		return "ui8";
	}
	@GetMapping("/ui9")
	public String showUi9Page() {
		return "ui9";
	}
	@GetMapping("/ui10")
	public String showUi10Page() {
		return "ui10";
	}
	@GetMapping("/ui11")
	public String showUi11Page() {
		return "ui11";
	}
}
