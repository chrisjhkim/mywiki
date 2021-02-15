package com.chrisjhkim.mysite.vo;

public class Tag {
	String tagName;
	int contentNo;

	public Tag(String tagName, int contentNo) {
		super();
		this.tagName = tagName;
		this.contentNo = contentNo;
	}

	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getContentNo() {
		return contentNo;
	}
	public void setContentNo(int contentNo) {
		this.contentNo = contentNo;
	}
	@Override
	public String toString() {
		return "Tag [tagName=" + tagName + ", contentNo=" + contentNo + "]";
	}
	
	
}
