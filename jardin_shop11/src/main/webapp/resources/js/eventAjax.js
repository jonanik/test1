/**
 * 
 */
//댓글 갯수
function replyCnt(eventNo){
	$.ajax({
		type:'post',
		url:'./replyCnt',
		data:{
			eventNo:eventNo
		},
		success:function(data){
			$("#replyCnt").html(data);
		},
		error:function(request,status,error){
			alert("댓글 개수 불러오기 실패");
		}
	});
}

//댓글 리스트 불러오기
function replyList(eventNo,memId){
$.ajax({
	type:'post',
	url:'./replyList',
	data:{
		eventNo:eventNo
	},
	success:function(data){
		var html="";
		for(var i=0;i<data.length;i++){
	html+='<form name="replyForm" id="replyFrom">';
	html+='<ul>';
	html+='<li class="name">'+data[i].memId+ '<span>['+data[i].replyDate+']</span></li>';
	html+='<li class="txt">'+data[i].content+'</li>';
	if(data[i].memId==memId){
	html+='<li class="btn '+data[i].replyNo+'"><c:out value="${memId}"><a href="javascript:;" class="modib rebtn" onclick="replymodi('+data[i].replyNo+')">수정</a> <a href="javascript:;" onclick=deleteConfirm('+data[i].replyNo+') class="rebtn">삭제</a></li>';
	html+='</ul>';
	html+='<ul id="'+data[i].replyNo+'" class="modiwzone" style="display: none;">';
	html+='<li class="name">'+data[i].memId+'<span>['+data[i].replyDate+']</span></li>';
	html+='<li class="txt"><textarea name="content" id="content'+data[i].replyNo+'" class="replyType">'+data[i].content+'</textarea><input type="text" hidden="hidden" value="'+data[i].content+'" name="'+data[i].replyNo+'"></li>';
	html+='<input type="hidden" name="replyNo" value="'+data[i].replyNo+'">';
	html+='<input type="hidden" name="eventNo" value="'+data[i].eventNo+'">';
	html+='<li class="btn"><button href="javascript:;" onclick="replyUpdate(this.form)" class="rebtn">완료</button>';
	html+='<a href="javascript:;" class="rebtn reset_re" onclick=replyReset("'+data[i].replyNo+'")>취소</a></li>';
	html+='</ul>';	
	}else{
		html+='</ul>';	
	}
//	html+='<li class="btn '+data[i].replyNo+'"><a href="javascript:;" class="modib rebtn" onclick="replymodi('+data[i].replyNo+')">수정</a> <a href="javascript:;" onclick=deleteConfirm('+data[i].replyNo+') class="rebtn">삭제</a></li>';
	html+='</form>';
	
		}
		$("#replyList").html(html);	
	},
	error:function(request,status,error){
		alert("리스트 불러오기 실패");
	}
	
});
}

//댓글 삭제
function deleteConfirm(replyNo){
	if(confirm("정말 삭제하시겠습니까?")==true){
		
		$.ajax({
			type:'post',
			url:'./replyDelete',
			data:{replyNo:replyNo},
			success:function(){
				alert("삭제되었습니다.");
				location.reload();
			},
			error:function(request,status,error){
				alert("실패")
			}
		});
	}else{
		return;
	}
}

//로그인 체크후 댓글 작성
function loginCheck() {
	var loginCheck = eventReply.memId.value;
	if (loginCheck == "") {
		alert("로그인후 작성 가능합니다.");
		return false;
	} else {
		//    	  		var bTitle1 = $('#bTitle').val();
		//    	  		var bContent1 = $('#bContent').val();
		//    	  		var bName1 = $('#bName').val();

		$.ajax({
			type : 'post',
			url : './eventReply', //맵핑 명을적어줌
			data : {
				eventNo : $("#eventNo").val(),
				memId : $("#memId").val(), //--->한개씩 받아올 경우
				replyPw : $("#replyPw").val(),
				content : $("#content").val()
			},
			// :$("#ajaxForm").serialize(), //Form에 있는거 모두 가져오고 심을경우  
			//jsp ->controller 보낼 데이터가 있으면 기입

			success : function(data) {//괄호안에 있는 'data'에 값이 담겨있다.
				alert("댓글이 달렸습니다.");
				replyList();

			},
			error : function(request, status, error) {
				alert("실패");
			}

		});
	}

	eventReply.submit();
}
// 댓글 수정 제위치로 오게 만들기
function replymodi(a){
	
	$(".modiwzone").hide();
	$(".modib").parent().show();
	$("."+a).hide();
	//수정취소일 경우 내용 초기화
	var k=$("input[name="+a+"]").val();
	$("#content"+a).val(k);
	$('#'+a).show();
}
//취소시 내용 사라지게 하기
function replyReset(b){
	$(".modib").parent().show();
	$('#'+b).hide();
	$(".replyType").val="";
}

//댓글 수정
function replyUpdate(replyForm){
	var content=replyForm.content.value;
	var replyNo=replyForm.replyNo.value;
	var eventNo=replyForm.eventNo.value;
// 		var params=$("form[name=replyForm]").serialize();
	$.ajax({
		type:'post',
		url:'./replyUpdate',
		data:{
			content:content,
			replyNo:replyNo,
			eventNo:eventNo
		},
		success:function(data){
			if(data==1){
			alert("댓글 수정 성공");
			location.reload();
			}else{
			alert("댓글 내용 수정 실패");	
			}
			
		},
		error : function(request, status, error) {
			alert("통신 오류");
		}
	
		
	});
}