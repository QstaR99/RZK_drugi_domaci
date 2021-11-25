<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${oglasi!=null}">
		<form method="post" action="/OglasiWEB/JavljanjeStranicaServlet">
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Text</th>
				<th>Link za javljanje</th>
			</tr>
			<c:forEach var="oglas" items="${oglasi}">
				<tr>
					<td>${oglas.idOglas}</td>
					<td>${oglas.text}</td>
					<td><a href="/OglasiWEB/javljanje.jsp?id=${oglas.idOglas}">javi</a></td>
				</tr>
			</c:forEach>
		</table>
			<input type="submit" value="Javi">
		</form>
	</c:if>
</body>
</html>