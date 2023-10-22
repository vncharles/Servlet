<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.tbl {
	width: 100%;
	border-collapse: collapse;
}

.tbl, tr, td {
	border: 1px solid brown;
}
</style>
</head>
<body>


	<input type='button' value='Add Product'
		onclick="window.location.href='addProduct.jsp'; return false">
	<table class='tbl'>
		<tr>

			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
		</tr>
		<c:forEach var="tempProduct" items="${PRODUCT_LIST}">
			<c:url var="tempLink" value="ListProductsServlet">
				<c:param name="command" value="LIST" />
				<c:param name="productId" value="${tempProduct.id}" />
			</c:url>
			
			<c:url var="tempAddtoCart" value="ListProductsServlet">
				<c:param name="command" value="BUY" />
				<c:param name="productId" value="${tempProduct.id}" />
			</c:url>

			<TR>
				<td>${tempProduct.name}</td>
				<td>${tempProduct.description}</td>
				<td>${tempProduct.price}</td>
				<td>
				<a href="${tempAddtoCart}">Add to Cart</a> 
				
				</td>
			</TR>
			
			
			
		</c:forEach>
	</table>
	
</body>
</html>