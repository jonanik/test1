<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">


</script>
</head>
<body>
	<form action="eventWrite" name="eventWriteForm" enctype="multipart/form-data" method="post">
		<table border="1">
			<tr>
				<th>이벤트 제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>이벤트 내용</th>
				<td><textarea rows="5" cols="40"  name="content"></textarea></td>
			</tr>
			<tr>
				<th>썸네일</th>
				<td><input type="file" name="multi"></td>
			</tr>
			<tr>
				<th>이벤트 내용 이미지</th>
				<td><input type="file" name="multi"></td>
			</tr>
			<tr>
				<th>이벤트 기간</th>
				<td><input type="date" name="startDate"> ~ <input
					type="date" name="endDate"></td>
			</tr>
		</table>
			<button >작성</button>
			<button type="reset" >취소</button>
	</form>
</body>
</html>