<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 스타일시트 -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<title>로그아웃</title>
<style>
	a{ text-decoration: none;}
</style>
</head>
<body>
<div class="container">
	<div class="row text-center" style="margin-top:40px;">
		<b>로그아웃이 되었습니다. 바위~</b><br/><br/>
		<a href="${pageContext.request.contextPath }/board/loginForm.do" class="text text-center">로그인 페이지로 이동</a>
	</div>
</div>
<!-- 애니메이션 담당 JQuery -->
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 JS -->
<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<!-- JS -->
<script src="${pageContext.request.contextPath }/js/script.js"></script>
</body>
</html>