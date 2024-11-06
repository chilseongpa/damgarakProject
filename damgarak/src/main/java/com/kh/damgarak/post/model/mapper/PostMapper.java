package com.kh.damgarak.post.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.damgarak.post.model.vo.Post;

@Mapper
public interface PostMapper {

	// 직원 페이지로 이동
    List<Post> noticeMenu();

    // 게시글 추가
    int insertPost(Map<String, Object> paramMap);

    // 게시글 삭제
    int deletePost(@Param("post_No") int postNo);

    // 게시글 업데이트
    int updatePost(@Param("postNo") int postNo, 
                   @Param("title") String title, 
                   @Param("content") String content);

    // 사용자 정보 업데이트 (추가)
    int updateUserInfo(Map<String, Object> paramMap);

	List<Post> postList();

	int selectListCount();

}