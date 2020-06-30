


function joinCheck(){
	var nameCheck = /^[가-힣]{2,6}$/;
	var idCheck=/^(?=.*[a-zA-Z])(?=.*[0-9]).{4,15}$/;
	var pwCheck=/^(?=.*[a-zA-Z])(?=.*[0-9]).{4,20}$/;
	var emailCheck=/^[a-zA-Z0-9]{3,20}$/
	var phoneCheck=/^[0-9]{4}$/
	var pwReCheck=joinForm.memPwCheck.value;
	
	if(joinForm.memName.value==""){
		alert("이름을 입력하세요");
		joinForm.memName.focus();
		return false;
	}
	if(!(nameCheck.test(joinForm.memName.value))){
		alert("이름은 한글로 6자 이하입니다.");
		joinForm.memName.value="";
		joinForm.memName.focus();
		return false;
	}
	if(joinForm.memId.value==""){
		alert("아이디를 입력하세요.");
		joinForm.memId.focus();
		return false;
	}
	if(!(idCheck.test(joinForm.memId.value))){
		alert("아이디는 영문,숫자 혼용하여 4 ~ 15자를 입력해주세요.");
		joinForm.memId.focus();
		return false;
	}
	if(joinForm.memPw.value==""){
		alert("비밀번호를 입력하세요.");
		joinForm.memPw.focus();
		return false;
	}
	if(!(pwCheck.test(joinForm.memPw.value))){
		alert("비밀번호는 영문,숫자 혼용하여 4 ~ 20자를 입력해주세요.");
		return false;
	}
	if(joinForm.memPwCheck.value==""){
		alert("비밀번호를 확인해주세요.");
		joinForm.memPwCheck.focus();
		return false;
	}
	if(!(joinForm.memPw.value==pwReCheck)){
		alert("비밀번호가 일치하지 않습니다. 다시한번 확인해주세요.");
		joinForm.memPwCheck.focus();
		return false;
	}
	if(joinForm.email1.value==""){
		alert("이메일을 입력해주세요.");
		joinForm.email1.focus();
		return false;
	}
	if(!(emailCheck.test(joinForm.email1.value))){
		alert("이메일은 영문과 숫자를 이용하여 3~20자로 입력가능합니다.");
		joinForm.email1.focus();
		return false;
	}
	if(joinForm.phone2.value=="" &&joinForm.phone3.value==""){
		alert("핸드폰 번호를 입력해주세요.");
		joinForm.email2.focus();
		return false;
	}
	
	
	alert("회원가입완료");
	
	joinForm.submit();
}

function selectChange(){
	if(!(joinForm.emailSelect.value=="1")){
		joinForm.email2.value=joinForm.email3.value;
		return true;
	}
}