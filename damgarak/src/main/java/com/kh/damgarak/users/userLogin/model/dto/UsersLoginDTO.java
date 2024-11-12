package com.kh.damgarak.users.userLogin.model.dto;

import com.kh.damgarak.employee.model.vo.Employee;
import com.kh.damgarak.users.model.vo.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersLoginDTO {
	private Employee employee;
	private Users users;	
}
