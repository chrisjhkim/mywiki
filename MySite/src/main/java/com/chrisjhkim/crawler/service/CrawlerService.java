package com.chrisjhkim.crawler.service;

import java.io.IOException;
import java.util.ArrayList;

import com.chrisjhkim.thesite.vo.Content;

public interface CrawlerService {

	
	public ArrayList<String> getContentUrlListFromReviewBoardUrl(String reviewBoardUrl) throws IOException;
	
	public String getContFromUrl(String contentUrl);
	
	public Content getContentFromUrl(String contentUrl);
}
