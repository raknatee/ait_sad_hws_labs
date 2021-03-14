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
		<label
			for="name">Name
		</label> 
		<input type="text" name="name" id="name" value="${e.name}"> 
		
		<br>
		<label for="disciplines">disciplines</label> 
		<input type="radio" name="disciplines" value="SCIENCE" ${e.disciplines=="SCIENCE"?"Checked":""}><label>SCIENCE</label>
		<input type="radio" name="disciplines" value="ENGINEERING" ${e.disciplines=="ENGINEERING"?"Checked":""}><label>ENGINEERING</label> 
		<input type="radio" name="disciplines" value="BUSINESS" ${e.disciplines=="BUSINESS"?"Checked":""}><label>BUSINESS</label>
			
		<br>
		<label>type</label> 
		<input type="radio" name="type" value="PAID" ${e.type=="PAID"?"Checked":""}><label>PAID</label>
		<input type="radio" name="type" value="FREE" ${e.type=="FREE"?"Checked":""}><label>FREE</label> 
		
		<br>
		<label>dateOffered</label> 
		<input type="date" name="dateOffered" id="dateOffered" value="${e.dateOffered}"> 
		<br>
		<label>revenueGenerated</label>
		<input type="number" name="revenueGenerated" id="revenueGenerated"
			value="${e.revenueGenerated}">

		<input type="submit" value="Save">

	</form>
</body>
</html>