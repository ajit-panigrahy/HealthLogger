package healthlogger.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import healthlogger.connection.VitalsConnection;
import healthlogger.entity.Vitals;

public class DeleteVitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String vitalIdParam = request.getParameter("vitalId");
		System.out.println("vital id : " + vitalIdParam);
		if (vitalIdParam != null && !vitalIdParam.isEmpty()) {
			try {
				int vitalId = Integer.parseInt(vitalIdParam);

				deleteVital(vitalId);

				response.sendRedirect("ViewAllVitalServlet");
			} catch (NumberFormatException e) {
				response.sendRedirect("DooctorHome.jsp");
			}
		} else {
			response.sendRedirect("AllPatientsVitals.jsp");
		}
	}

	private void deleteVital(int vitalId) {
		try (Session s = VitalsConnection.getSessionFactory().openSession()) {
			Transaction transaction = s.beginTransaction();

			Vitals vital = s.load(Vitals.class, vitalId);
			s.delete(vital);

			transaction.commit();
		}
	}

}
