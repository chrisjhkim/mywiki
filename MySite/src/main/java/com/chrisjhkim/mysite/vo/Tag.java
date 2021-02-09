package com.chrisjhkim.mysite.vo;

public class Tag {
	String tagName;
	int userNo;
	
	public Tag(String tagName, int userNo) {
		super();
		this.tagName = tagName;
		this.userNo = userNo;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "Tag [tagName=" + tagName + ", userNo=" + userNo + "]";
	}

	
}
