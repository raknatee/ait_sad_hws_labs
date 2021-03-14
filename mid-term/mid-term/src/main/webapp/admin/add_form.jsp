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

	<form  method="post">
		
		<h2>User info</h2>
		<label>Username</label>
		 <input type="text" name="username"> 
		 <label>Password</label>
		 <input type="password" name="password"> 
	
	<h2>Employee info</h2>
		<label>Name</label>
		 <input type="text" name="name"> 
		<br>
		<label for="level">Level</label> 
		<input type="radio" name="level" value="C1" ${e.level=="C1"?"Checked":""}><label>C1</label>
		<input type="radio" name="level" value="C2" ${e.level=="C2"?"Checked":""}><label>C2</label> 
		<input type="radio" name="level" value="C3" ${e.level=="C3"?"Checked":""}><label>C3</label>
			
		<br>
			<label>birthday</label> 
		<input type="date" name="birthday"> 
		
		
			
		<label>Base Salary</label>
		<input type="number" name="baseSalary" id="baseSalary">

		
		<h2>Init Address</h2>
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
		<br>
<input type="submit" value="Save">
	</form>
</body>
</html>