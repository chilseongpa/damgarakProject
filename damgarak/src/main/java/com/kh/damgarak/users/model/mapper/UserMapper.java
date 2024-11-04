package com.kh.damgarak.users.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.damgarak.users.model.vo.Users;

@Mapper
public interface UserMapper {

	Users loginUser(String usersId, String usersPassword);
	int logupUser(Users user);
	int idCheck(String usersId);
	String findUserId(String userName, String userMail);
	Users findUserPwd(String userName, String userId, String userMail);
	int updateUserPassword(String userId, String tempPassword);
	Users emailCheck(String email);
}
