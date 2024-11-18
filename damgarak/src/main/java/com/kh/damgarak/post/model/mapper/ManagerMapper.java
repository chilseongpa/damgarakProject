package com.kh.damgarak.post.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.employee.model.vo.Employee;
import com.kh.damgarak.post.model.vo.Notice;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.model.vo.Reply;
import com.kh.damgarak.post.specification.model.dto.OrderDetailsDTO;
import com.kh.damgarak.reservation.model.vo.Reservation;
import com.kh.damgarak.users.model.vo.Users;

@Mapper
public interface ManagerMapper {
	List<SuggestionDTO> selSuggest(Post post);

	List<SuggestionDTO> selEmp(Users user);
	
	List<Post> insertPost(Post post);
	
	List<SuggestionDTO> selsugDetail(int postNo);
	
	List<SuggestionDTO> selempDetails(String usersId);
	
	int updateNameEmail(Users user);

	int updateJob(Employee emp);
	
	List<SuggestionDTO> selReply(int postNo);
	
	int insertReply(Reply reply);
	
	int insertNotice(Notice notice);
	
	int updatePass(String userId, String changePassword);
	
	int updateFire(String usersId);

	List<OrderDetailsDTO> findOrdersWithinDateRange(String startDate, String endDate);
	
	List<SuggestionDTO> selRv (Reservation reservation);
	
	List<SuggestionDTO> selbentoRv (Reservation reservation);
}