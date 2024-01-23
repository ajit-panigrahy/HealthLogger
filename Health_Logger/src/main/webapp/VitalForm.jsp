<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Health Logger</title>
<link rel="stylesheet" href="VitalForm.css">
</head>
<body>

	<form id="form-1" action="AddVitalsServlet" method="post">
		<div id="container">
			<label for="selectPatient">Select Patient:</label> <select
				id="selectPatient" name="selectPatient">
				<c:forEach var="patientName" items="${sessionScope.patientNames}">
					<option value="${patientName}">${patientName}</option>
				</c:forEach>
			</select> <label for="bpLow">Blood Pressure Low:</label> <input type="text"
				id="bpLow" name="bpLow" required> <label for="bpHigh">Blood
				Pressure High:</label> <input type="text" id="bpHigh" name="bpHigh" required>

			<label for="spo2">Spo2:</label> <input type="text" id="spo2"
				name="spo2" required>

			<button id="button" type="submit">Add Vital</button>
			<button id="button-reset" type="reset">Reset</button>
		</div>
	</form>

</body>
</html>
