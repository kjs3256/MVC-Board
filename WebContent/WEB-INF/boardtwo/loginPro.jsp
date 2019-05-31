<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result == 1 }">
	<% response.sendRedirect("list.do");%>
</c:if>
<c:if test="${ret == false }">
	<script>
		alert("비밀번호가 틀렸습니다.");
		history.back();
	</script>
</c:if>
<c:if test="${ret }">
	<script>
		alert("존재하지 않는 아이디 입니다.");
		history.back();
	</script>
</c:if>