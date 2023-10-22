<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết khóa học</title>
    <style>
        /* Style for the table */
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Style for the links and buttons */
        a {
            text-decoration: none;
            color: #000;
        }

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* Style for headers */
        h1 {
            font-size: 24px;
            margin-bottom: 10px;
        }

        /* Style for the "Danh sách học viên rỗng!" message */
        .empty-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
	<%@ include file="navigation.jsp" %>
    <h1>Danh sách khóa học</h1>
    <button type="button"><a href="course/form-course.jsp">Add course</a></button>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên khóa học</th>
            <th>Trạng thái</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listCourse}" var="course">
            <tr>
            	<c:url var="view_students" value="CourseServlet">
					<c:param name="courseId" value="${course.ID}" />
				</c:url>
				<c:url var="update" value="CourseServlet">
					<c:param name="courseId" value="${course.ID}" />
					<c:param name="function" value="GET_UPDATE" />
				</c:url>
				<c:url var="delete" value="CourseServlet">
					<c:param name="courseId" value="${course.ID}" />
					<c:param name="function" value="DELETE" />
				</c:url>
            
                <td>${course.ID}</td>
                <td>${course.courseName}</td>
                <td>${course.statusCourse}</td>
                <td>
                	<button type="button"><a href="${view_students}">Xem học viên</a></button>
                	<button type="button"><a href="${update}">Sửa</a></button>
                	<button type="button"><a href="${delete}">Xóa</a></button>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:choose>
        <c:when test="${not empty courseStudents}">
            <h2>Danh sách học viên</h2>
            <c:url var="register" value="CourseServlet">
     			<c:param name="function" value="REGISTER" />
				<c:param name="courseId" value="${courseId}" />
			</c:url>
			<button type="button"><a href="${register }">Đăng kí</a></button>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Họ và tên đệm</th>
                    <th>Tên</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${courseStudents}" var="student">
                	<c:url var="unregister" value="CourseServlet">
		     			<c:param name="function" value="UNREGISTER" />
						<c:param name="courseId" value="${courseId}" />
						<c:param name="studentId" value="${student.ID}" />
					</c:url>
					
                    <tr>
                        <td>${student.ID}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.email}</td>
                        <td>
                        	<button type="button"> <a href="${unregister}">Hủy đăng kí</a> </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:when test="${empty courseId }">
        	<p>Hãy chọn khóa học xem danh sách học viên</p>
        </c:when>
        <c:otherwise>
        	<h2>Danh sách học viên</h2>
        	<c:url var="register" value="CourseServlet">
     			<c:param name="function" value="REGISTER" />
				<c:param name="courseId" value="${courseId}" />
			</c:url>
			<button type="button"><a href="${register }">Đăng kí</a></button>
            <p>Danh sách học viên rỗng!</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
