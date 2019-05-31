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
			</div>
		</nav>
	</header>
	<section>
		<!-- 회원가입 폼 -->
		<div class="container">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
			<!-- 점보트론 -->
				<div class="jumbotron" style="padding-top:20px;">
				<form class="form-horizontal" method="post"
				 action="${pageContext.request.contextPath }/board/joinPro.do" name="join">
				<h3 style="text-align:center;">회원가입</h3>
				<div class="form-group">
					<div class="col-lg-10">
						<input type="text" class="form-control" placeholder="아이디" name="id" maxlength="20" required>
					</div>
					<input type="button" class="btn btn-default" value="중복" onClick="idCheck(this.form.id.value)"/>
				</div>
				<div class="form-group">
					<div class="col-lg-12">
						<input type="password" class="form-control" placeholder="비밀번호" name="pass" maxlength="20" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-12">
						<input type="password" class="form-control" placeholder="비밀번호 확인" name="repass" maxlength="20" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-12">
						<input type="text" class="form-control" placeholder="이메일" name="email" maxlength="50" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-12">
						<input type="text" class="form-control" placeholder="닉네임" name="nickname" maxlength="20" required>
					</div>
				</div>
				<div class="form-group">
					<div class="custom-control custom-radio">
						<input type="radio" class="custom-control-input" id="man" name="gender" value="남자">
						<label class="custom-control-label" for="man">남자</label>
						<input type="radio" class="custom-control-input" id="woman" name="gender" value="여자">
						<label class="custom-control-label" for="woman">여자</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-12">
						<select class="form-control" name="age">
							<c:forEach var = "i" begin="${18 }" end="${51 }">
								<option value=${i }> ${i }살</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-12">
						<select name="loc" class="form-control">
							<option value="no">지정안함</option>
							<option value="서울/경기">서울/경기</option>
							<option value="부산/경남">부산/경남</option>
							<option value="대구/경북">대구/경북</option>
							<option value="강원도">강원도</option>
							<option value="대전/충청">대전/충청</option>
							<option value="광주/전라">광주/전라</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-4" style="float:none; margin:0 auto">
						<input type="button" class="btn btn-success form-control" value="회원가입" 
						onClick="inputCheck()"/>&nbsp;&nbsp;
						<input type="reset" class="btn btn-danger form-control" value="다시쓰기">
					</div>
				</div>
				</form>
				</div>
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