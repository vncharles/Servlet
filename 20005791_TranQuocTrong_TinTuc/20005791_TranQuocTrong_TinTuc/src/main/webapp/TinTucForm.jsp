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
	<div class="back-button">
		<button class="button">
			<a href="/20005791_TranQuocTrong_TinTuc/DanhSachTinTucServlet">Trang chủ</a>
		</button>
	</div>

	<c:choose>
		<c:when test="${not empty TINTUC and not empty update}">
			<form action="TinTucFormServlet">
				<input type="hidden" name="function" value="UPDATE"> 
				<input type="hidden" name="id" value="${TINTUC.maTT}"> 
				<label>Tiêu	đề :</label> 
				<input type="text" name="tieuDe" value="${TINTUC.tieuDe}">
				<br> <br> 
				<label>Nội dung:</label> <input type="text" name="noiDung" value="${TINTUC.noiDung }"> 
				<br> <br>
				<label>Liên kết</label> 
				<input type="text" name="lienKet" value="${TINTUC.lienKet }">

				<button type="submit">Xác nhận</button>
			</form>
		</c:when>

		<c:when test="${not empty TINTUC}">
			<form action="#">
				<h1>${TINTUC.tieuDe}</h1>
				<p>${TINTUC.noiDung}</p>
				<a href="${TINTUC.lienKet}">${TINTUC.lienKet}</a>
				<p>${TINTUC.danhMuc.tenDM}</p>
			</form>

		</c:when>

		<c:otherwise>
			<form action="TinTucFormServlet">
				<input type="hidden" name="function" value="ADD"> 
				
				<label>Tiêu	đề :</label> 
				<input type="text" name="tieuDe" value="">
				<br> <br> 
				<label>Nội dung:</label> <input type="text" name="noiDung" value=""> 
				<br> <br>
				<label>Liên kết</label> 
				<input type="text" name="lienKet" value="">

				<button type="submit">Thêm</button>
			</form>
		</c:otherwise>

	</c:choose>
</body>
</html>