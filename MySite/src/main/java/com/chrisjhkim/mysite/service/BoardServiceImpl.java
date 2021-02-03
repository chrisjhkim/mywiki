package com.chrisjhkim.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrisjhkim.mysite.dao.BoardDAO;
import com.chrisjhkim.mysite.vo.Board;

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

}
