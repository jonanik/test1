<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<tr>
<th>BID</th>
<th>BNAME</th>
<th>BTITLE</th>
<th>bContent</th>
<th>bDate</th>
<th>bHit</th>
</tr>
<c:forEach var="list" items="${list}">
<tr>
<td>${list.bId}</td>
<td>${list.bName}</td>
<td>${list.bTitle}</td>
<td>${list.bContent}</td>
<td>${list.bDate}</td>
<td>${list.bHit}</td>
</tr>


</c:forEach>

</table>


</body>
</html>