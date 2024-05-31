<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <jsp:include page="/WEB-INF/views/header.jsp" />
<h1>login page</h1>

	<form action="/loginProc" method="post">
		아이디 : <input type="text" name="username" required><br>
		비밀번호 : <input type="text" name="password" required>
		<button>로그인</button>
	</form>

</body>

<script>
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>
</html>