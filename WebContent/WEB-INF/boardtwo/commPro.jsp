<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${flag }">
	<script>
		alert("로그인을 해주세요오!!!!");
		history.go(-1);
	</script>
</c:if>
<c:if test="${flag == false }">
	<meta http-equiv="Refresh" content="0;url=${pageContext.request.contextPath}/board/content.do?num=${num }&pageNum=${pageNum}">
</c:if>