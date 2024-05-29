<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#box {
		display: flex;
		justify-content: space-between; /* 두 div 사이의 공간을 고르게 분배 */
	}
	#box > div {
		flex: 1; /* 각 div가 동일한 너비를 가짐 */
		margin: 10px; /* div 사이의 간격을 위한 여백 */
	}
	table {
		width: 100%;
		border-collapse: collapse;
	}
	th, td {
		border: 1px solid #ddd;
		padding: 8px;
	}
	th {
		background-color: #f2f2f2;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<h1>mypage</h1>
	<hr>
	<div id="box">
		<div>
			<h2>내 주문 내역</h2>
			<form action="ordersDelete" method ="post">
			<table>
			<thead>
			<tr>
			<th><input type="checkbox" id="chk_orderAll" onclick="toggleCheckboxes('chk_orderAll', 'orders_id')"></th><th>사진</th><th>상품명</th><th>수량</th><th>주문날짜</th><th>배송상태</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="dto" items="${orderList }">
			<tr>
			<td><input type="checkbox" name="orders_id" value="${dto.order_id }"></td>
			<td><img src="/uploads/${dto.img_url }" width="200px" height="150px"></td>
			<td>${dto.product_name }</td>
			<td>${dto.quantity }</td>
			<td>${dto.order_date }</td>
			<td>
			${dto.status }
				<c:if test='${dto.status eq "준비중" }'>
				<a href="orderDelete?order_id=${dto.order_id}">삭제</a>
				</c:if>
			</td>
			</tr>
			</c:forEach>
			</tbody>
			</table>
			<button>한꺼번에삭제</button>
			</form>
		</div>
		<div>
			<h2>내 장바구니</h2>
			<form action="cartsOrder" method ="post">
			<table>
			<thead>
			<tr>
			<th><input type="checkbox" id="chk_cartAll" onclick="toggleCheckboxes('chk_cartAll', 'carts_id')"></th><th>사진</th><th>상품명</th><th>수량</th><th>등록날짜</th><th>주문</th><th>삭제</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="dto" items="${cartList }">
			<tr>
			<td><input type="checkbox" name="carts_id" value="${dto.cart_id }"></td>
			<td><img src="/uploads/${dto.img_url }" width="200px" height="150px"></td>
			<td>${dto.product_name }</td>
			<td>${dto.quantity }</td>
			<td>${dto.reg_date }</td>
			<td><a href="cartOrder?cart_id=${dto.cart_id}">주문</a></td>
			<td><a href="cartDelete?cart_id=${dto.cart_id}">삭제</a></td>
			</tr>
			</c:forEach>
			</tbody>
			</table>
			<button>한꺼번에주문</button>
			</form>
		</div>

	</div>


</body>

<script>

    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>
<script>
    function toggleCheckboxes(masterCheckboxId, checkboxName) {
        var masterCheckbox = document.getElementById(masterCheckboxId);
        var checkboxes = document.getElementsByName(checkboxName);
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = masterCheckbox.checked;
        }
    }

    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>	

</html>