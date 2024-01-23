<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.io.IOException"%>

<%
session = request.getSession(false);
String username = (session != null) ? (String) session.getAttribute("Doctor_Name") : null;
System.out.print("username : " + username);
if (username == null) {
	try {
		response.sendRedirect("DoctorLogin.jsp?error=invalid_credentials");
	} catch (IOException e) {
		e.printStackTrace();
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Health Logger</title>
<link rel="stylesheet" href="Index.css">
</head>
<body>
	<form id="form-1">
		<div id="container">
			<div id="logo">
				<h1>Health Logger</h1>
				<h1>Developed by Ajit Panigrahy</h1>
			</div>
			<button id="managePatientButton" type="button"
				onclick="managePatient()">Manage Patient</button>
			<button id="manageVitalsButton" type="button"
				onclick="manageVitals()">Manage Vitals</button>
		</div>
	</form>
</body>
<script>
	function managePatient() {
		window.location.href = 'PatientDetailsServlet';
	}

	function manageVitals(patientId) {
		window.location.href = 'ViewAllVitalServlet';
	}
</script>
</html>

