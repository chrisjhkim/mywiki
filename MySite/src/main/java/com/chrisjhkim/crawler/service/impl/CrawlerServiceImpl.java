package com.chrisjhkim.crawler.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.chrisjhkim.crawler.service.CrawlerService;
import com.chrisjhkim.thesite.vo.Content;

public class CrawlerServiceImpl implements CrawlerService {

	@Override
	public ArrayList<String> getContentUrlListFromReviewBoardUrl(String reviewBoardUrl) throws IOException {
		ArrayList<String> contentUrlList = new ArrayList<>();
		Document doc = getDocumentGET(reviewBoardUrl);
		Elements tables = doc.select("table");
		
//			System.out.println("tables ---------------------------------");
//			System.out.println(tables);
		
		Element targetTable = tables.get(1);
		System.out.println("targetTable -------------- ");
		System.out.println(targetTable);
		
		Element tableBody = targetTable.select("tbody").get(0);
		
		Elements trList = tableBody.select("tr");
		for ( Element tr : trList) {
			String contnetUrl = tr.select("a").attr("href");
			contentUrlList.add(contnetUrl);
		}
		
		Element tempFirstTr = trList.get(0);
		
			

		return contentUrlList;

	}
	private static Document getDocumentGET(String url) throws IOException {
		Connection.Response response = Jsoup.connect(url)
				.method(Connection.Method.GET)
				.execute();
		Document doc = response.parse();
		return doc;
	}
	@Override
	public String getContFromUrl(String contentUrl) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Content getContentFromUrl(String contentUrl) {
		// TODO Auto-generated method stub
		return null;
	}

}
