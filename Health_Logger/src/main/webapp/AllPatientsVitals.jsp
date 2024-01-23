<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="healthlogger.mainapp.PatientList"%>
<%@ page import="healthlogger.entity.Patients"%>
<%@ page import="healthlogger.entity.Vitals"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patients List</title>
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
		Vitals <span style="float: right; display: flex; gap: 10px;">
			<form action="DoctorHome.jsp" method="post">
				<button type="submit" id="manage-vital-btn">
					<span style="margin-right: 5px;">Doctor Home</span> <span
						style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
			<form action="VitalAlert.jsp" method="post">
				<button id="export-btn" onclick="exportTableToCSV('patients.csv')">
					Export <span style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
			<form action="PatientNameListServlet" method="post">
				<button type="submit" id="record-new-vitals-btn">
					<span style="margin-right: 5px;">Record New Vital</span> <span
						style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
			<form action="VitalAlert.jsp" method="post">
				<button type="submit" id="vital-alert-btn">
					<span style="margin-right: 5px;">Vital Alerts</span> <span
						style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
		</span>
	</h2>

	<br>
	<table>
		<thead>
			<tr>
				<th>Sr No</th>
				<th>Name</th>
				<th>Phone</th>
				<th>BP Low</th>
				<th>BP High</th>
				<th>SPO2</th>
				<th>Record On Date</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="patient" items="${sessionScope.allPatientVital}"
				varStatus="status">
				<c:forEach var="vital" items="${patient.vitals}">z
					<tr>
						<td>${status.index + 1}</td>
						<td>${patient.patientName}</td>
						<td>${patient.phone}</td>
						<td>${vital.bpLow}</td>
						<td>${vital.bpHigh}</td>
						<td>${vital.spo2}</td>
						<td><fmt:formatDate value="${vital.dateAdded}"
								pattern="yyyy-MM-dd hh:mm:ss a" /></td>
						<td class="action-column">
							<div class="action-buttons">
								<button class="action-button delete-button"
									onclick="window.location.href='DeleteVitalServlet?vitalId=${vital.vitalId}'">Delete</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
