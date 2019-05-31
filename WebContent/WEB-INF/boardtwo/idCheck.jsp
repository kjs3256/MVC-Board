<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- JS -->
<script src="${pageContext.request.contextPath }/js/script.js"></script>
<title>ID 중복체크</title>
<!-- 스타일시트 -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<style>
	a{ text-decoration: none; }
</style>
</head>
<body>
	<div class="text-center">
		<br><b>${id }</b>
		<c:if test="${check}">
			는 이미 존재하는 ID입니다.<br/><br/>
		</c:if>
		<c:if test="${check ==false}">
			는 사용 가능한 ID입니다.<br/><br/>
		</c:if>
		
		<a href="#" onClick="javascript:self.close()">닫기</a>
	</div>
<!-- 애니메이션 담당 JQuery -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 JS -->
<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
</body>
</html>