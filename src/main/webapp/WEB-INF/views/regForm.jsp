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
<h1>regForm page</h1>
<hr>
<form action="reg" method="post">
	아이디 : <input type="text" name="username"><br>
	비밀번호 : <input type="text" name="pw"><br>
	이름 : <input type="text" name="name"><br>
	이메일 : <input type="text" name="email"><br>
	주소 : <input type="text" name="address"><br>
	<input type="hidden" name="role" value ="ROLE_MEMBER">
	<button>회원가입</button>
</form>

</body>
<script>
    var msg = "${msg}";
    if (msg !== "") {
        alert(msg);
    }
</script>
</html>