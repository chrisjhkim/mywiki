<%@page import="java.util.Enumeration"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.catalina.util.ParameterMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" 
		content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>View Page 0031 </title>
<script type="text/javascript">
window.onload = function(){
	console.log('ho!');
	var userIdSession = $("#userIdSession").val(); 
	var userIdModel = $("#userIdModel").val();

	console.log('userIdS = '+userIdSession);
	console.log('userIdM = '+userIdModel);
	if (userIdSession == userIdModel){
 		$("#editBtn").removeAttr("style"); 
 		$("#deleteBtn").removeAttr("style"); 
 		console.log('match!');
	}else{
		console.log('no match!');
	}

} 
</script>
</head>
<body>
	<%
		String userId = null;
		if (session.getAttribute("userId")!=null){
			userId = (String)session.getAttribute("userId");
		}
		PrintWriter script = response.getWriter();
		script.println("<script>");
		String board = request.getParameter("board");
		String testString = request.getParameter("testString");
		String testString2 = request.getParameterMap().getClass().toString();
		//java.util.Map map= request.getParameterMap();
		Enumeration params = request.getParameterNames();
		script.println("console.log('---paramMap----')");
		while(params.hasMoreElements()) {
			String name = (String) params.nextElement();
			script.println("console.log('"+name+" = "+request.getParameter(name)+"')");
		}

		script.println("console.log('---------------')");
		
		String test = (String)pageContext.getAttribute("test") ;
	      
		script.println("console.log('test="+test+"')");
		script.println("console.log('userId="+userId+"')");
		script.println("console.log('board="+board+"')");
		script.println("console.log('testString="+testString+"')");
		
		script.println("console.log('testString2="+testString2+"')");
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
				if( userId == null ) {
					
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
						<li><a href="/logout">로그아웃</a></li>
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
			<table class="table table-striped" style="text-align:center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">글 제목</td>
						<td colspan="2">${board.title.replaceAll("<", "&lt;").replaceAll(">", "&gt")}</td>
							<td colspan="2">${testString}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2">${board.userId}</td>
						<c:set var="test" value="abc" />
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2">${board.regDate.substring(0,11)}${board.regDate.substring(11,13)}시 ${board.regDate.substring(14,16)}분</td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left;">${board.content.replaceAll(" ", "&nbsp;").replaceAll("<", "&#60;").replaceAll(">", "&gt").replaceAll("\\n", "<br>").replaceAll("\\t", "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp")}</td>
					</tr>
					<tr>
						<td>id from session</td>
						<td><%=userId %></td>
					</tr>
					<c:set var="userId" value='${board.userId}'  />
					<c:set var="name" value="홍길동" />
					<c:if test="${name eq '홍길동'}">
					<tr>
						<td>${ board.userId }</td>
						<td>${name eq '홍길동a'} abc</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<input type="hidden" id="userIdModel" value="${board.userId}">
			<input type="hidden" id="userIdSession" value=<%=userId %>>
			
			<a href="board" class="btn btn-primary">목록</a>
			<a href="edit?contentNo=${board.contentNo}" id="editBtn" class="btn btn-primary" style="display: none;">수정</a>
			<a href="delete?contentNo=${board.contentNo}" id="deleteBtn" class="btn btn-primary" style="display: none;">삭제</a>
			<a href="write" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>
 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	Main page 1
</body>
</html>