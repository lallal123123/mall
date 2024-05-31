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
	
	<div id="box">
		<div>
			<jsp:include page="/admin/list"/>
		</div>
		<div>
			
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