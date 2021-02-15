package com.chrisjhkim.mysite.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chrisjhkim.mysite.vo.Board;
import com.chrisjhkim.mysite.vo.Tag;
import com.chrisjhkim.mysite.service.BoardService;

@Controller
public class DemoController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("/index");
		return "index";
	}

	
	@RequestMapping("/main")
	public String main() {
		System.out.println("/main");
		return "main";
	}
	
	@RequestMapping(value="/board", method = RequestMethod.GET)
	public ModelAndView board(ModelAndView mav) {
		System.out.println("board - GET");
		mav.setViewName("board");
		
//		List<Board> list = boardService.getAllList();
		List<HashMap<String, Object>> list = boardService.getBoardList();
		System.out.println(list);
		mav.addObject("boardList", list);
		return mav;
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView view(ModelAndView mav, HttpServletRequest request) {
		System.out.println("/view");
		mav.setViewName("view");
		int contentNo = Integer.parseInt(request.getParameter("no"));
		Board board = boardService.getBoardContent(contentNo);
		System.out.println(board);
//		mav.addObject(board);
		mav.addObject("board",board);
		List<String> tagList = boardService.getTagList(contentNo);
		System.out.println();
		return mav;
	}
	
	
	

	
	@RequestMapping("/write")
	public ModelAndView write(ModelAndView mav, HttpSession session) {
		if( session.getAttribute("userId") == null ) {
			mav.setViewName("login");
		}else {
			mav.setViewName("write");
		}
		return mav;
	}
	@RequestMapping("/writeAction")
	public ModelAndView writeAction(@ModelAttribute Board board,  ModelAndView mav, HttpSession session) {
		System.out.println("/writeAction");
		if( session.getAttribute("userId") == null ) {
			mav.setViewName("login");
		}else {
			System.out.println("input = "+board);
			board.setUserNo(1);
			int result = boardService.insertBoard(board);
			System.out.println("insert result = "+result);
			
			String tags[] = null;
//			boardService.insertTags(board.getUserNo(), tags);
			
		}
		
		mav.setViewName("redirect:/board");
		return mav;
	}
}
