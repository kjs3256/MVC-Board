<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<div class="container">
			<div class="row">
				<h3><b>자유 게시판(전체 글:${count })</b></h3>
				<c:if test="${count == 0}">
					<table class="table table-striped text-center" style="border:1px solid #dddddd">
						<tr>
							<td><h3 class="text-center">게시판에 저장된 글이 없습니다.</h3></td>
						</tr>
					</table>
				</c:if>
				<c:if test="${count > 0}">
					<table class="table table-striped text-center" style="border:1px solid #dddddd">
						<thead>
							<tr>
								<th style="background-color:#eeeeee; text-align:center;">번호</th>
								<th style="background-color:#eeeeee; width:40%; text-align:center;">제목</th>
								<th style="background-color:#eeeeee; text-align:center;">작성자</th>
								<th style="background-color:#eeeeee; text-align:center;">작성일</th>
								<th style="background-color:#eeeeee; text-align:center;">조회수</th>
								<th style="background-color:#eeeeee; text-align:center;">IP</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="article" items="${articleList}">
								<tr>
									<td width="50">
										<c:out value="${number }"/>
										<c:set var="number" value="${number-1 }"/>
									</td>
									<td style="text-align:center;">
										<c:if test="${article.depth>0 }">
											<img src=
											"${pageContext.request.contextPath }/images/level.gif"
											width="${5*article.depth }">
											<img src=
											"${pageContext.request.contextPath }/images/re.gif">
										</c:if>
										<c:if test="${article.depth == 0 }">
											<img src=
											"${pageContext.request.contextPath }/images/level.gif"
											width="${5*article.depth }">
										</c:if>
										<a href= "${pageContext.request.contextPath }/board/content.do?num=
										${article.num}&pageNum=${currentPage}">${article.subject}</a>
										<c:if test="${article.readcount >= 20}">
											<img src=
											"${pageContext.request.contextPath}/images/hot.gif">
										</c:if>
									</td>
									<td>${article.nickname }</td>
									<td>${sdf.format(article.regdate) }</td>
									<td>${article.readcount }</td>
									<td>${article.ip }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<a class="btn btn-primary pull-right" 
						href="${pageContext.request.contextPath}/board/writeForm.do">글쓰기</a>
				<c:if test="${count>0 }">
					<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }"/>
					<c:set var="pageCount" value="${count / pageSize + imsi }"/>
					<c:set var="pageBlock" value="${5 }"/>
					<fmt:parseNumber var="result" value="${currentPage / pageBlock }"
					integerOnly="true"/>
					<c:set var="startPage" value="${result * pageBlock+1 }"/>
					<c:set var="endPage" value="${startPage + pageBlock-1 }"/>
					
					<c:if test="${endPage > pageCount }">
						<c:set var="endPage" value="${pageCount }"/>
					</c:if>
					<div class="text-center">
						<ul class="pagination">
						<c:if test="${startPage > pageBlock }">
							<li><a href="${pageContext.request.contextPath }/board/list.do?pageNum=
							${startPage - pageBlock}">이전</a></li>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<li><a class="text text-center" 
							href="${pageContext.request.contextPath }/board/list.do?pageNum=${i}">${i }</a></li>
						</c:forEach>
						<c:if test="${endPage < pageCount }">
							<li><a href="${pageContext.request.contextPath }/board/list.do?pageNum=
							${startPage + pageBlock}">다음</a></li>
						</c:if>
						</ul>
					</div>
				</c:if>
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