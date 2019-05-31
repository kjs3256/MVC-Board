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
	<c:if test="${loginID != null }">
	<script>
		alert("이미 로그인 되어있습니다.");
		location.href="list.do";
	</script>
	</c:if>
	<div class="container">
		<div class="row text-center" style="margin-top:40px;">
		<c:if test="${flag }">
			<b>회원 가입을 축하드립니다.</b><br/>
			게시판에 가서 글도 좀쓰고 파일도 올리고 해보셈!<br/>
			<a href="${pageContext.request.contextPath}/board/loginForm.do">로그인</a>
		</c:if>
		<c:if test="${flag == flase }">
			<b>가입 불가(ID 중복체크 X 또는 기타 오류)</b><br/>
			<a href="${pageContext.request.contextPath}/board/joinForm.do">다시 가입</a>
		</c:if>
		</div>
	</div>
<!-- 애니메이션 담당 JQuery -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 JS -->
<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<!-- JS -->
<script src="${pageContext.request.contextPath }/js/script.js"></script>
</body>
</html>