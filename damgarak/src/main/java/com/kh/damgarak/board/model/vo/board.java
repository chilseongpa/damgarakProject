package com.kh.damgarak.board.model.vo;

import java.sql.Date;
import lombok.Data;

@Data
public class board {
	private int post_No;
	private int post_Count;
	private String title;
	private String content;
	private Date creation_Date;
	private Date Modification_Date;
	private String delete_State;
	private String user_Id;
	private String category_Code;
	private String reply_Comment;
	private String post_Category_Code;
	private String post_Category_Name;
}
