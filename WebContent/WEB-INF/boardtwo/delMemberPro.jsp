<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 스타일시트 -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<title>회원 탈퇴</title>
<!-- 애니메이션 담당 JQuery -->
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 JS -->
<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<!-- JS -->
<script src="${pageContext.request.contextPath }/js/script.js"></script>
</head>
<c:if test="${check == 1 }">
	<meta http-equiv="Refresh" content="3;url=${pageContext.request.contextPath}/board/loginForm.do">
<body>
<div class="container">
	<div class="jumbotron" style="padding-top:30px;">
		<div class="text-center">
			<p>회원정보가 삭제되었습니다.<br></p>
			<p>3초 후에 로그인 페이지로 이동합니다.</p>
		</div>
	</div>
</div>
</body>
</c:if>
<c:if test="${check != 1 }">
<body>
	<script>
		alert("비밀번호가 틀렸습니다.");
		history.go(-1);
	</script>
</body>
</c:if>
</html>