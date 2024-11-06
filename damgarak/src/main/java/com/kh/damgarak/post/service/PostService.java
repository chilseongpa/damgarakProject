package com.kh.damgarak.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.damgarak.post.model.mapper.PostMapper;
import com.kh.damgarak.post.model.vo.Post;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    // 공지사항 리스트 가져오기
    public List<Post> noticeMenu() {
        return postMapper.noticeMenu();
    }

    // 게시글 업데이트
    public void updatePost(int postNo, String title, String content) {
        postMapper.updatePost(postNo, title, content); // 이 메서드는 PostMapper에 구현되어야 함
    }

    // 게시글 수 가져오기
    public int selectListCount() {
        return postMapper.selectListCount(); // 이 메서드는 PostMapper에 구현되어야 함
    }


}
