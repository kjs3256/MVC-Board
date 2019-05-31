function writeSave(){
	if(document.writeForm.subject.value == ""){
		alert("제목을 입력하세요.");
		document.writeForm.subject.focus();
		return false;
	}
	if(document.writeForm.content.value == ""){
		alert("내용을 입력하세요.");
		document.writeForm.content.focus();
		return false;
	}
}
function idCheck(id){
	if(id == ""){
		alert("아이디를 입력해주세요.");
		document.join.id.focus();
	}
	else{
		var popWidth = 300;
		var popHeight = 200;
		var winHeight = document.body.clientHeight; // 현재 창의 높이
		var winWidth = document.body.clientWidth; //현재 창의 너비
		var winX = window.screenLeft; 	//현재창의 x좌표
		var winY = window.screenTop;	//현재창의 y좌표
		var popX = winX + (winWidth - popWidth)/2;
		var popY = winY + (winHeight - popHeight)/2;
		url = "idCheck.do?id="+id;
		window.open(url, "post","left="+popX+",top="+popY+
				",width="+popWidth+", height="+popHeight);
	}
}
function inputCheck(){
	if(document.join.id.value==""){
		alert("아이디를 입력해주세요.");
		document.join.id.focus();
		return;
	}
	if(document.join.pass.value==""){
		alert("비밀번호를 입력해주세요.");
		document.join.pass.focus();
		return;
	}
	if(document.join.repass.value==""){
		alert("비밀번호를 확인해주세요.");
		document.join.repass.focus();
		return;
	}
	if(document.join.pass.value != document.join.repass.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.join.repass.focus();
		return;
	}
	if(document.join.email.value==""){
		alert("이메일을 입력해주세요.");
		document.join.email.focus();
		return;
	}
	var str = document.join.email.value;
	var atPos = str.indexOf('@');
	var atLastPos = str.lastIndexOf('@');
	var dotPos = str.indexOf('.');
	var spacePos = str.indexOf(' ');
	var commaPos = str.indexOf(',');
	var eMailSize = str.length;
	if(		atPos > 1 && 
			atPos == atLastPos &&
			dotPos > 3 &&
			spacePos == -1 &&
			commaPos == -1 &&
			atPos + 1 < dotPos &&
			dotPos + 1 < eMailSize);
	else{
		alert('E-mail주소 형식이 잘못 되었습니다.\n\r다시 입력해주세요!!!!');
		document.join.email.focus();
		return;
	}
	if(document.join.nickname.value==""){
		alert("닉네임을 입력해주세요.");
		document.join.nickname.focus();
		return;
	}
	if(document.join.nickname.value=="관리자"){
		alert("관리자는 나야 이놈아!");
		document.join.nickname.focus();
		return;
	}
	if(document.join.gender.value==""){
		alert("성별을 체크해주세요.");
		return;
	}
	if(document.join.age.value==""){
		alert("나이를 입력해주세요.");
		document.join.age.focus();
		return;
	}
	if(document.join.loc.value=="no"){
		alert("지역을 선택해주세요.");
		document.join.loc.focus();
		return;
	}
	document.join.submit();	
}
function commentCheck(){
	if(document.commentForm.comment_content.value==""){
		alert("댓글을 입력해주세요.");
		document.commentForm.comment_content.focus();
		return;
	}
	document.commentForm.submit();
}