package com.chrisjhkim.mysite.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrisjhkim.mysite.dao.BoardDAO;
import com.chrisjhkim.mysite.vo.Board;
import com.chrisjhkim.mysite.vo.Tag;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO boardDao;

	@Override
	public List<Board> getAllList() {
		System.out.println("service - getAllList");
		List<Board> list = boardDao.getAllList();
//		int temp = boardDao.getAllList();
		System.out.println(list==null?"list null":"list size="+list.size());
		return list;
	}

	@Override
	public int insertBoard(Board board) {
		System.out.println("insert board ");
		int result = 0;
		result = boardDao.insertBoard(board);
		return result;
	}

	@Override
	public List<HashMap<String, Object>> getBoardList() {
		Map<String, Object> input = new HashMap<String, Object>();
		List<HashMap<String,Object>> retList = boardDao.getActiveBoardList(input);
		for ( int i = 0 ; i < retList.size(); i ++ ) {
			retList.get(i).put("regDate", retList.get(i).get("regDate").toString());
		}
		return retList;
	}

	@Override
	public Board getBoardContent(int contentNo) {
		Board board = boardDao.getBoardContent(contentNo);
		return board;
	}

	@Override
	public int insertTags(int contentNo, String[] tags) {
		List<Tag> list = new ArrayList<Tag>();
		for ( int i = 0 ; i < tags.length ; i ++ ) {
			list.add(new Tag(tags[i], contentNo));
		}
		return insertTags(list);
	}

	@Override
	public int insertTags(List<Tag> list) {
		int result = -1;
		result = boardDao.insertTags(list);
		return result;
	}

	@Override
	public List<String> getTagList(int contentNo) {
		boardDao.getTagList(contentNo);
		return null;
	}
	
	
	

}
