package com.chrisjhkim.mysite.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrisjhkim.mysite.dao.UserDAO;
import com.chrisjhkim.mysite.param.ReturnCode;
import com.chrisjhkim.mysite.vo.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao;

	@Override
	public int registerUser(User user) {
		int result = -1;
		if ( user.isValidateForRegister() ) {
			System.out.println("UserServiceImpl registerUser"); //TODO : DELETE after test@@
			result = userDao.insertUser(user);
			System.out.println("insert result = "+result);
		}else {
			System.out.println("not ok for registerUser ");
			return -1;
		}
		return result;
	}

	@Override
	public boolean isNewIdOk(String userId) {
		System.out.println("isNewIdOk");
		User user = userDao.selectUserByUserId(userId);
		System.out.println("isNewId Ok? " + user);
		if ( user == null ) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int selectUserByIdAndPw(String userId, String userPw) {
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("userId", userId);
		User user = userDao.selectUser(input);
		if ( user == null ) {
			System.out.println("no user match - no id");
			return ReturnCode.USER_BAD_ID;
		}else if ( !user.getUserPassword().equals(userPw) ) {
				System.out.println("no user match - bad pw");
				return ReturnCode.USER_BAD_ID;
		}else{
			System.out.println("user match = "+userId);
			return ReturnCode.USER_OK;
		}
	}

	/**
	 * returns -1 if user null
	 */
	@Override
	public int getUserNoByUserId(String userId) {
		int userNo = -1 ;
		User user = userDao.selectUserByUserId(userId);
		if ( user != null ) {
			userNo = user.getUserNo();
		}
		return userNo;
	}
}
