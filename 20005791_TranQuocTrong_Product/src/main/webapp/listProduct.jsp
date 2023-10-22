<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List product</title>
</head>
<body>

<h1>List product</h1>
<input type='button' value='Add Product'
		onclick="window.location.href='form_product.jsp'; return false">
		
<c:url var="viewCart" value="ProductController">
	<c:param name="command" value="CART" />
</c:url>
<button><a href="${viewCart}">View cart</a> </button>
		
<table>
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Price</th>
		<th>Action</th>
	</tr>
	<c:forEach var="item" items="${LIST_PRODUCT}">
		<c:url var="detail" value="ProductController">
				<c:param name="command" value="DETAIL" />
				<c:param name="productId" value="${item.id}" />
		</c:url>
		
		<c:url var="addToCart" value="ProductController">
			<c:param name="command" value="ADD_TO_CART" />
			<c:param name="productId" value="${item.id}" />
		</c:url>
		
		<c:url var="getFormProduct" value="ProductController">
			<c:param name="command" value="GET_FORM_PRODUCT" />
			<c:param name="productId" value="${item.id}" />
		</c:url>
		
		<c:url var="deleteProduct" value="ProductController">
			<c:param name="command" value="DELETE" />
			<c:param name="productId" value="${item.id}" />
		</c:url>
			
		<tr>
			<td>${item.id}</td>
			<td>${item.name}</td>
			<td>${item.price}</td>
			<td>
				<button><a href="${detail}">Detail</a> </button>
				<button><a href="${getFormProduct}">Update</a> </button>
				<button><a href="${deleteProduct}">Delete</a> </button>
				<button><a href="${addToCart}">Buy</a> </button>
			</td>
		</tr>		
	</c:forEach>
</table>

</body>
</html>