<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
			${dto.price }원<br>
			<sec:authorize access="hasRole('ROLE_MEMBER')" var="isUser">
				<form action="order" id="myform" method="post">
					<c:if test="${(dto.stock) >= 35 }">
				주문개수 : <select name="quantity"><c:forEach begin="1"
								end="30" var="number">

								<option value="${number}">${number}</option>
							</c:forEach></select>
					</c:if>
					<c:if test="${(dto.stock) < 35 }">
					주문개수 : <select name="quantity"><c:forEach begin="1"
								end="${(dto.stock)-5 }" var="number">

								<option value="${number}">${number}</option>
							</c:forEach></select>
					</c:if>
					<input type="hidden" name="product_id" value="${dto.product_id }">
					<button>주문</button>
					<button onclick="cart()">장바구니</button>
					<br>
				</form>
			</sec:authorize>




			상품설명 : ${dto.content }<br>


		</div>
	</div>
	<sec:authorize access="hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')" var="isUser">
		<form action="review" method="post">
			<div id="review">${loginMember.username}</div>
			<input type="hidden" name="product_id"
				value="${dto.product_id}"> 
				<input type="hidden" name="username"
				value="${loginMember.username}">

			<textarea name="content" class="form-control"></textarea>
			<br>
			<button class="btn btn-jobs w-100">리뷰등록</button>
		</form>
	</sec:authorize>
	<hr>
	<div id="reviewList">

		<c:forEach var="review" items="${reviewList}">
			<div class="p-4 border mb-2">
			<c:set var="isOrdered" value="false"/>
			<c:forEach var="order" items="${orderList }">
				<c:if test="${review.username eq order.username }">
					<c:set var="isOrdered" value="true"/>
					</c:if>
			</c:forEach>
			
			<div>
			<c:choose>
				<c:when test="${isOrdered }">
					<p>주문</p>
					</c:when>
					<c:otherwise>
					<p>미주문</p>
					</c:otherwise>
			</c:choose>
			</div>
				<div>${review.content}</div>
				<div class="d-flex justify-content-end text-secondary">
					${review.username} ${review.reg_date}
					<!--<c:if test="${!(empty loginMember) }"> -->
					<c:if test="${review.username eq loginMember.username}">
						<a class="btn btn-sm btn-secondary ms-3"
							href="reviewDelete?review_id=${review.review_id}&product_id=${dto.product_id}">삭제</a>
					</c:if>
					<!--</c:if>-->
				</div>
			</div>
			<hr>
		</c:forEach>
	</div>

</body>
<script>
	//alert("상품아이디는 ${dto.product_id }");
	function cart() {

		document.getElementById('myform').action = "cart";
		document.getElementById('myform').submit(); // 폼 제출

	}
</script>
<script>
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>
</html>