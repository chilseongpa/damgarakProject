package com.kh.damgarak.users.userLogin.model.dto;

import lombok.Data;

@Data
public class UsersLoginDTO {
	private String usersId;
	private	String usersPassword;
	private String usersType;
	private String employeeType;	
}
