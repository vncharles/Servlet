<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Add product</h1>
<button><a href="ProductController">Go back</a> </button> <br/><br/>

<form action="ProductController" method="post">
	<c:if test="${empty PRODUCT }">
		<input type="hidden" name="command" value="ADD">
	</c:if>
	<c:if test="${not empty PRODUCT }">
		<input type="hidden" name="command" value="UPDATE">
		<input type="hidden" name="productId" value="${PRODUCT.id }">
	</c:if>
	
	
	<label>Name: </label> <input name="name" type="text" value="${PRODUCT.name }"> <br/>
	<label>Decription: </label> <input name="description" type="text" value="${PRODUCT.description }"> <br/>
	<label>Price: </label> <input name="price" type="text" value="${PRODUCT.price }"> <br/>

	<input type="Submit" value="Save">
	
</form>

</body>
</html>