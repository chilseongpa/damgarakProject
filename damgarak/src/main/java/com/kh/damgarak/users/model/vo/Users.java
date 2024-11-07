package com.kh.damgarak.users.model.vo;

import java.sql.Date;
import lombok.Data;

@Data
public class Users {
	
	private String usersId;
	private String usersPassword;
	private String usersName;
	private String email;
	private Date joinDate;
	private Date withdrawalDate;
	private String usersState;
	private String usersType;
}
