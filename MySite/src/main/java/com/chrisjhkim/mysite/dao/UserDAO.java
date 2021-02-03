package com.chrisjhkim.mysite.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chrisjhkim.mysite.vo.User;

@Mapper
public interface UserDAO {
	public int insertUser(User user);
	public User selectUserByUserId(String userId);
	
	public User selectUser(Map<String, String> input);
}
