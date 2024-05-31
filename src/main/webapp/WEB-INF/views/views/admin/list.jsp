<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <jsp:include page="/WEB-INF/views/header.jsp" />
<h1>regForm page</h1>
<hr>
<table>
<thead>
<tr>
<th>카테고리</th><th>상품명</th><th>가격</th>
</tr>
</thead>
<tbody>

<c:forEach var="dto" items="${list }">
<tr>
<td>${dto.category }</td>
<td><a href="detail?no=${dto.product_id}">${dto.name }</a></td>
<td>${dto.price }</td>
</c:forEach>
</tbody>
</table>

</body>
<script>
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>
</html>