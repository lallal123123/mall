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
<a href="/">홈화면</a>
<c:if test="${empty loginMember }">
<a href="/regForm">회원가입</a>
<a href="/loginPage">로그인</a>
</c:if>
<c:if test="${!(empty loginMember)  }">
<c:choose>
<c:when test='${loginMember.role eq "ROLE_ADMIN" }'>
<a href="/admin/productRegForm">상품등록</a>
<a href="/admin/mypage">관리자모드</a>
</c:when>
<c:otherwise>
<a href="/members/mypage">마이페이지</a>
</c:otherwise>
</c:choose>
<a href="/logout">로그아웃</a>
	${loginMember.name }님 환영합니다.
</c:if>

</body>
</html>