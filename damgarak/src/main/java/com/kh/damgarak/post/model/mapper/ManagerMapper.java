package com.kh.damgarak.post.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.vo.Post;
import com.kh.damgarak.users.model.vo.Users;

@Mapper
public interface ManagerMapper {
	List<SuggestionDTO> selSuggest(Post post);

	List<SuggestionDTO> selEmp(Users user);
	
	List<Post> insertPost(Post post);
		
}