<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="healthlogger.mainapp.PatientList"%>
<%@ page import="healthlogger.entity.Patients"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Patient - Health Logger</title>
<link rel="stylesheet" href="PatientList.css">
</head>
<body>
	<%
	HttpSession session1 = request.getSession(false);
	String doctorName = (String) session1.getAttribute("Doctor_Name");
	System.out.println("doctor name in PatientDetailsServlet list: " + doctorName);
	if (doctorName == null || doctorName.isEmpty()) {
		response.sendRedirect("DoctorLogin.jsp");
	}
	%>
	<div style="text-align: right; padding: 10px;">
		<form action="LogoutServlet" method="post">
			<button type="submit" id="logout-btn">
				<span style="margin-right: 5px;">Logout</span> <span
					style="font-weight: bold;">&rarr;</span>
			</button>
		</form>
	</div>

	<h2>
		Patients List [
		<c:out
			value="${empty sessionScope.patientsList ? '0' : sessionScope.patientsList.size()}" />
		] <span style="float: right; display: flex; gap: 10px;">
			<form action="PatientList.jsp" method="post">
				<button type="submit" id="search-btn">
					<span style="margin-right: 5px;">Home</span> <span
						style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
			<form action="AddPatient.jsp" method="post">
				<button type="submit" id="add-patient-btn">
					<span style="margin-right: 5px;">Add Patient</span> <span
						style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
		</span>
	</h2>
	<!-- Add search form -->
	<form action="SearchPatientServlet" method="post">
		<label for="searchName">Search Patient:</label> <input type="text"
			id="searchName" name="searchName" required>
		<button type="submit" id="searchPatientBtn">Search</button>
	</form>

	<br>
	<table>
		<thead>
			<tr>
				<th>Sr No</th>
				<th>Name</th>
				<th>Age</th>
				<th>Gender</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Diagnosis</th>
				<th>Remark</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="patient" items="${sessionScope.patientsSearchList}">
				<tr>
					<td>${patient.patientId}</td>
					<td>${patient.patientName}</td>
					<td>${patient.age}</td>
					<td>${patient.gender}</td>
					<td>${patient.email}</td>
					<td>${patient.phone}</td>
					<td>${patient.diagnosis}</td>
					<td>${patient.remark}</td>
					<td class="action-column">
						<div class="action-buttons">
							<button class="action-button manage-button"
								onclick="window.location.href='ManageVitalServlet?patientId=${patient.patientId}'">Manage
								Vital</button>
							<button class="action-button update-button"
								onclick="window.location.href='UpdatePatientServlet?patientId=${patient.patientId}'">Update</button>
							<button class="action-button delete-button"
								onclick="window.location.href='DeletePatientServlet?patientId=${patient.patientId}'">Delete</button>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
