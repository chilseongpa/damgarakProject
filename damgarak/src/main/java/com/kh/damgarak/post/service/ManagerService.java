package com.kh.damgarak.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.damgarak.post.model.dto.SuggestionDTO;
import com.kh.damgarak.post.model.mapper.ManagerMapper;
import com.kh.damgarak.post.model.vo.Post;
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
}