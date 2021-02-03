<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" 
		content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>Join Page 0022</title>
<script type="text/javascript">
function reactivateDupBtn(){
	$("#dupBtn").attr('disabled', false);
}
function dupCheck(){
	var id = $("#userId").val();
	if ( id == null || id.trim() == '') {
		alert('아이디를 입력하세요');
		return;
	}
	console.log('id = ' + id)
	$.ajax({
		type:"POST",
		data:'id='+id,
		dataType : "json",
		url:"checkIdDuplicate",
		success:function (data) {
			console.log(data);
			if(data["resultCode"] == '0000'){
				alert('사용가능한 아이디입니다.');
				$("#dupBtn").attr('disabled', true);
			} else if ( data["resultCode"]=='1001'){
				alert('중복된 아이디입니다.');
			}else{
				alert('서버 통신 오류');
			}
		},
		error:function(request, error){
			console.log('ERROR!');
			console.log(request);
			console.log(error);
		}
		
	});
	
}
function checkValues(){
	if( $("#userId").val() == '' ){
		alert('ID를 입력하세요');
		return false;
	}
	if( $("#pw1").val() == '' ){
		alert('비밀번호를 입력하세요');
		return false;
	}
	if( $("#pw2").val() == '' ){
		alert('비밀번호를 한번 더 입력하세요');
		return false;
	}
	if( $("#pw1").val() != $("#pw2").val() ){
		alert('비밀번호 불일치');
		return false;
	}
	if( $("#userName").val() == '' ){
		alert('이름을 입력하세요');
		return false;
	}
	
}

</script>
</head>
<body>

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
				<li><a href="bbs">게시판</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" 
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login">로그인</a></li>
						<li class="active"><a href="join">회원가입</a></li>
					</ul>
				</li>
			</ul>
			
			
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top:20px">
				<form method="post" action="joinAction" onsubmit="return checkValues()" name="formModel">
					<h3 style="text-align: center;">회원가입 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userId" id="userId" maxlength="20" onkeyup="reactivateDupBtn()">
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-primary btn-sm" id="dupBtn" onclick="dupCheck()" >아이디 중복확인</button>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" id="pw1" name="userPassword" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호 재입력" id="pw2" name="userPassword2" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="userName" id="userName" maxlength="20">
					</div>
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary">
								<input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자
							</label> 
							<label class="btn btn-primary">
								<input type="radio" name="userGender" autocomplete="off" value="여자" checked>여자
							</label>
						</div>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" name="userEmail" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="회원가입" >
					
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	login page 1
</body>
</html>