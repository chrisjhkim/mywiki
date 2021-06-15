package com.chrisjhkim.crawler;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import com.chrisjhkim.crawler.service.CrawlerService;
import com.chrisjhkim.crawler.service.impl.CrawlerServiceImpl;

public class TestCrawler {
	
	@Test
	public void doTest() throws IOException {
		CrawlerServiceImpl crawler = new CrawlerServiceImpl();
		
		assertNotNull(crawler);
		
		String targetUrl = null;
																targetUrl = "https://opga43.com/bbs/board.php?_menu=1&bo_table=op_review&cat=12575";
		
		ArrayList<String> contentUrlList = crawler.getContentUrlListFromReviewBoardUrl(targetUrl);
		

		for ( int i = 0 ; i < contentUrlList.size() ; i ++ ) {
			System.out.println(i + " " + contentUrlList.get(i));
		}

		Content content = 

		
		
	}
	
	
//	@Test
	public void testCrawler() {
		try {
			// 간략화된 GET, POST
			Document google1 = Jsoup.connect("http://www.google.com").get();
			Document google2 = Jsoup.connect("http://www.google.com").post();

			// Response로부터 Document 얻어오기
			Connection.Response response = Jsoup.connect("http://www.google.com")
			                                    .method(Connection.Method.GET)
			                                    .execute();
			Document google3 = response.parse();

			
			Document document = response.parse();
			
			String html = document.html();
			String text = document.text();
			
			System.out.println(html);
			System.out.println(text);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		


			
	}
}
