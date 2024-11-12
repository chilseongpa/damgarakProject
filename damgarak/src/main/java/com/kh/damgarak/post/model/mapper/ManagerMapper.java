package com.kh.damgarak.post.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.employee.model.vo.Employee;
import com.kh.damgarak.post.model.vo.Notice;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.post.model.vo.Reply;
import com.kh.damgarak.users.model.vo.Users;

@Mapper
public interface ManagerMapper {
	List<SuggestionDTO> selSuggest(Post post);

	List<SuggestionDTO> selEmp(Users user);
	
	List<Post> insertPost(Post post);
	
	List<SuggestionDTO> selsugDetail(int postNo);
	
	List<SuggestionDTO> selempDetails(String usersId);
	
	List<Users> deleteUser(Users users);
	
	int updateNameEmail(Users user);

	int updateJob(Employee emp);
	
	List<SuggestionDTO> selReply(int postNo);
		
	int updatePass(@Param("usersPassword") String usersPassword);
	
	int insertReply(Reply reply);
	
	int insertNotice(Notice notice);
}