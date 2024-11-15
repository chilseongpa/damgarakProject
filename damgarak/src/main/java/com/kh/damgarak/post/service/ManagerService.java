package com.kh.damgarak.post.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.mapper.ManagerMapper;
import com.kh.damgarak.employee.model.vo.Employee;
import com.kh.damgarak.post.model.vo.Notice;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.model.vo.Reply;
import com.kh.damgarak.post.specification.model.dto.OrderDetailsDTO;
import com.kh.damgarak.users.model.vo.Users;

@Service
public class ManagerService {

    private final ManagerMapper managerMapper;

    @Autowired
    public ManagerService(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

	public List<SuggestionDTO> selSuggest(Post post) {
		return managerMapper.selSuggest(post);
	}
	public List<SuggestionDTO> selEmp(Users user){
		return managerMapper.selEmp(user);
	}
	
	public List<Post> insertPost(Post post){
		return managerMapper.insertPost(post);
	}
	
	public List<SuggestionDTO> selsugDetail(int postNo) {
	    return managerMapper.selsugDetail(postNo);
	}
	public List<SuggestionDTO> selempDetails(String usersId){
		return managerMapper.selempDetails(usersId);
	}
	@Transactional // 두 가지 업데이트가 모두 성공해야 커밋됩니다.
	public boolean updateUserInfo(String usersId, String usersName, String email, String jobCode) {
		Users u = new Users();
		u.setUsersId(usersId); u.setEmail(email); u.setUsersName(usersName);
	    int updateUserResult = managerMapper.updateNameEmail(u);
	    
	    Employee e = new Employee();
	    e.setJobCode(jobCode); e.setUsersId(usersId);	    
	    int updateJobResult = managerMapper.updateJob(e);

	    
	    System.out.println("-".repeat(30));
	    System.out.println(updateUserResult);	    
	    System.out.println(updateJobResult);	    
	    System.out.println("-".repeat(30));
	    // 두 쿼리가 모두 성공적으로 업데이트된 경우에만 true 반환
	    return updateUserResult > 0 && updateJobResult > 0;
	}
	public List<SuggestionDTO> selReply(int postNo){
		return managerMapper.selReply(postNo);
	}
    public int insertReply(Reply reply) {
        return managerMapper.insertReply(reply); // 성공 시 1, 실패 시 0 반환
    }
    public int insertNotice(Notice notice) {
    	return managerMapper.insertNotice(notice);
    }
    public int updatePass(String userId, String changePassword) {
    	return managerMapper.updatePass(userId, changePassword);
    }
    public int updateFire(String usersId) {
    	return managerMapper.updateFire(usersId);
    }

    public List<OrderDetailsDTO> getOrdersWithinDateRange(String startDate, String endDate) {
        return managerMapper.findOrdersWithinDateRange(startDate, endDate);
    }
    
    public OrderDetailsDTO getOrderDetails(int orderNo) {
    	return managerMapper.findOrderDetails(orderNo);
    }
    
	

}