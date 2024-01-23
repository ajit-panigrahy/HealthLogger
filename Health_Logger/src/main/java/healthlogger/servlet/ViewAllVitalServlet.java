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

public class ViewAllVitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Patients> patientVitals = getPatientVital();
		HttpSession s = request.getSession();
		s.setAttribute("allPatientVital", patientVitals);
		response.sendRedirect("AllPatientsVitals.jsp");
	}

	public static List<Patients> getPatientVital() {
		SessionFactory sessionFactory = PatientsConnection.getSessionFactory();
		Session session = sessionFactory.openSession();

		Query<Patients> q = session.createQuery("FROM Patients",
				Patients.class);
		List<Patients> patientsList = q.list();
		System.out.println(patientsList);
		return patientsList;
	}
}
