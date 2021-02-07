package com.chrisjhkim.mysite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chrisjhkim.mysite.dao.BoardDAO;
import com.chrisjhkim.mysite.param.ReturnCode;
import com.chrisjhkim.mysite.vo.Board;
import com.chrisjhkim.mysite.vo.User;
import com.chrisjhkim.mysite.service.BoardService;
import com.chrisjhkim.mysite.service.UserService;
import com.chrisjhkim.mysite.util.JsonUtil;

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
		System.out.println();
		return mav;
	}
	
	@RequestMapping("/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/checkIdDuplicate", method=RequestMethod.POST)
//	public Map<String, Object> checkIdDuplicate(HttpServletResponse resp, String id) throws IOException{
	public void checkIdDuplicate(HttpServletResponse resp, String id) throws IOException{
		System.out.println("/checkIdDuplicate - POST " + id);
		
		String result = "";
		if ( id == null || id.trim().equals("")) {
			result =  ReturnCode.EMPTY_PARAMETER;
		}else {
			if( userService.isNewIdOk(id) ) {
				result = ReturnCode.OK;
			}else {
				result = ReturnCode.ID_DUPLICATE;
			}
		}
		System.out.println("/checkIdDuplicate result="+result);
		JsonUtil.jsonString(resp, result, "");
		
		
//		return retMap;
		
	}
	@RequestMapping(value="/joinAction", method = RequestMethod.POST )
	public String joinAction(@ModelAttribute("formModel")User user ){
		System.out.println("/joijnAction ");
		System.out.println(user==null? "user null ":user);
		if ( user.isValidateForRegister() ) {
			if ( userService.isNewIdOk(user.getUserId()) ) {
				userService.registerUser(user);
			}else {
				return "";
			}
		}else {
			System.out.println("BAD for register");
			
		}
		
		return "redirect:/";
	}

	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	public void loginCheck(@ModelAttribute("formModel")User user, HttpSession session , HttpServletResponse resp) throws IOException{
		System.out.println("/loginCheck " + user);
		
		int loginResult = userService.selectUserByIdAndPw(user.getUserId(), user.getUserPassword());

		System.out.println("login result = "+loginResult);
		if ( loginResult == ReturnCode.USER_OK) {
			System.out.println("login OK");
			session.setAttribute("userId", user.getUserId());
			JsonUtil.jsonString(resp, "0000", "");
		}else {
			System.out.println("login BAD");
			JsonUtil.jsonString(resp, "1001", "");
		}
		System.out.println();
	}
	@RequestMapping(value="/loginAction")
	public String loginAction(@ModelAttribute("formModel")User user, HttpSession session ){
		System.out.println("/loginAction ");
		System.out.println(user==null? "user null ":user);
		
		int result = userService.selectUserByIdAndPw(user.getUserId(), user.getUserPassword());
		
		System.out.println("login result = "+result);
		if ( result == ReturnCode.USER_OK) {
			session.setAttribute("userId", user.getUserId());
			return "redirect:/main";
		}else {
			return "redirect:/login";
			
		}
	}
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if ( session.getAttribute("userId") != null ) {
			session.removeAttribute("userId");
		}
		return "redirect:/main";
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
		}
		mav.setViewName("redirect:/board");
		return mav;
	}
}
