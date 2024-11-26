package com.kh.damgarak.users.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.damgarak.users.model.vo.Users;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;
import com.kh.damgarak.users.userMemvership.model.dto.userMemvershipDTO;

@Mapper
public interface UserMapper {

	UsersLoginDTO loginUser(String usersId);
	int logupUser(Users user);
	int idCheck(String usersId);
	String findUserId(String userName, String userMail);
	Users findUserPwd(String userName, String userId, String userMail);
	int updateUserPassword(String userId, String password);
	Users emailCheck(String email);
	UsersLoginDTO checkPassword(String usersId);
	int deleteUser(String usersId);
	userMemvershipDTO userMyInfo(String usersId);
	int empSingup(String empId);
}
