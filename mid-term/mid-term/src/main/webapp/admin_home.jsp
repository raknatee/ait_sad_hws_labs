<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin</title>
</head>
<body>
<a href="/admin/test_user">test add user1</a>
<h1>Admin Page</h1>

<a href="/admin/add_employee">add new User</a>

<c:forEach items="${employees}" var="obj">
	<div>
	<h3>Id</h3><p><c:out value="${obj.id}"/></p>
	<h3>User</h3><p><c:out value="${obj.user}"/></p>
	
	<h3>Name</h3><p><c:out value="${obj.name}"/></p>
	<h3>Level</h3><p><c:out value="${obj.level}"/></p>
	<h3>Birthday</h3><p><c:out value="${obj.birthday}"/></p>
	<h3>baseSalary</h3><p><c:out value="${obj.baseSalary}"/></p>
	<h3>NetSalary</h3><p><c:out value="${obj.getSalary()}"/></p>
	<h3>Addresses</h3><p><c:out value="${obj.addresses}"/></p>

	<a href="/admin/edit_employee?id=${obj.id}">edit this employee</a>


	<hr>
	</div>
</c:forEach>









</body>
</html>