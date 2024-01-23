package healthlogger.servlet;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchPatientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchName = request.getParameter("searchName");

		List<Patients> searchResults = searchPatientsByName(searchName);

		HttpSession session = request.getSession();
		session.setAttribute("patientsSearchList", searchResults);

		response.sendRedirect("SearchPatients.jsp");
	}

	private List<Patients> searchPatientsByName(String searchName) {
		try (Session s = PatientsConnection.getSessionFactory().openSession()) {
			String hql = "FROM Patients WHERE PatientName LIKE :searchName";
			Query<Patients> query = s.createQuery(hql, Patients.class);
			query.setParameter("searchName", "%" + searchName + "%");

			return query.getResultList();
		}
	}
}
