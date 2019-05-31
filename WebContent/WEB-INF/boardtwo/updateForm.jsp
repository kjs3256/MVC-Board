<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 스타일시트 -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<c:if test="${flag1 }">
	<script>
		alert("로그인을 해주세요오!!!!");
		location.href="loginForm.do";
	</script>
</c:if>
<c:if test="${flag2 }">
	<script>
		alert("권한이 없습니다.");
		location.href="list.do";
	</script>
</c:if>
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
				<!-- 로그아웃 상태 -->
				<c:if test="${loginID == null }">
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
				</c:if>
				<c:if test="${loginID != null}">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/board/logout.do">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/board/delMember.do">회원탈퇴</a></li>
					</ul>
					</li>
				</ul>
				</c:if>
			</div>
		</nav>
	</header>
	<section>
		<!-- 수정 -->
		<div class="container">
			<div class="row">
				<form method="post" name="writeForm" 
				enctype="multipart/form-data"
				action="${pageContext.request.contextPath}/board/updatePro.do?pageNum=${pageNum}"
				onsubmit="return writeSave()">
					<input type="hidden" name="num" value="${article.num }">
					<input type="hidden" name="nickname" value="${article.nickname }" >
					<input type="hidden" name="filename" value="${article.filename }" >
					<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
						<thead>
							<tr>
								<th style="background-color:#eeeeee; text-align:center"><h3>글 수정</h3></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="text" class="form-control col-sm-4" placeholder="변경할 글 제목" name="subject" maxlength="50" value="${article.subject }"/></td>
							</tr>
							<tr>
								<td><textarea class="form-control" name="content" maxlength="2048" style= "resize:none; height:350px;">${article.content }</textarea></td>
							</tr>
							<tr>
								<td class="text-left">				
									${article.filename }<br>
									<input type="file" name="filename">
								</td>
							</tr>
						</tbody>
					</table>
					<button type="submit" class="btn btn-primary pull-right">글수정</button>
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