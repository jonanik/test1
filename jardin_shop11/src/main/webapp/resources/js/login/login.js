/**
 * 
 */
function logoutCheck(){
	var logout=confirm("로그아웃 하시겠습니까?");
	if (logout){
		alert("로그아웃 합니다.");
		location.href="main";
	}else{
		return false;
	}
}

function loginOk(){
	$.ajax({
		type:'post',
		url:'./loginOk', //맵핑 명을적어줌
		data: {
				memId:$("#memId").val(),
				memPw:$("#memPw").val(),   //--->한개씩 받아올 경우
		}, 
		success:function(data) {//괄호안에 있는 'data'에 값이 담겨있다.
			if(data.memId!=null){
			alert("로그인 성공");
			location.href="main";
			}else if(data.memId==null){
				alert("로그인 실패! 아이디 또는 비밀번호를 확인해주세요.");
				location.reload();
			}
		},
		error:function(request, status, error) {
			alert("통신오류");
		}

	});
	
}