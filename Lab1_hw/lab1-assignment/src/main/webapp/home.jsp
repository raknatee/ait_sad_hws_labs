<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/employee.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>


	<div class=main_grid>
		<c:forEach items="${employeeData}" var="e">
			<div class="container box_shadow ${ (e.value-e.salary>0)?"positive_net_value":"minus_net_value"}">
				<p><c:out value="${e.id}"/> | <c:out value="${e.fullName}"/></p>
				<p>Gender: <c:out value="${e.gender}"/></p>
				<p>Address: <c:out value="${e.address}"/></p>
				<p>Salary: <c:out value="${e.salary}"/> | Value: <c:out value="${e.value}"/></p> 
				<p>Net: <c:out value="${e.value-e.salary }"/>
				<p>Position Level: <c:out value="${e.positionLevel}"/></p>
				<a href="/employee/form?uid=${e.id}"><img class="my_icon1 edit_icon" alt="edit" src="icon/edit.png"></a>
				<a href="/employee/delete?uid=${e.id} "><img class="my_icon1 del_icon" alt="edit" src="icon/delete.png"></a>
			</div>
		</c:forEach>
	</div>
</body>
</html>