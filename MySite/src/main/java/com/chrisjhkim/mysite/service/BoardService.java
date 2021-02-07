package com.chrisjhkim.mysite.service;

import java.util.HashMap;
import java.util.List;

import com.chrisjhkim.mysite.vo.Board;

public interface BoardService {

	public List<Board> getAllList();
	
	public int insertBoard(Board board);
	public List<HashMap<String, Object>> getBoardList();
	
	public Board getBoardContent(int contentNo);
}
