<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="ListProductsServlet" method="Get">
		<input type="hidden" name="command" value="BUY" />
		 <input	type="hidden" name="productId" value="${PRODUCT.id}" />
		 <label>Product Name</label>
		 <P>
		 <input type="text" name="Name"  value="${PRODUCT.Name}" />
		 </P> 
		 
		<P>
		<input type="text" name="quantity"  value="${PRODUCT.Name}" />
		</P>	
	</form>

</body>
</html>