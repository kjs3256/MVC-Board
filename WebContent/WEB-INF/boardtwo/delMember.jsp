<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 스타일시트 -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<title>진수 Blog</title>
</head>
<body>
	<header>
		<!-- 네비게이션 -->
		<nav class="navbar navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/board/main.do">진수's World!</a>
			</div>
			<div class="collapse navbar-collapse" id="#bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/board/list.do">게시판(자료실)</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/board/logout.do">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/board/delMemeber.do">회원탈퇴</a></li>
					</ul>
					</li>
				</ul>
			</div>
		</nav>
	</header>
	<section>
		<div class="container">
			<div class="jumbotron" style="margin:100px; margin-left:200px; margin-right:200px;">
				<form name="myForm" method="post" action="${pageContext.request.contextPath }/board/delMemberPro.do"
				onsubmit="return checkIt()">
					<table class="table">
						<tr><th colspan="2" class="text-center"><h3>회원 탈퇴(탈퇴 시 게시글도 모두 삭제됩니다.)</h3></th></tr>
						<tr><td><h4>비밀번호</h4></td>
							<td><input type="password" name="pass" class="form-control col-sm-5"></td>
						</tr>
					</table>
					<div class="text-center">
						<input type="submit" class="btn btn-danger" value="회원탈퇴">
						<input type="button" class="btn btn-primary" value="취소"
								onClick="javascript:window.location='list.do'">
					</div>
				</form>
			</div>
		</div>
	</section>
<!-- 애니메이션 담당 JQuery -->
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 JS -->
<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<!-- JS -->
<script src="${pageContext.request.contextPath }/js/script.js"></script>
</body>
</html>