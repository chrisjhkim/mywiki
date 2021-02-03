<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" 
		content="text/html; charset=UTF-8">
<title>Login Page 0033 </title>
</head>
<body>
	<%
		String userID = null;
		if ( session.getAttribute("userID") != null ) {
			userID = (String) session.getAttribute("userID");
		}
		if( userID != null ) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인이 되어있습니다.')");
			script.println("location.href = 'main.jsp");
			script.println("</script>");
		}
		
		
		PrintWriter script = response.getWriter();
		UserDAO userDAO = new UserDAO();

		script.println("<script>");
		script.println("console.log('ho!')");
		String id = user.getUserID();
		script.println("console.log('ID = " + user.getUserID() + "')");
		script.println("console.log('PW = " + user.getUserPassword() + "')");
		script.println("</script>");
		
		int result = userDAO.login(user.getUserID(), user.getUserPassword());
		
		if( result == 1 ){
			script.println("<script>");
			script.println("console.log('result 1 ')");
			session.setAttribute("userID", user.getUserID());
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}else if( result == 0 ){
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");
			script.println("history.back()");
			script.println("</script>");
		}else if( result == -2 ){
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다. ')");
			script.println("history.back()");
			script.println("</script>");
		}else if( result == -1 ){
			script.println("<script>");
			script.println("alert('아이디가 없습니다.')");
			script.println("history.back()");
			script.println("</script>");
			
		}
	%>

</body>
</html>