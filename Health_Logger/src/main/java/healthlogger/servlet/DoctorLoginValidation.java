package healthlogger.servlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import healthlogger.connection.DoctorConnection;
import healthlogger.entity.Doctors;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DoctorLoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		SessionFactory sessionFactory = DoctorConnection.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			System.out.println("Hello");
			Doctors doctor = (Doctors) session.createQuery(
					"FROM Doctors WHERE email = :email AND password = :password")
					.setParameter("email", email)
					.setParameter("password", password).uniqueResult();

			transaction.commit();

			if (doctor != null) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("Doctor_Name", doctor.getDoctorName());
				response.sendRedirect("DoctorHome.jsp");
			} else {
				response.sendRedirect(
						"DoctorLogin.jsp?error=invalid_credentials");
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
