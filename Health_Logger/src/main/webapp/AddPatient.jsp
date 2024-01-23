<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Health Logger</title>
<link rel="stylesheet" href="AddPatient.css">
</head>
<body>

	<form id="form-1" action="AddPatientServlet" method="post">
		<div id="container">
			<div id="logo">
				<h1>Health Logger</h1>
				<h1>Developed by Ajit Panigrahy</h1>
			</div>

			<table>
				<tr>
					<td><label for="name">Name:</label></td>
					<td><input type="text" id="name" name="name" required></td>
				</tr>
				<tr>
					<td><label for="email">Email:</label></td>
					<td><input type="email" id="email" name="email" required></td>
				</tr>
				<tr>
					<td><label for="phone">Phone:</label></td>
					<td><input type="tel" id="phone" name="phone" required></td>
				</tr>
				<tr>
					<td><label for="age">Age:</label></td>
					<td><input type="number" id="age" name="age" required></td>
				</tr>
				<tr>
					<td><label for="diagnosis">Diagnosis:</label></td>
					<td><input type="text" id="diagnosis" name="diagnosis"
						required></td>
				</tr>
				<tr>
					<td><label for="remark">Remarks:</label></td>
					<td><textarea id="remark" name="remark" rows="4" required></textarea></td>
				</tr>
				<tr>
					<td><label>Gender:</label></td>
					<td class="radio-container"><input type="radio" id="male"
						name="gender" value="male" required> <label for="male">Male</label>
						<input type="radio" id="female" name="gender" value="female"
						required> <label for="female">Female</label></td>
				</tr>
			</table>

			<button id="button" type="submit">Add Patient</button>
			<button id="button-reset" type="reset">Reset</button>
		</div>
	</form>

</body>
</html>
