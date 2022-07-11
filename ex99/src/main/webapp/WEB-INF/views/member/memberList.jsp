<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.select"/></title>
</head>
<body>
	<h2><spring:message code="member.select"/></h2>
	<form:form modelAttribute="listCommand">
		<p>
			<label><spring:message code="member.from"/> : <form:input path="from"/></label>
			~
			<label><spring:message code="member.to"/> : <form:input path="to"/></label>
			<input type="submit" value="<spring:message code="select"/>">
		</p>
	</form:form>
	<c:if test="${!empty members}">
		<table>
			<tr>
				<th>아이디</th>
				<th>이메일</th>
				<th>이름</th>
				<th>가입일</th>
			</tr>
			<c:forEach items="${members}" var="member">
				<tr>
					<td>${member.id}</td>
					<td><a href="<c:url value='/member/detail/${member.id}'/>">${member.email}</a></td>
					<td>${member.name}</td>
					<td><fmt:formatDate value="${member.registerDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>