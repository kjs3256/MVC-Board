<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${flag }">
	<script>
		alert("로그인을 해주세요오!!!!");
		location.href="loginForm.do";
	</script>
</c:if>

<c:if test="${i == 1}">
	<meta http-equiv="Refresh" content="0;url=${pageContext.request.contextPath}/board/content.do?num=${num }&pageNum=${pageNum}">
</c:if>