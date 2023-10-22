<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .back-button {
            margin: 10px;
        }

        .back-button a {
            text-decoration: none;
            background-color: #007bff;
            color: #fff;
            padding: 5px 10px;
            border-radius: 5px;
        }

        h2 {
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
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
	<div class="back-button">
        <button class="back-button"><a href="/20005791_TranQuocTrong_StudentCourse/CourseServlet">Back</a></button>
    </div>
	<c:choose>
		<c:when test="${empty COURSE }">
			<h2>Create a New Course</h2>
		    <form action="/20005791_TranQuocTrong_StudentCourse/CourseServlet" method="post">
		        <input type="hidden" name="function" value="POST_ADD"/>
		    
		        <label for="courseName">Course Name:</label>
		        <input type="text" id="courseName" name="courseName" required><br><br>
		        
		        <label for="statusCourse">Status:</label>
		        <select id="statusCourse" name="statusCourse" required>
		            <option value="ON">ON</option>
		            <option value="OFF">OFF</option>
		        </select><br><br>
		
		        <input type="submit" value="Create Course">
		    </form>
		</c:when>
		<c:when test="${not empty COURSE }">
			<h2>Update Course</h2>
		    <form action="/20005791_TranQuocTrong_StudentCourse/CourseServlet" method="post">
		        <input type="hidden" name="function" value="POST_UPDATE"/>
		        <input type="hidden" name="ID" value="${COURSE.ID }"/>
		    
		        <label for="courseName">Course Name:</label>
		        <input type="text" id="courseName" name="courseName" value="${COURSE.courseName }" required><br><br>
		        
		        <label for="statusCourse">Status:</label>
		        <select id="statusCourse" name="statusCourse" required>
			        <c:choose>
			            <c:when test="${COURSE.statusCourse eq 'ON'}">
			                <option value="ON" selected>ON</option>
			                <option value="OFF">OFF</option>
			            </c:when>
			            <c:when test="${COURSE.statusCourse eq 'OFF'}">
			                <option value="ON">ON</option>
			                <option value="OFF" selected>OFF</option>
			            </c:when>
			            <c:otherwise>
			                <option value="ON">ON</option>
			                <option value="OFF">OFF</option>
			            </c:otherwise>
			        </c:choose>
			    </select><br><br>
		
		        <input type="submit" value="Update Course">
		    </form>
		</c:when>
	</c:choose>
 
    
    
</body>
</html>
