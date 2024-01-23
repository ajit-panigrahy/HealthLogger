package healthlogger.servlet;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePatientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String patientIdParam = request.getParameter("patientId");

		if (patientIdParam != null && !patientIdParam.isEmpty()) {
			try {
				int patientId = Integer.parseInt(patientIdParam);

				deletePatient(patientId);

				response.sendRedirect("PatientDetailsServlet");
			} catch (NumberFormatException e) {
				response.sendRedirect("PatientList.jsp");
			}
		} else {
			response.sendRedirect("PatientList.jsp");
		}
	}

	private void deletePatient(int patientId) {
		try (Session s = PatientsConnection.getSessionFactory().openSession()) {
			Transaction transaction = s.beginTransaction();

			Patients patient = s.load(Patients.class, patientId);
			s.delete(patient);

			transaction.commit();
		}
	}
}
