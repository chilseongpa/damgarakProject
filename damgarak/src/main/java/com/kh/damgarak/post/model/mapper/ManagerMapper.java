package com.kh.damgarak.post.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.post.model.vo.Post;

@Mapper
public interface ManagerMapper {
	// 예시
	// int updateUserInfo(User user);
	int selSuggest(Post post);
	
}
