<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Health Logger - Doctor Login</title>
<link rel="stylesheet" href="DoctorLogin.css">
</head>
<body>

	<div id="container">

		<div id="logo">
			<h1>Health Logger</h1>
			<h1>Developed By Ajit Panigrahy</h1>
			<h1>Doctors Login</h1>
		</div>
		<form action="DoctorLoginValidation" method="post">
			<label for="email">Enter Email:</label> <input type="email"
				id="email" name="email" required> <label for="password">Enter
				Password:</label> <input type="password" id="password" name="password"
				required>

			<button type="submit" id="button">Login</button>
			<button type="reset" id="button-reset">Reset</button>

			<%
			if (request.getAttribute("errorMessage") != null) {
			%>
			<div style="color: red; margin-top: 10px;">
				<%=request.getAttribute("errorMessage")%>
			</div>
			<%
			}
			%>
		</form>
	</div>

</body>
</html>
