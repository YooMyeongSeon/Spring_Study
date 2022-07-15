<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<form action="UM" method="post">
		조회할 이메일 : <input type="email" name="email"><br>
		수정 이름 : <input type="text" name="name"><br>
		수정 비밀번호 : <input type="password" name="password"><br>
		<input type="submit" value="수정">
	</form>
</body>
</html>