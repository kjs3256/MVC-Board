<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check == 1 }">
	<meta http-equiv="Refresh" 
	content="0;url=${pageContext.request.contextPath}/board/content.do?num=${num }&pageNum=${pageNum}">
</c:if>
<c:if test="${check == -1 }">
	<script>
		alert("수정 실패(이상한거 적지마 이눔들아!!)");
		location.href="list.do";
	</script>
</c:if>
