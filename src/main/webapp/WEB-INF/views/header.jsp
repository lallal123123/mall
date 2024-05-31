<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/">홈화면</a>
<sec:authorize access="isAnonymous()">
<a href="/regForm">회원가입</a>
<a href="/login">로그인</a>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')" var="isUser">
<a href="/admin/productRegForm">상품등록</a>
<a href="/admin/mypage">관리자모드</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<a href="/members/mypage">마이페이지</a>
<a href="/logout">로그아웃</a>
<sec:authentication property="principal.name"/>님 환영합니다.
</sec:authorize>




</body>
</html>