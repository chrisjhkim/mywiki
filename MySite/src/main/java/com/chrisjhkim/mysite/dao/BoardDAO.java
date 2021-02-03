package com.chrisjhkim.mysite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chrisjhkim.mysite.vo.Board;

@Mapper
public interface BoardDAO {
	
	public List<Board> getAllList();

	
}
