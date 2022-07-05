<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h2>메인 페이지</h2>
	<p>환영 합니다.</p>
	<p><a href="<c:url value='/register/step1'/>">[회원 가입 하기]</a></p>
	<p><a href="<c:url value='/survey'/>">[설문 조사 하기]</a></p>
</body>
</html>