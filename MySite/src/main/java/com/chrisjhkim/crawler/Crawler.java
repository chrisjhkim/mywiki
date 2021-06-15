package com.chrisjhkim.crawler;

import java.awt.dnd.DropTargetContext;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.html.HTMLHeadElement;

public class Crawler {

	
	public static void main(String[] args) {
		try {
			ArrayList<String> contentUrlList = getContentUrlList();
			
			
			for ( int i = 0 ; i < contentUrlList.size() ; i++ ) {
				String content = getContentFromContentUrl(contentUrlList.get(i));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static String getContentFromContentUrl(String contentUrl) throws IOException {
		System.out.println("contentUrl = " + contentUrl + " ---------");
		Document doc;
		doc = getDocumentGET(contentUrl);
		
		Elements content = doc.getElementsByClass("view-content");
		String contentText = content.text();
		Elements contentSpans = content.select("span");
		
		String contentSpansHtml = contentSpans.html();
		System.out.println(contentSpansHtml);
		
		contentSpansHtml = contentSpansHtml.replace("&nbsp", "");
		return contentSpansHtml;
	}
	private static ArrayList<String> getContentUrlList() throws IOException{
		ArrayList<String> contentUrlList = new ArrayList<>();
		String targetUrl = null;
		//targetUrl = "https://opga43.com/bbs/board.php?_menu=1&bo_table=op_review&cat=12575";
		
		Document doc = getDocumentGET(targetUrl);
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
		
		int index = 0;
		for ( String contentUrl : contentUrlList ) {
			System.out.println(index + " " + contentUrl);
			index++;
			
		}
		
		Element tempFirstTr = trList.get(0);
		
		System.out.println("first tr sample --------------------");
		System.out.println(tempFirstTr);
		tempFirstTr.select("a").attr("href");
			

		return contentUrlList;
	}
	
	private static Document getDocumentGETSimple(String url) throws IOException {
		Document doc = Jsoup.connect("http://www.google.com").get();
		return doc;
	}
	private static Document getDocumentPOSTSimple(String url) throws IOException {
		Document doc = Jsoup.connect("http://www.google.com").get();
		return doc;
	}
	private static Document getDocumentGET(String url) throws IOException {
		Connection.Response response = Jsoup.connect(url)
				.method(Connection.Method.GET)
				.execute();
		Document doc = response.parse();
		return doc;
	}

	
}
