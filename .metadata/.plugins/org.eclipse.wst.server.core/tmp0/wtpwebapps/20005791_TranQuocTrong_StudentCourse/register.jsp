<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Details</title>
    <style>
        /* Body styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        /* Container styles */
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        /* Back button styles */
        .back-button {
            margin-bottom: 20px;
        }

        /* Header styles */
        h1 {
            font-size: 24px;
            color: #333;
        }

        /* Label styles */
        label {
            font-weight: bold;
        }

        /* Course info styles */
        .course-info {
            margin-bottom: 20px;
        }

        /* Student list styles */
        h2 {
            font-size: 20px;
            margin-top: 30px;
            margin-bottom: 10px;
            color: #333;
        }

        /* Form styles */
        .registration-form {
            display: flex;
            align-items: center;
        }

        .registration-form label {
            margin-right: 10px;
        }

        /* Select menu styles */
        select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        /* Submit button styles */
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
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
            <button class="back-button"><a href="CourseServlet">Back</a></button>
        </div>
        
        <h1>Thông tin khóa học</h1>
        <div class="course-info">
            <label>ID: </label> <p>${course.ID }</p>
            <label>Name: </label> <p>${course.courseName }</p>
            <label>Status: </label> <p>${course.statusCourse }</p>
        </div>
        
        <h2>Danh sách học viên</h2>
        
        <form class="registration-form" action="CourseServlet" method="post">
            <input type="hidden" name="function" value="DONE_REGISTER" />
            <input type="hidden" name="courseId" value="${course.ID }" />
            <label for="menu">List</label>
            <select id="menu" name="studentId">
                <c:forEach items="${listStudent }" var="student">
                    <option value="${student.ID }">${student.firstName} ${student.lastName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Chọn">
        </form>
    </div>
</body>
</html>
