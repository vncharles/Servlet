<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        h2 {
            color: #333;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        button a {
            text-decoration: none;
            color: #007bff;
            padding: 5px 10px;
            border: 1px solid #007bff;
            border-radius: 5px;
        }

        button a:hover {
            background-color: #007bff;
            color: #fff;
        }
    </style>
</head>
<body>
    <%@ include file="/navigation.jsp" %>
    <h2>Danh sách học viên</h2>
    <button type="button"><a href="student/form-student.jsp">Add course</a></button>
    <table>
        <tr>
            <th>ID</th>
            <th>Họ và tên đệm</th>
            <th>Tên</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${LIST_STUDENT}" var="student">
        	<c:url var="update" value="StudentServlet">
				<c:param name="studentId" value="${student.ID}" />
				<c:param name="function" value="FORM_UPDATE" />
			</c:url>
			<c:url var="delete" value="StudentServlet">
				<c:param name="studentId" value="${student.ID}" />
				<c:param name="function" value="DELETE" />
			</c:url>
        
            <tr>
                <td>${student.ID}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.email}</td>
                <td>
                    <button type="button"><a href="${update}">Update</a></button>
                    <button type="button"><a href="${delete}">Delete</a></button>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
