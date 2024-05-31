<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<h1>detail page</h1>
	<hr>
	<div>
		<div id="imgBox">
			<img src="/uploads/${dto.img_url}" width="500px">
		</div>
		<div id="content">
			카테고리 : ${dto.category }<br> 상품명 : ${dto.name }<br> 가격 :
			${dto.price }원<br> 재고 : ${dto.stock }<br> 상품설명 :
			${dto.content }<br>
			<a href="updateForm?id=${dto.product_id }"><button>수정</button></a>
			<a href="delete?no=${dto.product_id }"><button>삭제</button></a>

		</div>
	</div>


</body>
<script>
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>

</html>