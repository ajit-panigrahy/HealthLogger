<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="healthlogger.entity.Patients"%>
<%@ page import="healthlogger.entity.Vitals"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vitals Chart</title>
<link rel="stylesheet" href="PatientVitals.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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
			<form action="SearchPatients.jsp" method="post">
				<button type="submit" id="manage-vital-btn">
					<span style="margin-right: 5px;">Manage Vitals</span> <span
						style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
			<form action="AddPatient.jsp" method="post">
				<button type="submit" id="export-vital-btn">
					<span style="margin-right: 5px;">Export</span> <span
						style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
			<form action="VitalForm.jsp" method="post">
				<button type="submit" id="record-new-vitals-btn">
					<span style="margin-right: 5px;">Record New Vital</span> <span
						style="font-weight: bold;">&rarr;</span>
				</button>
			</form>
			<form action="ViewAllVitalsServlet" method="post">
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
			<c:forEach var="patient" items="${sessionScope.patientVitalById}"
				varStatus="status">
				<c:forEach var="vital" items="${patient.vitals}">
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
									onclick="window.location.href='DeletePatientServlet?vitalId=${vital.vitalId}'">Delete</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>

	<h2>Vitals Chart</h2>

	<!-- Create a Canvas for Chart -->
	<canvas id="myChart"></canvas>

	<!-- Prepare Data -->
	<script>
    var labels = [];
    var bpLowData = [];
    var bpHighData = [];
    var spo2Data = [];

    <c:forEach var="patient" items="${patientVitalById}" varStatus="status">
        <c:forEach var="vital" items="${patient.vitals}">
            labels.push('<fmt:formatDate value="${vital.dateAdded}" pattern="yyyy-MM-dd hh:mm:ss a" />');
            bpLowData.push(${vital.bpLow});
            bpHighData.push(${vital.bpHigh});
            spo2Data.push(${vital.spo2});
        </c:forEach>
    </c:forEach>
</script>

	<!-- Initialize Chart -->
	<script>
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'BP Low',
                data: bpLowData,
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1,
                fill: false
            }, {
                label: 'BP High',
                data: bpHighData,
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
                fill: false
            }, {
                label: 'SPO2',
                data: spo2Data,
                borderColor: 'rgba(255, 206, 86, 1)',
                borderWidth: 1,
                fill: false
            }]
        },
        options: {
            scales: {
                x: [{
                    type: 'time',
                    time: {
                        unit: 'day',
                        displayFormats: {
                            day: 'YYYY-MM-DD HH:mm:ss A'
                        }
                    }
                }],
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>

</body>
</html>
