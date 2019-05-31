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
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/board/loginForm.do">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/board/joinForm.do">회원가입</a></li>
					</ul>
					</li>
				</ul>
			</div>
		</nav>
	</header>
	<section>
		<!-- 로그인 폼 -->
		<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<!-- 점보트론 -->
				<div class="jumbotron" style="padding-top:20px;">
				<!-- 로그인 정보를 숨기면서 전송post -->
				<form method="post" action="${pageContext.request.contextPath }/board/loginPro.do">
				<h3 style="text-align:center;">로그인 화면</h3>
					<div class="form-group">
						<c:if test="${check }">
						<input type="text" class="form-control" placeholder="아이디" name="id" value=${cookieID } maxlength="20">
						</c:if>
						<c:if test="${check==false }">
						<input type="text" class="form-control" placeholder="아이디" name="id" maxlength="20">
						</c:if>
					</div>
					<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호" name="pass" maxlength="20">
					</div>
					<div class="form-group">
						<c:if test="${check }">
						<input type="checkbox" name="saveID" value="saveID" checked="checked">아이디 저장
						</c:if>
						<c:if test="${check==false }">
						<input type="checkbox" name="saveID" value="saveID">아이디 저장
						</c:if>
					</div>
					<input type="submit" class="btn btn-primary form-control" value="로그인">
				</form>
				</div>
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