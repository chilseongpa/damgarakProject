package com.kh.damgarak.users.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.kh.damgarak.users.model.mapper.UserMapper;
import com.kh.damgarak.users.model.vo.Users;
import com.kh.damgarak.users.userLogin.model.dto.UsersLoginDTO;
import com.kh.damgarak.users.userMemvership.model.dto.userMemvershipDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
		private final UserMapper userMapper;
		
		public UsersLoginDTO userLogin(String usersId){
			return userMapper.loginUser(usersId);
		} 
		public int userSingup(Users user){
			return userMapper.logupUser(user);
		}
		public int idCheck(String usersId) {
			return userMapper.idCheck(usersId);
		}
		public String findUserId(String userName, String userMail) {
			return userMapper.findUserId(userName, userMail);
		}
		public boolean findUserPwd(String userName, String userId, String userMail) {
			Users user = userMapper.findUserPwd(userName, userId, userMail);
			if(user != null) {
				return true;
			}
			return false;
		}
		public int updateUserPassword(String userId, String password) {
			return userMapper.updateUserPassword(userId, password);
		}
		
		
		public Users emailCheck(String email) {
			return userMapper.emailCheck(email);
		}

		public UsersLoginDTO checkPassword(String usersId) {
			
			return userMapper.checkPassword(usersId);
		}
		public int deleteUser(String usersId) {
			
			return userMapper.deleteUser(usersId);
		}
		public userMemvershipDTO userMyPageInfo(String usersId) {
			return userMapper.userMyInfo(usersId);
		}
		public int empSingup(String empId) {
			
			return userMapper.empSingup(empId);
		}

		

	
		
		
}
