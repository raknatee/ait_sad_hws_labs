<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>User</title>
</head>
<body>
<h1>User Page</h1>

<p>Your Employee Name: <c:out value="${user.emp.name}"/></p>
<p>Your Employee Level: <c:out value="${user.emp.level}"/></p>
<p>Your base Salary: <c:out value="${user.emp.baseSalary}"/></p>
<p>Your Net Salary: <c:out value="${user.emp.getSalary()}"/></p>
<hr>
<p>Your Birthday: <c:out value="${user.emp.birthday}"/></p>
<form action="/user/edit_bdo" method="post">
<label>edit birthday</label> 
		<input type="hidden" name="id" value="${user.id}">
		<input type="date" name="birthday"> 
<input type="submit" value="Save">
</form>
<hr>
<h1>Address</h1>
<form action="/user/add_address" method="post">
<label>city</label>
		 <input type="text" name="city"> 
		<br>
		<label>street</label>
		 <input type="text" name="street"> 
		<br>
		<label>houseNo</label>
		 <input type="text" name="houseNo"> 
		<br>
		<label>zipcode</label>
		 <input type="text" name="zipcode"> 
		 <input type="submit" value="Add more address">
</form>
<hr>
<c:forEach items="${user.emp.addresses}" var="obj">
	<div>
	<h3>Address ID</h3><p><c:out value="${obj.id}"/></p>
	<h3>city</h3><p><c:out value="${obj.city}"/></p>
	<h3>street</h3><p><c:out value="${obj.street}"/></p>
	
	<h3>houseNo</h3><p><c:out value="${obj.houseNo}"/></p>
	<h3>zipcode</h3><p><c:out value="${obj.zipcode}"/></p>
	

	<a href="/user/edit_address?id=${obj.id}">edit this address</a>
	<br>
	<a href="/user/remove_address?id=${obj.id}">remove this address</a>
	<hr>
	</div>
</c:forEach>









</body>
</html>