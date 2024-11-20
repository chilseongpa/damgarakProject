package com.kh.damgarak.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.damgarak.employee.model.vo.Employee;
import com.kh.damgarak.employee.serchEmployee.model.dto.SerchEmployeeDto;
import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.mapper.PostMapper;
import com.kh.damgarak.post.model.vo.Notice;
import com.kh.damgarak.post.model.vo.Post;

import lombok.RequiredArgsConstructor;

@Service
public class PostService {
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper) {
    	this.postMapper = postMapper;
    }
    public List<SuggestionDTO> selNotice(Notice notice){
    	return postMapper.selNotice(notice);
    }
    public List<SuggestionDTO> selNoticeDetail(int noticeNo){
    	return postMapper.selNoticeDetail(noticeNo);
    }

    public List<SuggestionDTO> getUserSuggestions(String userId) {
        return postMapper.selectUserSuggestions(userId);
    }

	public SerchEmployeeDto empInfomation(String usersId) {
		return postMapper.empInfomation(usersId);
	}



}
