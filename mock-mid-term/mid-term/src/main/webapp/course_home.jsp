<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Courses</title>
</head>
<body>

<c:if test="${isAdmin}">
	<a href="/admin/add_course">click here to add new course</a>
</c:if>
<c:forEach items="${list_course}" var="obj">
	<div>
	<p><c:out value="${obj.name}"/></p>
	<p><c:out value="${obj.disciplines}"/></p>
	<p><c:out value="${obj.type}"/></p>
	<p><c:out value="${obj.dateOffered}"/></p>
	<p><c:out value="${obj.revenueGenerated}"/></p>
	<c:if test="${isAdmin}">
	<a href="/admin/edit_course?id=${obj.id}">edit this course</a>
	</c:if>

	<hr>
	</div>
</c:forEach>

<c:forEach items="${list_enrolledCourse}" var="obj">
	<div>
	<p><c:out value="${obj.name}"/></p>
	<p><c:out value="${obj.disciplines}"/></p>
	<p><c:out value="${obj.type}"/></p>
	<p><c:out value="${obj.dateOffered}"/></p>
	<p><c:out value="${obj.revenueGenerated}"/></p>
	<c:if test="${isAdmin}">
	<a href="/admin/edit_course?id=${obj.id}">edit this course</a>
	</c:if>
	<c:if test="${!isAdmin}">
	<a href="/unenroll?id=${obj.id}">unenroll this course</a>
	</c:if>
	<hr>
	</div>
</c:forEach>


<c:forEach items="${list_unenrolledCourse}" var="obj">
	<div>
	<p><c:out value="${obj.name}"/></p>
	<p><c:out value="${obj.disciplines}"/></p>
	<p><c:out value="${obj.type}"/></p>
	<p><c:out value="${obj.dateOffered}"/></p>
	<p><c:out value="${obj.revenueGenerated}"/></p>
	<c:if test="${isAdmin}">
	<a href="/admin/edit_course?id=${obj.id}">edit this course</a>
	</c:if>
	<c:if test="${!isAdmin}">
	<a href="/enroll?id=${obj.id}">enroll this course</a>
	</c:if>
	<hr>
	</div>
</c:forEach>








</body>
</html>