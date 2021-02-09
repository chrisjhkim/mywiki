package com.chrisjhkim.mysite.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chrisjhkim.mysite.param.ReturnCode;
import com.chrisjhkim.mysite.service.UserService;
import com.chrisjhkim.mysite.util.JsonUtil;
import com.chrisjhkim.mysite.vo.User;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/join")
	public String join() {
		return "join";
	}
	
	/**
	 * checks for duplication of ID 
	 * for register service 
	 */
	@RequestMapping(value="/checkIdDuplicate", method=RequestMethod.POST)
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
	
	
	

}
