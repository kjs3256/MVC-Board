<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	function checkBox() {
		var check_Box = document.getElementsByName("formType"); //체크박스 name
		for (i = 0; i < check_Box.length; i++) {
			 var disign = document.getElementById([i+1]); // 보여질내용
			 if(check_Box[i].checked == true ){
			  disign.style.display = "block";
			 }else{
			  check_Box[i].checked = false;
			  disign.style.display = "none";
			 }
		 }
	}
</script>
<!-- 스타일시트 -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<title>진수 Blog</title>
</head>
<body>
	<c:if test="${loginID == null}">
		<script>
			alert("로그인을 해주세요!!");
			location.href = "list.do?pageNum=1";
		</script>
	</c:if>
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
				<form method="post" name="writeForm" 
				enctype="multipart/form-data"
				action="${pageContext.request.contextPath }/board/writePro.do"
				onsubmit="return writeSave()">
					<input type="hidden" name="num" value="${num}" />
					<input type="hidden" name="ref" value="${ref}" />
					<input type="hidden" name="step" value="${step}" />
					<input type="hidden" name="depth" value="${depth}" />
					<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
						<thead>
							<tr>
								<th colspan="2" style="background-color:#eeeeee; text-align:center;"><h3>글 쓰기</h3></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
								<c:if test="${num == 0 }">
									<input type="text" class="form-control" placeholder="제목" name="subject" maxlength="50"/>
								</c:if>
								<c:if test="${num != 0 }">
									<input type="text" name="subject" class="form-control" placeholder="답변 제목" value="[답변]" />
								</c:if>
								</td>
							</tr>
							<tr>
								<td><textarea class="form-control" placeholder="내용" name="content" maxlength="2048" style= "resize:none; height:350px;"></textarea></td>
							</tr>
							<tr>
								<td class="text-left">				
									<input type="file" name="filename">
								</td>
							</tr>
						</tbody>
					</table>
					<input type="submit" class="btn btn-success pull-right" value="글쓰기"/>
					<input type="button" class="btn btn-default" value="목록" 
										onClick="window.location='${pageContext.request.contextPath}/board/list.do'"/>
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