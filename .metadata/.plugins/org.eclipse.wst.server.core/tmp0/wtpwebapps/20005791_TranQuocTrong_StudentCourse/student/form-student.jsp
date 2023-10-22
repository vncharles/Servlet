<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
        }

        h2 {
            color: #333;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
    	<div class="back-button">
        	<button class="back-button"><a href="/20005791_TranQuocTrong_StudentCourse/StudentServlet">Back</a></button>
    	</div>
    	
    	<c:choose>
    		<c:when test="${empty STUDENT }">
    			<h2>Add Student</h2>
		        <form action="/20005791_TranQuocTrong_StudentCourse/StudentServlet" method="post">
		        	<input type="hidden" name="function" value="ADD" />
		        
		            <label for="firstName">First Name:</label>
		            <input type="text" id="firstName" name="firstName" required><br>
		            
		            <label for="lastName">Last Name:</label>
		            <input type="text" id="lastName" name="lastName" required><br>
		            
		            <label for="email">Email:</label>
		            <input type="text" id="email" name="email" required><br>
		
		            <input type="submit" value="Add Student">
		        </form>
    		</c:when>
    		<c:when test="${not empty STUDENT }">
    			<h2>Add Student</h2>
		        <form action="/20005791_TranQuocTrong_StudentCourse/StudentServlet" method="post">
		        	<input type="hidden" name="function" value="UPDATE" />
		        	<input type="hidden" name="id" value="${STUDENT.ID }" />
		        
		            <label for="firstName">First Name:</label>
		            <input type="text" id="firstName" name="firstName" value="${STUDENT.firstName }" required><br>
		            
		            <label for="lastName">Last Name:</label>
		            <input type="text" id="lastName" name="lastName" value="${STUDENT.lastName }"  required><br>
		            
		            <label for="email">Email:</label>
		            <input type="text" id="email" name="email" value="${STUDENT.email }"  required><br>
		
		            <input type="submit" value="Update Student">
		        </form>
    		</c:when>
    	</c:choose>
    	
        
    </div>
</body>
</html>
