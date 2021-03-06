<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" 
		content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>Main Page 005 </title>
</head>
<body>
	<%
		String userId = null;
		if (session.getAttribute("userId")!=null){
			userId = (String)session.getAttribute("userId");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("console.log('yes session')");
//			script.println("console.log('hi2. "+session.getAttribute("boardList")+"')");
//			script.println("console.log('hi3. "+request.getAttribute("boardList")+"')");
			script.println("</script>");
		}else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("console.log('no session')");
//			script.println("console.log('hi2. "+session.getAttribute("boardList")+"')");
//			script.println("console.log('hi3. "+request.getAttribute("boardList")+"')");
			script.println("</script>");
			
		}
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
				<li class="active"><a href="main">메인</a>
				<li><a href="board">게시판</a>
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
						<li><a href="/login">로그인</a></li>
						<li><a href="/join">회원가입</a></li>
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
		<div class="jumbotron">
			<div class="container">
				<h1>웹 사이트 소개</h1>
				<p>테스트용 웹사이트 .  부트 스트렙 테스트를 위해 만든 페이지 이며 해당 사이트는 JSP 에서 직접 데이터 처리.</p>
				<p><a class="btn btn-primary btn-pull" href="#" role="button">자세히 알아보기</a></p>
			</div>		
		</div>
	</div>
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="item active">
					<img src="images/1.jpg">
				</div>
				<div class="item">
					<img src="images/2.png">
				</div>
				<div class="item">
					<img src="images/3.png">
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</div>
	</div>
	
 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	Main page 1
</body>
</html>