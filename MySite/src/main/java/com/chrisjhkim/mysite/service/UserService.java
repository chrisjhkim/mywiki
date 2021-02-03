package com.chrisjhkim.mysite.service;

import com.chrisjhkim.mysite.vo.User;

public interface UserService {
	
	public int registerUser(User user);
	
	public boolean isNewIdOk(String userId);
	
	public int selectUserByIdAndPw(String userId, String userPw);
	
}
