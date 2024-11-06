package com.kh.damgarak.post.model.vo;

import java.sql.Date;
import lombok.Data;

@Data
public class Post {
	private int post_No;
	private int post_Count;
	private String title;
	private String content;
	private Date creation_Date;
	private Date modification_Date;
	private String delete_State;
	private String user_Id;
	private String category_Code;
}
