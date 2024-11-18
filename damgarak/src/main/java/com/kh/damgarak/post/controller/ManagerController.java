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

import com.kh.damgarak.employee.model.vo.Employee;
import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.vo.Notice;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.model.vo.Reply;
import com.kh.damgarak.post.service.ManagerService;
import com.kh.damgarak.post.specification.model.dto.OrderDetailsDTO;
import com.kh.damgarak.reservation.model.vo.Reservation;
import com.kh.damgarak.users.model.vo.Users;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;

import jakarta.servlet.http.HttpSession;
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
	@ResponseBody
	@PostMapping("/insertReply")
	public String insertReply(@RequestBody Reply reply, HttpSession session) {
	    UsersLoginDTO userLogin = (UsersLoginDTO) session.getAttribute("userLogin");
	    
	    if (userLogin != null) {
	        reply.setUsersId(userLogin.getUsersId()); // 세션에서 usersId 설정
	        System.out.println("Retrieved usersId from session: " + userLogin.getUsersId());
	    } else {
	        System.out.println("UserLoginDTO not found in session");
	        return "failed"; // 세션에 userLogin 정보가 없는 경우 실패 처리
	    }
	    
	    int result = mService.insertReply(reply);
	    return result > 0 ? "success" : "failed";
	}
	@ResponseBody
	@PostMapping("/insertNotice")
	public String insertNotice(@RequestBody Notice notice, HttpSession session) {
	    UsersLoginDTO dto = (UsersLoginDTO) session.getAttribute("userLogin");

	    if (dto != null && dto.getUsersId() != null) {
	        String usersId = dto.getUsersId();
	        System.out.println("Logged in userId: " + usersId); // usersId 출력
	        notice.setUsersId(usersId);  // Notice 객체에 usersId 설정
	    } else {
	        System.out.println("User not logged in or invalid session."); // 로그 출력
	        return "failed: not logged in";  // 로그인되지 않은 경우 실패 메시지 반환
	    }

	    // Notice 객체를 서비스로 전달
	    int result = mService.insertNotice(notice);
	    
	    return result > 0 ? "success" : "failed";
	}

	@ResponseBody
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestBody Map<String, String> passwordData, HttpSession session) {
	    // 세션에서 userId 가져오기
	    String userId = (String) session.getAttribute("userId");
	    if (userId == null) {
	        return "fail";
	    }

	    // 클라이언트로부터 전달받은 변경할 비밀번호
	    String changePassword = passwordData.get("changePassword");

	    // 비밀번호 변경 서비스 호출
	    int result = mService.updatePass(userId, changePassword);

	    return result > 0 ? "success" : "fail";
	}
	@GetMapping("/fireEmployee")
	public String fireEmployee(String usersId) {
		System.out.println("유저 아이디 확인용" + usersId);
		
	    int result = mService.updateFire(usersId);
	    
	    
	    return result > 0 ? "success" : "fail";
	}
	@GetMapping("/rv")
	public String rvPage(Model model, Reservation reservation) {
	    List<SuggestionDTO> r = mService.selRv(reservation);
	    model.addAttribute("reserList", r);
	    return "post/board/manager/rv";
	}
	@GetMapping("/bentoRv")
	public String bentoRvPage(Model model, Reservation reservation) {
		List<SuggestionDTO> br = mService.selbentoRv(reservation);
		model.addAttribute("bentoList",br);
		return "post/board/manager/bentoRv";
	}
	@GetMapping("/saleSheet")
	public String salePage() {
		return "post/board/manager/saleSheet";
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
	@GetMapping("/filterOrders")
	@ResponseBody
	public List<OrderDetailsDTO> filterOrdersByDate(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return mService.getOrdersWithinDateRange(startDate, endDate);
	}


}
