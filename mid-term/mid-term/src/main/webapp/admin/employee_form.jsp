<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="${form_action}" method="post">
		<c:if test="${not empty e.id }"><input type="hidden" name="id" value="${e.id}"> </c:if>
		
		<br>
		
		<label for="level">Level</label> 
		<input type="radio" name="level" value="C1" ${e.level=="C1"?"Checked":""}><label>C1</label>
		<input type="radio" name="level" value="C2" ${e.level=="C2"?"Checked":""}><label>C2</label> 
		<input type="radio" name="level" value="C3" ${e.level=="C3"?"Checked":""}><label>C3</label>
			
		<br>
		<label
			for="name">Name
		</label> 
		<input type="text" name="name" id="name" value="${e.name}"> 
		
		<br>
	
		<br>
		
		<label>Base Salary</label>
		<input type="number" name="baseSalary" id="baseSalary"
			value="${e.baseSalary}">

		<input type="submit" value="Save">

	</form>
</body>
</html>