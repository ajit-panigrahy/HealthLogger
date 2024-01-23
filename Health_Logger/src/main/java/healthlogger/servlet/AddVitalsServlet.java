package healthlogger.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;
import healthlogger.entity.Vitals;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AddVitalsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String patientName = request.getParameter("selectPatient");
		int bpLow = Integer.parseInt(request.getParameter("bpLow"));
		int bpHigh = Integer.parseInt(request.getParameter("bpHigh"));
		int spo2 = Integer.parseInt(request.getParameter("spo2"));

		Session session = PatientsConnection.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Query<Patients> q = session.createQuery(
					"FROM Patients WHERE PatientName = :name", Patients.class);
			q.setParameter("name", patientName);
			List<Patients> patients = q.list();

			if (!patients.isEmpty()) {
				Patients patient = patients.get(0);

				Vitals vital = new Vitals();
				vital.setBpHigh(bpHigh);
				vital.setBpLow(bpLow);
				vital.setSpo2(spo2);
				vital.setDateAdded(new Date());
				vital.setPatient(patient);

				patient.getVitals().add(vital);

				session.saveOrUpdate(patient);

				transaction.commit();
				System.out.println("vital added");
				response.sendRedirect("ViewAllVitalServlet");
			} else {
				response.sendRedirect("VitalForm.jsp");
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
