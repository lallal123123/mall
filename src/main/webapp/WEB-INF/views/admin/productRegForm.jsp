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
<form action="productReg" method="post" enctype="multipart/form-data">
	상품명 : <input type="text" name="name"><br>
	가격 : <input type="number" name="price"><br>
	재고 : <input type="number" name="stock"><br>
	상품설명 : <textarea name="content"></textarea><br>
	이미지파일 : <input type="file" name="img_url"><br>
	category : 	<select name="category">
	<c:forEach var="fruit" items="${fruitList }">
		<option value="${fruit.name }">${fruit.name }</option>
		</c:forEach>
			</select>
			
	<button>상품등록</button>
	
</form>

</body>
<script>
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>
</html>