<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/css/employee.css" rel="stylesheet" />
<link href="/css/employee_form.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>

	<form class="main_form box_shadow" action="/employee" method="post">
		<div class="uid_box">
			<p>ID:${e.id}</p>
			<input type="hidden" name="id" value="${e.id}">
		</div>
		<div class="info_box">
			<label for="fullName"><p class="title">Full Name</p></label> <input
				type="text" name="fullName" id="fullName" value="${e.fullName}">
		</div>
		<div class="info_box">
			<p class="title">Gender</p>
			<div>
				<input type="radio" name="gender" value="Male" id="male_radio"
					${e.gender=="Male"?"Checked":""}><label for="male_radio"><p>Male</p></label>
				<input type="radio" name="gender" value="Female" id="female_radio"
					${e.gender=="Female"?"Checked":""}><label
					for="female_radio"><p>Female</p></label>

			</div>
		</div>

		<div class="info_box">
			<label for="address"><p class="title">Address</p></label> <input
				type="text" name="address" id="address" value="${e.address}">
		</div>

		<div class="info_box">
			<label for="salary"><p class="title">Salary</p></label> <input
				type="number" step="any" name="salary" id="salary" value="${e.salary}">
		</div>
		<div class="info_box">
			<label for="value"><p class="title">Value</p></label> <input
				type="number" step="any" name="value" id="value" value="${e.value}">
		</div>
		<div class="info_box">
			<label for="positionLevel"><p class="title">Position
					Level</p></label> <input type="number" name="positionLevel" id="positionLevel"
				value="${e.positionLevel}">
		</div>
		<div class="submit_box">
			<input type="submit" value="Save"> <a href="/">Back</a>
		</div>

	</form>
</body>
</html>