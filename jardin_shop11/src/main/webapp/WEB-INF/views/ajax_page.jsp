<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

table, tr, td {
	border: 1px solid black;
	width: 1100px;
}
textarea{width:1100px; row:"3"; cols:"30";}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	//jquery 자동 실행구문이다. 이부분을 다 실행하고 다시 이페이지를 로딩함
	$(function() {
		ajax_view(); //여기에 호출하면 자동으로 실행하게 된다.
	})

	
	function ajax_Form_insert(){
		
// 		var bTitle1 = $('#bTitle').val();
// 		var bContent1 = $('#bContent').val();
// 		var bName1 = $('#bName').val();
		
		$.ajax({
			type:'post',
			url:'./ajax_insert', //맵핑 명을적어줌
			data: {
					bTitle:$("#bTitle").val(),
					bContent:$("#bContent").val(),   //--->한개씩 받아올 경우
					bName:$("#bName").val()
			}, 
// :$("#ajaxForm").serialize(), //Form에 있는거 모두 가져오고 심을경우  
			 //jsp ->controller 보낼 데이터가 있으면 기입
			

			success:function(data) {//괄호안에 있는 'data'에 값이 담겨있다.
				alert("입력 성공");
				ajax_view();
			},
			error:function(request, status, error) {
				alert("실패");
			}

		});
	}
	
	
// 	리스트 가져오기 ajax
	function ajax_view() { //클릭해서 이 펑션을 실행해주게 한다.
	
		$.ajax({
			type : 'post',
			url : './ajax_view', //맵핑 명을 적어줌
			data : {}, //jsp ->controller 보낼 데이터가 있으면 기입
			contentType : "application/json; charset=utf-8",

			success : function(data) {//괄호안에 있는 'data'에 값이 담겨있다.
				alert("데이터 가지고 오는데 성공");
			//ajaxname 호출
				var name1 = '<span>'+data.ajaxname+'</span>';
				$("#ajaxname").html(name1);
				
				//data.list[i].bId,data.list[i].bTitle리스트 호출
				var html = "";
				
				for(var i=0; i<data.list.length;i++){
				html += '<tr>'
				html += '<td>' + data.list[i].bId + '</td>';//만약에 컨트롤러에서 리스트로 넘길경우
				html += '<td>' + data.list[i].bTitle + '</td>';//data 자체가 list가 되기 때문에
				html += '<td>' + data.list[i].bContent + '</td>';//data[i].bId 이런식으로 바꿀 수 있다.
				html += '<td>' + data.list[i].bName + '</td>';
				html += '</tr>'	
				}
				
				$("#listForm").html(html);
			},
			error : function(request, status, error) {
				alert("실패");
			}

		});

	}
</script>
</head>
<body>
<form  id="ajaxForm" name="ajaxForm" method="post">
<table>
<tr>
<td>제목</td>
<td><input type="text" name="bTitle" id="bTitle"></td>
</tr>
<tr>
<td>내용</td>
<td><textarea name="bContent" id="bContent"></textarea></td>
</tr>
<tr>
<td>이름</td>
<td><input type="text" name="bName" id="bName"></td>
</tr>
<tr>
<td></td>
<td>
<button onclick="ajax_Form_insert()">전송</button>
<input type="reset" value="취소" ></td>
</tr>
</table>
</form>






	<h2>게시판</h2>
	<p id="ajaxname"><!-- ajaxname호출되는 부분 --></p>
	<table>
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>내용</td>
				<td>이름</td>
			</tr>
		</thead>
		<tbody id="listForm">
				<!-- ajax에서 데이터를 뿌려줌  -->
		</tbody>
	</table>
</body>
</html>