package com.kh.damgarak.post.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.post.model.vo.Post;

@Mapper
public interface ManagerMapper {
	// 예시
	// int updateUserInfo(User user);
	List<Post> selSuggest(Post post);
	
}