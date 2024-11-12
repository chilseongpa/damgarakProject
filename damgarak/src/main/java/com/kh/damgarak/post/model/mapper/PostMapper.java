package com.kh.damgarak.post.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.vo.Notice;
import com.kh.damgarak.post.model.vo.Post;

@Mapper
public interface PostMapper {

	List<SuggestionDTO> selNotice(Notice notice);
	
	List<SuggestionDTO> selNoticeDetail(@Param("noticeNo") int noticeNo);

}