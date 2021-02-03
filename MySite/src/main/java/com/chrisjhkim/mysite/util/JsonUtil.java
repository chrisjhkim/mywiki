package com.chrisjhkim.mysite.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

	
	/**
	 * returns to   
	 * {"resultCode":"0000","resultMsg":"message..."}
	 * response in jSon form
	 * @throws IOException 
	 */
	public static void jsonString(HttpServletResponse resp, String resultCode, String resultMsg) throws IOException   {
		resp.setHeader("Cache-Control", "no-cache"); 
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();	
		
		try {
			json.put("resultCode", resultCode);
			json.put("resultMsg", resultMsg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.write(json.toString());
		out.close();
	}
	

}
