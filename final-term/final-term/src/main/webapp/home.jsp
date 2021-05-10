<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>

<h1>Welcome ${locking_type} locking</h1>

<c:forEach items="${products}" var="product">

	<div>
	<h1>Product</h1>
<h2>id: <c:out value="${product.id}"></c:out></h2>
<h2>name: <c:out value="${product.name}"></c:out></h2>	
<h2>price: <c:out value="${product.price}"></c:out></h2>
<h2>stock: <c:out value="${product.stock}"></c:out></h2>

	<form action="/${locking_type}" method="post">
	 <input type="hidden" name="productID" value="${product.id }">
	<label for="n_buy">Buy IT!</label>
	<select name= "n_buy" id="n_buy">
	
	
	
	<c:forEach begin="0" end="${product.stock}" varStatus="loop">
		<option value="${loop.index}">${loop.index}</option>
	</c:forEach>
	
	</select>
	<input type="submit" value="Submit">
	</form>
	</div>

</c:forEach>

</html>