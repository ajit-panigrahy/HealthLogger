package healthlogger.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;

public class ManageVitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int searchId = Integer.parseInt(request.getParameter("patientId"));
		List<Patients> patientVitals = getPatientVital(searchId);
		HttpSession s = request.getSession();
		s.setAttribute("patientVitalById", patientVitals);
		response.sendRedirect("PatientVitals.jsp");
	}
	public static List<Patients> getPatientVital(int patientId) {
		SessionFactory sessionFactory = PatientsConnection.getSessionFactory();
		Session session = sessionFactory.openSession();

		Query<Patients> q = session.createQuery(
				"FROM Patients WHERE PatientId=:patientId", Patients.class);
		q.setParameter("patientId", patientId);
		List<Patients> patientsList = q.list();
		System.out.println(patientsList);
		return patientsList;
	}
}
