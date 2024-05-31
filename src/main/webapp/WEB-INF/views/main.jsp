<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Main Page</title>

</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<header>
		<h1>Main Page</h1>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous">
	</header>

	<div class="container text-center">
		<div class="row row-cols-3">

			<c:forEach var="dto" items="${list}">
				<c:if test="${dto.status eq 1}">
					<div class="col">
						<img src="/uploads/${dto.img_url}" class="img-thumbnail rounded "
							alt="${dto.name}"  height="300px">
						<div class="item-content">
							<h3>${dto.name}</h3>
							<p>Category: ${dto.category}</p>
							<p>Price: ${dto.price}</p>
							<a href="members/detail?id=${dto.product_id}">View Details</a>
						</div>

					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>


</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</html>