<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Student Confirmation Title</title></head>

<body>

  The student is confirmed: ${param.firstName} ${param.lastName}  
  <br/> <br/>	
  The student's country: ${param.country}
 
<%--   The student's favorite programming language: ${param.favoriteLanguage} --%>
  <br/> <br/>

	Favorite Programming Languages: 	
	<!-- display list of "favoriteLanguage" -->	
	<ul>
		<%
			String[] langs = request.getParameterValues("favoriteLanguage");
			for (String tempLang : langs) {
				out.println("<li>" + tempLang + "</li>");} 
		%>
	</ul>

</body>
</html>