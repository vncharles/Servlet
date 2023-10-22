<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<img alt=""
			src="https://claynewsnetwork.com/wp-content/uploads/03-10-17BAnner.png">
	</div>
	<div>
		<c:url var="add" value="DanhSachTinTucServlet">
			<c:param name="function" value="ADD"></c:param>
		</c:url>
		
		<a href="DanhSachTinTucServlet">Danh sách tin tức</a> <a
			href="${add}">Thêm tin tức mới</a>
	</div>
</body>
</html>