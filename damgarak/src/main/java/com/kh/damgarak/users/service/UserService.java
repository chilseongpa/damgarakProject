package com.kh.damgarak.users.service;

import org.springframework.stereotype.Service;

import com.kh.damgarak.users.model.mapper.UserMapper;
import com.kh.damgarak.users.model.vo.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
		private final UserMapper userMapper;
		
		public Users userLogin(Users user){
			return userMapper.loginUser(user.getUsersId(), user.getUsersPassword());
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
		public int updateUserPassword(String userId, String tempPassword) {
			return userMapper.updateUserPassword(userId, tempPassword);
		}
		public Users emailCheck(String email) {
			return userMapper.emailCheck(email);
		}

		

	
		
		
}
