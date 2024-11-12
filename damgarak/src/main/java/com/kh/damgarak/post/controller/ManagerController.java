package com.kh.damgarak.post.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.vo.Notice;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.model.vo.Reply;
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
    	List<SuggestionDTO> p = mService.selSuggest(post);
    	
    	log.info("post list size: {}",p.size());
    	
    	if (p.size() > 0) {
    		log.info("post[0]: {}", p.get(0));
    	}
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
	@GetMapping("/suggestDetail")
	public String showSuggestDetail(@RequestParam int postNo, Model model) {
	    // 게시글 조회
	    List<SuggestionDTO> postDetails = mService.selsugDetail(postNo);
	    if (!postDetails.isEmpty()) {
	        model.addAttribute("post", postDetails.get(0));
	    } else {
	        model.addAttribute("error", "해당 게시물을 찾을 수 없습니다.");
	    }
	    
	    // 댓글 리스트 조회
	    List<SuggestionDTO> replies = mService.selReply(postNo);
	    model.addAttribute("replist", replies); // 댓글 목록을 모델에 추가
	    
	    return "post/board/manager/suggestDetail";
	}
	@GetMapping("/empDetails")
	public String showempDetails(@RequestParam("usersId") String usersId, Model model) {
	    List<SuggestionDTO> userDetails = mService.selempDetails(usersId);

	    if (!userDetails.isEmpty()) {
	        model.addAttribute("user", userDetails.get(0));  // user 객체 추가
	    } else {
	        model.addAttribute("error", "해당 게시물을 찾을 수 없습니다.");
	    }

	    return "post/board/manager/empDetails";
	}
	@ResponseBody
	@PostMapping("/updateUser")
	public String updateUser(@RequestBody SuggestionDTO user) {
	    boolean isUpdated = mService.updateUserInfo(user.getUsersId(), user.getUsersName(), user.getEmail(), user.getJobCode());

	    if (isUpdated) {
	        return "ok";
	    } else {
	        return "fail";
	    }

	    // 직원 상세 정보 페이지로 리다이렉트
	    // return "redirect:/manager/empDetails?usersName=" + usersName;
	}
	@PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
	    String newPassword = request.get("newPassword");
	    boolean isUpdated = mService.updatePassword(newPassword);
	    if (isUpdated) {
	        return ResponseEntity.ok("Password updated successfully");
	    } else {
	        return ResponseEntity.status(500).body("Failed to update password");
	    }
	}
	@ResponseBody
	@PostMapping("/insertReply")
	public String insertReply(@RequestBody Reply reply) {
	    int result = mService.insertReply(reply);
	    return result > 0 ? "success" : "failed";
	}
	@ResponseBody
	@PostMapping("/insertNotice")
	public String insertNotice(@RequestBody Notice notice) {
		int result = mService.insertNotice(notice);
		return result > 0? "success" : "failed";
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
	@GetMapping("/specification")
	public String specificationPage() {
		return "post/board/manager/specification";
	}
	@GetMapping("/detailSpecification")
	public String detailSpecificationPage() {
		return "post/board/manager/detailSpecification";
	}
	
	
}