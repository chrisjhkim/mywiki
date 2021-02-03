package com.chrisjhkim.mysite.vo;

public class User {
	
	private int userNo;
	private String userId;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail;

	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName + ", userGender="
				+ userGender + ", userEmail=" + userEmail + "]";
	}
	
	public boolean isValidateForRegister() {
		boolean ret = true;
		if ( this == null 
				|| this.getUserId() == null 
				|| this.getUserId().trim().equals("") 
				|| this.getUserPassword() == null 
				|| this.getUserPassword().trim().equals("")
				|| this.getUserName() == null 
				|| this.getUserName().trim().equals("") ) 
			return false;
		return ret;
	}

}
