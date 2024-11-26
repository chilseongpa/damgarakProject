package com.kh.damgarak.post.model.vo;

import java.sql.Date;
import lombok.Data;

@Data
public class Post {
	private int postNo;
	private int postCount;
	private String title;
	private String content;
	private Date creationDate;
	private Date modificationDate;
	private String deleteState;
	private String userId;
	private String categoryCode;
}
