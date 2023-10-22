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

<c:url var="list" value="ProductController">
	<c:param name="command" value="LIST" />
</c:url>

<h1>Detail product</h1>
<button><a href="${list}">Go back</a> </button>
<p>${PRODUCT.name}</p>
<p>${PRODUCT.description}</p>
<p>${PRODUCT.price}</p>

</body>
</html>