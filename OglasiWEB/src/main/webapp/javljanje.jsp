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
	<h1>Javljanje</h1><br>
	<form method="post" action="/OglasiWEB/JavljanjeServlet">
	
		<table>
			<tr>
				<td>id</td>
				<td><input type="text" readonly="readonly"
					value="${param.id}" name="id"></td>
			</tr>
			<tr>
				<td>Text</td>
				<td><textarea name="text"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="Javi">
	</form>
</body>
</html>