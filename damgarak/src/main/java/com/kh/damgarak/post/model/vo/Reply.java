package com.kh.damgarak.post.model.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Reply {
	private int postNo;
	private Date creationDate;
	private String replyComment;
	private String deleteState;
	private String usersId;
}
