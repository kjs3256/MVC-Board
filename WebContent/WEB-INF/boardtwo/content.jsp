<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 스타일시트 -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<title>${article.subject}</title>
<script>
function confirmDelete(num, pageNum){
	var msg = confirm("정말 삭제하시겠습니까?");
	if(msg == true){
		alert("삭제되었습니다.");
		location.href="deleteForm.do?num="+num+"&pageNum="+pageNum;
	}
}
function commentDelete(num, pageNum, comment_num){
	var msg = confirm("정말 삭제하시겠습니까?");
	if(msg == true){
		alert("삭제되었습니다.");
		location.href="comm_Del.do?num="+num+"&pageNum="+pageNum+"&comment_num="+comment_num;
	}
}
function fileDelete(filename,num,pageNum){
	var msg = confirm("정말 삭제하시겠습니까?");
	if(msg == true){
		alert("삭제되었습니다.");
		location.href="deleteFile.do?filename="+filename+"&num="+num+"&pageNum="+pageNum;
	}
}
function onDownload(num){
	var o = document.getElementById("ifrm_filedown");
	o.src = "download.do?num="+num;
}
</script>
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
		<!-- 글내용 -->
		<div class="container">
			<div class="row">
				<h3>${article.subject }</h3>
				<h4><small>${article.nickname } | ${sdf.format(article.regdate) } | 조회 : ${article.readcount }</small></h4>
				<hr/>
				<iframe id="ifrm_filedown" style="position:absolute; z-index:1; visibility:hidden;"></iframe>
				<div class=container>
					<div class = "row">
						<table class="table table-striped text-left">
							<tr>
								<c:if test="${article.filename != null }">
								<td><a href="#" onClick="onDownload('${article.num}')">${article.filename }</a>
									<c:if test="${loginID != null && loginID.equals(article.id) }">
									<input type="image" name="deleteFile" style="width:12px; height:12px; border=0;" 
									onClick="return fileDelete('${article.filename }',${article.num },${pageNum })" 
									src="${pageContext.request.contextPath }/images/deletebutton.png">
									</c:if>
								</td>
								</c:if>
								<c:if test="${article.filename == null }">
								<th style="text-align:center;">첨부된 파일이 없습니다.</th>
								</c:if>
							</tr>
							<tr>
								<td style="font-size:15px;">${article.content}</td>
							</tr>
						</table>
						<c:if test="${article.filename != null && image}">
							<table>
								<tr>
									<td>									
										<img src="${path }/${article.filename}">
									</td>
								</tr>
							</table>
						</c:if>
						<c:if test="${article.filename != null && video}">
							<table>
								<tr>
									<td>									
										<video src="${path }/${article.filename}" width="1000" height="500" controls></video>
									</td>
								</tr>
							</table>
						</c:if>
					</div>
				</div>
				<div class=container style="margin-top:100px;">
					<form id="commentForm" name="commentForm" method="post" action="${pageContext.request.contextPath}/board/comm_In.do">
						<input type="hidden" name="num" value=${num }>
						<input type="hidden" name="pageNum" value="${pageNum }">
						<div>
						 	<div>
								<span><strong>Comments(댓글 개수: ${count })</strong></span><span id="cCnt"></span>
								<c:if test="${count == 0 }">
								<div class="text-center">
									<h3>아직 댓글이 없습니다.</h3>
								</div>
								</c:if>
								<c:if test="${count > 0 }">
								<div>
									<!-- 닉네임, 작성일, 댓글 내용, ip -->
									<table class="table">
									<c:forEach var = "comment" items="${commList }">
										<tr>
											<th style="width:150px; background-color:#dddddd">${comment.comment_nickname }&nbsp;&nbsp;|&nbsp;&nbsp; ${sdf.format(comment.comment_regdate) } &nbsp;&nbsp;<br><small>${comment.comment_ip }</small></th>
											<td class="text-left">${comment.comment_content }</td>
											<c:if test="${loginID != null && loginID.equals(comment.id) }">
												<td><input type="button" class="btn btn-sm btn-danger pull-right" 
														onClick="return commentDelete(${num },${pageNum },${comment_num })" value="댓글삭제"></td>
											</c:if>
										</tr>
									</c:forEach>
									</table>
								</div>
								</c:if>
								<div>
									<table class="table">
										<tr>
											<c:if test="${loginID != null }">
												<td colspan="3"><textarea name="comment_content" class="form-control" style= "resize:none; height:100px;" placeholder="댓글을 입력하세요"></textarea></td>
											</c:if>
											<c:if test="${loginID == null }">
												<td colspan="3"><textarea name="comment_content" class="form-control" style= "resize:none; height:100px;" placeholder="로그인을 해야 댓글을 쓸 수 있습니다." readonly></textarea></td>
											</c:if>
										</tr>
										<tr>
											<td colspan="3"><input type="button" class="btn btn-primary" value="댓글 달기" onClick="commentCheck()"></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</form>
					
				</div>
				
				<form method="post" action="${pageContext.request.contextPath}/board/deleteForm.do" name=confirmForm>
					<a href="${pageContext.request.contextPath}/board/list.do?pageNum=${pageNum}" class="btn btn-default">목록</a>
					<c:if test="${loginID != null && loginID.equals(article.id)}">
					<a href="${pageContext.request.contextPath}/board/updateForm.do?num=${article.num }&pageNum=${pageNum}" class="btn btn-warning">수정</a>
					<input type="button" class="btn btn-danger" onClick="return confirmDelete(${article.num },${pageNum })" value="삭제">
					</c:if>
					<input type = "button" value="답글" class="btn pull-right"
									onClick="document.location.href=
										'${pageContext.request.contextPath}/board/writeForm.do?num=${article.num }&ref=${article.ref }&step=${article.step }&depth=${article.depth }'">
				</form>
			</div>
		</div>
	</section>
	<footer style="margin-top:30px; padding:30px; text-align:center; background-color:gray;">
		<h4 style="color:white;">JS Blog</h4>
	</footer>
<!-- 애니메이션 담당 JQuery -->
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 JS -->
<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
<!-- JS -->
<script src="${pageContext.request.contextPath }/js/script.js"></script>
</body>
</html>