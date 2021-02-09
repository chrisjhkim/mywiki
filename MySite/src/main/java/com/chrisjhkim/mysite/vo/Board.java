package com.chrisjhkim.mysite.vo;

import java.util.List;

public class Board {
	
	private int contentNo;
	private String title;
	private String content;
	private int userNo;
	private String regDate;
	private String status;
	
	private String userId;
	
	private List<String> tagNames;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getContentNo() {
		return contentNo;
	}
	public void setContentNo(int contentNo) {
		this.contentNo = contentNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getTagNames() {
		return tagNames;
	}
	public void setTagNames(List<String> tagNames) {
		this.tagNames = tagNames;
	}
	@Override
	public String toString() {
		return "Board [contentNo=" + contentNo + ", title=" + title + ", content=" + content + ", userNo=" + userNo
				+ ", regDate=" + regDate + ", status=" + status + ", userId=" + userId + ", tagNames=" + tagNames + "]";
	}

	
	
	
}
