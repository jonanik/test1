/**
 * 
 */
function logoutCheck(){
	var logout=confirm("로그아웃 하시겠습니까?");
	if (logout){
		alert("로그아웃 합니다.");
		location.href="logout";
	}else{
		return false;
	}
}