package com.chrisjhkim.mysite.service;

import java.util.HashMap;
import java.util.List;

import com.chrisjhkim.mysite.vo.Board;
import com.chrisjhkim.mysite.vo.Tag;

public interface BoardService {

	public List<Board> getAllList();
	
	public int insertBoard(Board board);
	public List<HashMap<String, Object>> getBoardList();
	
	public Board getBoardContent(int contentNo);
	
	public int insertTags(int contentNo, String[] tags);
	public int insertTags(List<Tag>list);
	public List<String> getTagList(int contentNo);

	public int updateBoardContent(Board board);
	
	public int disableBoardContent(int contentNo);
//	public List<Tag> getTagList(int userNo);
//	public List<Tag> getTagList()
}
