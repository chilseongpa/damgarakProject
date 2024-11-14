package com.kh.damgarak.post.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.damgarak.employee.model.vo.Employee;
import com.kh.damgarak.employee.serchEmployee.model.dto.SerchEmployeeDto;
import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.vo.Notice;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.service.ManagerService;
import com.kh.damgarak.post.service.PostService;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/emp")
public class PostController {
	
	private final PostService pService;

	
	@GetMapping("/myPage")
	public String empMyPage(HttpSession session, Model model) {
		UsersLoginDTO dto = (UsersLoginDTO)session.getAttribute("userLogin");
		String usersId= dto.getUsersId();
		SerchEmployeeDto emp = pService.empInfomation(usersId);
		
		if(emp != null) {
			model.addAttribute("emp", emp);
			return "post/board/emp/empMyPage";
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/notice")
	public String noticePage(Model model, Notice notice) {
		List<SuggestionDTO> n = pService.selNotice(notice);

		

	
	   @GetMapping("/myPage")
	   public String empMyPage() {
	      return "post/board/emp/empMyPage";
	   }
	   
	   @GetMapping("/notice")
	   public String noticePage(Model model, Notice notice) {
	      List<SuggestionDTO> n = pService.selNotice(notice);
	      
	       model.addAttribute("noticeList",n);
	      
	      return "post/board/emp/notice";
	   }
	
	
	@GetMapping("/noticeDetail")
	public String suggestDetailPage(@RequestParam("noticeNo") int noticeNo, Model model) {
	    List<SuggestionDTO> postDetails = pService.selNoticeDetail(noticeNo);
	    if (!postDetails.isEmpty()) {
	        model.addAttribute("post", postDetails.get(0));
	    } else {
	        model.addAttribute("error", "해당 게시물을 찾을 수 없습니다.");
	    }
	    return "post/board/emp/noticeDetail"; // 경로 수정
	}

	@GetMapping("/mySuggest")
	public String mySuggestPage(HttpSession session, Model model) {
	    String userId = (String) session.getAttribute("userId"); // 세션에서 사용자 ID 가져오기
	    List<SuggestionDTO> suggestions = pService.getUserSuggestions(userId); // 서비스 메서드 호출
	    
	    model.addAttribute("suggestions", suggestions); // 건의 목록을 모델에 추가
	    return "post/board/emp/mySuggest";
	}

}