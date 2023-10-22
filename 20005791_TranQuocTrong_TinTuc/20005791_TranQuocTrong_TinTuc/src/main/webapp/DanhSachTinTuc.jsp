<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sach tin tuc</title>
<style>
	.tinTucList {
		display: flex;
		flex-direction: column;
	}

	
	.item {
		height : 50px;
		display: flex;
		align-items: center;
		margin-bottom: 20px;
		
		
	}
	.button {
		margin-left: 20px;
		padding: 0
	}
	
</style>
</head>
<body>
	<%@ include file="navigation.jsp"%>
	<h1>Danh sách tin tức</h1>
	<div class="tinTucList">
		<c:forEach items="${tinTucList}" var="tinTuc">
			<c:url var="view" value="DanhSachTinTucServlet">
				<c:param name="id" value="${tinTuc.maTT}"></c:param>
				<c:param name="function" value="VIEW"></c:param>
			</c:url>
			<c:url var="update" value="DanhSachTinTucServlet">
				<c:param name="id" value="${tinTuc.maTT}"></c:param>
				<c:param name="function" value="UPDATE"></c:param>
			</c:url>
			<c:url var="delete" value="DanhSachTinTucServlet">
				<c:param name="id" value="${tinTuc.maTT}"></c:param>
				<c:param name="function" value="DELETE"></c:param>
			</c:url>
			<div class="item">
				<a class="tinTuc" href="${view}">${tinTuc.tieuDe}</a>
				<button class="button">
					<a href="${update}">Chỉnh sửa</a>
				</button>
				<button class="button">
					<a href="${delete}">Xóa</a>
				</button>
			</div>
		</c:forEach>

	</div>
</body>
</html>