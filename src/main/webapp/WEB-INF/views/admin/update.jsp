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
		<form action="update" method="post" enctype="multipart/form-data">
			<div id="content">
				상품명 : <input type="text" name="name" value=${dto.name }><br>
				가격 : <input type="number" name="price" value=${dto.price }><br>
				재고 : <input type="number" name="stock" value=${dto.stock }><br>
				상품설명 :
				<textarea name="content">${dto.content }</textarea>
				<br> 이미지파일 : <input type="file" name="img_url"><br>
				category : <select name="category">
					<c:forEach var="fruit" items="${fruitList }">
						<option class="option" value="${fruit.name }">${fruit.name }</option>
					</c:forEach>
				</select> <br> 판매여부 : <input type="radio" id="option1" class="status"
					name="status" value="1"> <label for="option1">판매</label> <input
					type="radio" id="option2" class="status" name="status" value="0">
				<label for="option2">품절</label><br>
				<input type="hidden" name="product_id" value="${dto.product_id }">

				<button>상품수정</button>
				</div>
		</form>
	</div>



</body>
<script>
document.addEventListener("DOMContentLoaded", function() {
    let options = document.querySelectorAll(".option");
    let radios = document.querySelectorAll("input[type='radio']");
    
    options.forEach(option => {
        if (option.value == "${dto.category}") {
            option.selected = true;
        }
    });
    
    radios.forEach(radio => {
        if (radio.value == "${dto.status}") {
            radio.checked = true;
        }
    });
});
</script>
<script>
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>

</html>