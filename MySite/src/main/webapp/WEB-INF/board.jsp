<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%-- <%@ page import="bbs.BbsDAO" %> --%>
<%@ page import="com.chrisjhkim.mysite.vo.Board" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" 
		content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>JSP 게시판 0027 Page</title>
<style type="text/css">
	a, a:hover{
		color: #000000;
		text-decoration: none;
	}
</style>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("userID")!=null){
			userID = (String)session.getAttribute("userID");
		}
		int pageNumber = 1;
		if ( request.getParameter("pageNumber")!= null ) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("console.log('hi')");
//		script.println("console.log('hi2. "+session.getAttribute("boardList")+"')");
//		script.println("console.log('hi3. "+request.getAttribute("boardList")+"')");
		script.println("</script>");
	%>

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" 
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main">메인</a>
				<li class="active"><a href="board">게시판</a>
			</ul>
			<%
				if( userID == null ) {
					
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" 
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login">로그인</a></li>
						<li><a href="join">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
					
				}else{
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" 
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logout">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
			
			
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-9">
				<table class="table table-striped" style="text-align:center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">번호</th>
							<th style="background-color: #eeeeee; text-align: center;">제목</th>
							<th style="background-color: #eeeeee; text-align: center;">작성자</th>
							<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${boardList}">
						<tr>
							<td>${board.bbsID}</td>
							<td><a href="view.jsp?bbsID=${board.bbsID}}">${board.bbsTitle.replaceAll("<", "&lt;")
																			.replaceAll(" ", "&nbsp;")
																			.replaceAll("<", "&lt;")
																			.replaceAll(">", "&gt")}</a></td>
							<td>${board.userID}</td>
	 						<td>${board.bbsDate.substring(0,11)} ${board.bbsDate.substring(11,13)}시 ${board.bbsDate.substring(14,16)}분 </td>
						</tr>
						</c:forEach>
						<%
							//BbsDAO bbsDAO = new BbsDAO();
							//ArrayList<Bbs> list = bbsDAO.getList(pageNumber);
							//for ( int i = 0 ; i < list.size() ; i ++ ) {
						%>
						<tr>
							<td>13</td>
							<td><a href="view.jsp?bbsID=13">13번글</a></td>
							<td>13유저</td>
	 						<td>대충 시간 00시00분</td>
						</tr>
						<%
							//}
						%>
					</tbody>
				</table>
				<% 
					//if( pageNumber != 1 ) {
						
				%>
					<a href="bbs.jsp?pageNumber=<%=pageNumber -1 %>" class="btn btn-success btn-arrow-left">이전</a>
				<%
					//}if ( bbsDAO.nextPage(pageNumber+1)){
				%>
						<a href="bbs.jsp?pageNumber=<%=pageNumber +1 %>" class="btn btn-success btn-arrow-right">다음</a>
				<%	
					//}
				%>
				<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
			</div>
			<div class="col-sm-3">
			<div class="sidebar-module">
	            <h4>Archives</h4>
	            <ol class="list-unstyled">
	              <li><a href="#">March 2014</a></li>
	              <li><a href="#">February 2014</a></li>
	              <li><a href="#">January 2014</a></li>
	              <li><a href="#">December 2013</a></li>
	              <li><a href="#">November 2013</a></li>
	              <li><a href="#">October 2013</a></li>
	              <li><a href="#">September 2013</a></li>
	              <li><a href="#">August 2013</a></li>
	              <li><a href="#">July 2013</a></li>
	              <li><a href="#">June 2013</a></li>
	              <li><a href="#">May 2013</a></li>
	              <li><a href="#">April 2013</a></li>
	            </ol>
	          </div>
			</div>
		</div>
	</div>
 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	Main page 1
</body>
</html>