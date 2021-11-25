<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
</head>
<body>
	<form action="/OglasiWEB/LogInServlet" method="post" >
		<label for="input-1">Username:</label>
		<input id="input-1" placeholder="username" name="username" type="text"/>
		<label for="pass">Password:</label>
		<input id="pass" type="password" placeholder="password" name = "password"/>
		<input type="submit" value="login"/>
	</form>
</body>
</html>