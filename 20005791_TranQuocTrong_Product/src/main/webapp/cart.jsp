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

<h1>Your Cart</h1>
<button><a href="ProductController">Go back</a> </button> <br/><br/>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Into Money</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="cartItem" items="${cart}">
            <tr>
                <td>${cartItem.product.id}</td>
                <td>${cartItem.product.name}</td>
                <td>${cartItem.product.price}</td>
                <td>${cartItem.quantity}</td>
                <td>${cartItem.intoMoney}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<h3>Total price: ${totalPrice}</h3>  

</body>
</html>