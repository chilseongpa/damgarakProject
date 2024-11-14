package com.kh.damgarak.post.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SuggestionDTO {
	
	private int postNo;
	private int postCount;
	private String title;
	private String content;
	private Date creationDate;
	private String usersId;
	private String usersName;
	private String email;
	private String jobCode;
	private String jobName;
	private String replyComment;
	private String noticeTitle;
	private String noticeContent;
	private int noticeNo;
	private Date noticeCreationDate;

}

