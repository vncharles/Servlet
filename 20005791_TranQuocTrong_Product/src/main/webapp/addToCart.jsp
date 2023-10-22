<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Add to cart</h1>
<button><a href="ProductController">Go back</a> </button> <br/><br/>
<form action="ProductController" method="post">
	<input type="hidden" name="command" value="DONE_ADD_TO_CART" />
	<input type="hidden" name="productId" value="${PRODUCT.id }" />
	<label>Name: </label> <p>${PRODUCT.name}</p>
	<label>Price: </label> <p>${PRODUCT.price}</p>
	<label>Quantity: </label> <input type="text" name="quantity" />
	
	<input type="submit" value="Save" />
</form>

</body>
</html>