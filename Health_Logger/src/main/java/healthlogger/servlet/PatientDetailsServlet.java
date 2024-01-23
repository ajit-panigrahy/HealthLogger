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

public class PatientDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Patients> patientsList = null;
		SessionFactory sessionFactory = PatientsConnection.getSessionFactory();
		Session s = sessionFactory.openSession();

		Query<Patients> q = s.createQuery("FROM Patients", Patients.class);
		patientsList = q.list();
		for (Patients patients : patientsList) {
			System.out.println(patients);
		}
		HttpSession session = request.getSession();
		session.setAttribute("patientsList", patientsList);

		request.getRequestDispatcher("PatientList.jsp").forward(request,
				response);
	}
}
