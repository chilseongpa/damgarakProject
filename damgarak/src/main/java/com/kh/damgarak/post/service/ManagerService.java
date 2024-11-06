package com.kh.damgarak.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.damgarak.post.model.mapper.ManagerMapper;
import com.kh.damgarak.post.model.vo.Post;

@Service
public class ManagerService {

    private final ManagerMapper managerMapper;

    @Autowired
    public ManagerService(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    public List<Post> getSuggestedPosts(Post post) {
        // selectSuggest 메서드를 사용하여 추천 포스트 가져오기
        return managerMapper.selSuggest(post); // 실제 구현에 맞게 반환할 데이터 설정
    }

	public List<Post> selSuggest(Post post) {
		return managerMapper.selSuggest(post);
	}
}