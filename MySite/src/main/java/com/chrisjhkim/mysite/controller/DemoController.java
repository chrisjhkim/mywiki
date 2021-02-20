package com.chrisjhkim.mysite.controller;

import java.text.ParseException;
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
import com.chrisjhkim.mysite.service.BoardService;
import com.chrisjhkim.mysite.service.UserService;

@Controller
public class DemoController {
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;
	
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
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest req,  ModelAndView mav, HttpSession session) {
		System.out.println("/edit");
		if( session.getAttribute("userId") == null ) {
			mav.setViewName("login");
		}else {
			try{
				int contentNo = Integer.parseInt(req.getParameter("contentNo"));
				System.out.println("contentNo = "+contentNo);
				Board board  = boardService.getBoardContent(contentNo);
				System.out.println(board);
				
				if ( board.getUserId().equals(session.getAttribute("userId"))){
					mav.addObject("board", board);
				}else {
					System.out.println("user not match . something wrong");
				}
						
			}catch (Exception e ) {
				e.printStackTrace();
			}
			
			mav.setViewName("edit");
		}
		System.out.println();
		return mav;
	}
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest req, ModelAndView mav, HttpSession session) {
		System.out.println("/delete");
		if( session.getAttribute("userId") == null ) {
			System.out.println("userId from session null");
			mav.setViewName("login");
			return mav;
		}else if ( req.getParameter("contentNo") == null ) {
			System.out.println("req parameter contentNo null");
			mav.setViewName("board");
			return mav;
		}
		
		String userId = (String) session.getAttribute("userId");
		int userNo = userService.getUserNoByUserId(userId);
		
		if ( userNo == -1 ) {
			System.out.println("user vo null from userId");
			mav.setViewName("board");
			return mav;
		}
		
		int contentNo = -1;
		try {
			contentNo = Integer.parseInt(req.getParameter("contentNo"));
		}catch (NumberFormatException e) {
			System.out.println("parsing error");
			e.printStackTrace();
			mav.setViewName("redirect:/board");
			return mav;
		}
		
		if ( contentNo == -1 ) {
			System.out.println("contentNo wrong");
			mav.setViewName("redirect:/board");
			return mav;
		}
		
		Board board = boardService.getBoardContent(contentNo);
		if ( board == null ) {
			System.out.println("ERROR boardNo -> board null ");
			mav.setViewName("redirect:/board");
			return mav;
		}else if ( userNo != board.getUserNo() ) {
			System.out.println("userNo from session != userNo from content not match ");
			mav.setViewName("redirect:/board");
			return mav;
		}
		
		int result = boardService.disableBoardContent(contentNo);
		System.out.println("delete(disable) result = "+result);
		mav.setViewName("redirect:/board");
		System.out.println();
		return mav;
	}
	@RequestMapping("/editAction")
	public ModelAndView editAction(@ModelAttribute Board board,  ModelAndView mav, HttpSession session) {
		System.out.println("/editAction");
		if( session.getAttribute("userId") == null ) {
			mav.setViewName("login");
		}else {
			int userNo = userService.getUserNoByUserId((String) session.getAttribute("userId"));
			if ( userNo != -1 && userNo == board.getUserNo()) {
				System.out.println("input="+board);
				board.setStatus("1");
				int result = boardService.updateBoardContent(board);
				System.out.println("edit Result = "+result);
				mav.setViewName("redirect:/board");		
			}else {
				System.out.println("wrong userId");
				mav.setViewName("login");
			}
			
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
			String userId = (String) session.getAttribute("userId");
			System.out.println("userId from session ="+userId);
			int userNo = userService.getUserNoByUserId(userId);
			System.out.println("userNo = "+userNo);
			if ( userNo != -1 ) {
				board.setUserNo(userNo);
				int result = boardService.insertBoard(board);
				System.out.println("insert result = "+result);
				mav.setViewName("redirect:/board");
			}else {
				System.out.println("wrong userId");
				mav.setViewName("login");
			}
			
			
		}
		
		return mav;
	}
}
