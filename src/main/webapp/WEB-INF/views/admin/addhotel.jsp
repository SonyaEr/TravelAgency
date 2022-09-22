<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage = "true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="apple-touch-icon" sizes="76x76" href="img/apple-icon.png">
<link rel="icon" type="image/png" href="img/favicon.png">
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
<form:form method="POST" modelAttribute="hotelSave" action="saveHotel">
	<td></td>
	<td><form:input path="name" /> <form:errors path="name" cssClass="error" /></td>
	<td>
		<select path="country.id">
			<option value="-1">Выберите страну</option>
			<c:forEach items="${countrys}" var="country">
				<option value="${country.id}">${country.name}</option>
			</c:forEach>
		</select>
	</td>
	<td>
		<select class="form-control" id="city" name="city_id" path="city_id">
			<option value="-1">Выберите город</option>
			<c:forEach items="${citys}" var="city">
				<option value="${city.id}">${city.name}</option>
			</c:forEach>
		</select>
	</td>
	<td><form:input path="stars" /> <form:errors path="stars" cssClass="error" /></td>
	<td><form:input path="food" /> <form:errors path="food" cssClass="error" /></td>
	<td><form:input path="href" /> <form:errors path="href" cssClass="error" /> </td>
	<td><form:input path="description" /> <form:errors path="description" cssClass="error" /> </td>
	<td><form:input path="address" /> <form:errors path="address" cssClass="error" /> </td>
	<td><form:input path="tel_num" /> <form:errors path="tel_num" cssClass="error" /> </td>
	<td><input type="submit" value="<spring:message code="save"/>" /></td>
</form:form>
</body>
</html>