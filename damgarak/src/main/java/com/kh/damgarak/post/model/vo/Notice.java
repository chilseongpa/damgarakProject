package com.kh.damgarak.post.model.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Notice {
	private String UsersId;
	private String noticeTitle;
	private String noticeContent;
	private Date creationDate;
	private int noticeNo;
}
