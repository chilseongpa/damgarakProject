package com.kh.damgarak.users.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.damgarak.users.model.vo.Users;

@Mapper
public interface UserMapper {

	Users loginUser(String usersId, String usersPassword);
	int logupUser(Users user);
	int idCheck(String usersId);
}
