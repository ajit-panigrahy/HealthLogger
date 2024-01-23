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

public class PatientNameListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			List<Patients> patientsList = null;
			SessionFactory sessionFactory = PatientsConnection
					.getSessionFactory();
			Session s = sessionFactory.openSession();

			Query<Patients> q = s.createQuery("FROM Patients", Patients.class);
			patientsList = q.list();

			List<String> patientNames = patientsList.stream()
					.map(Patients::getPatientName).toList();

			System.out.println(patientNames);

			HttpSession session = request.getSession();
			session.setAttribute("patientNames", patientNames);

			request.getRequestDispatcher("VitalForm.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions
		}
	}
}
