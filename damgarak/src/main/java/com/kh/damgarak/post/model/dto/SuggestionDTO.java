package com.kh.damgarak.post.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SuggestionDTO {
	
	private int postNo;
	private String title;
	private Date creationDate;
	private String usersId;
	private String usersName;
	private String email;
	private String jobCode;
	private String jobName;
	
	
}
