<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.item-container {
    width: 100%; /* 부모 요소의 전체 너비를 사용 */
}

.dto {
    width: calc(33.33% - 20px); /* 3분할. 여백 고려하여 너비 설정 */
    float: left; /* 왼쪽으로 정렬 */
    margin: 10px; /* 여백 설정 */
    box-sizing: border-box; /* 너비와 높이를 요소의 경계선까지 포함 */
}
</style>
</head>
<body>
 <jsp:include page="/WEB-INF/views/header.jsp" />
<h1>main page</h1>
<div class="item-container">
<c:forEach var="dto" items="${list }">
<c:if test="${dto.status eq 1 }">
<div class="dto">
	카테고리:${dto.category }<br>
	<a href="members/detail?id=${dto.product_id }"><img src="/uploads/${dto.img_url}" width="100%" height="300px"></a>
	상품명:${dto.name }<br>
	가격:${dto.price }<br>
</div>
</c:if>
</c:forEach>

</body>
<script>
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>
</html>