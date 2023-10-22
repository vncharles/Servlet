<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ListProductsServlet" method="get">
	<input type="hidden" name="command" value="ADD" />

	<label>Ten san pham</label>	<br><input name="c" type="text"><br>
	<label>Mo ta san pham</label>	<br><input name="productDes" type="text"><br>
	<label>Gia</label>	<br><input name="productPrice" type="text"><br>
	
	<input type="Submit" value="Save">
</form>
<p>
			<a href="ListProductsServlet">Back to List</a>
		</p>
</body>
</html>