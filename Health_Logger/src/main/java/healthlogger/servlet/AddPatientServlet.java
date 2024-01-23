package healthlogger.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;

public class AddPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SessionFactory sf = PatientsConnection
			.getSessionFactory();
	private static final Session s = sf.openSession();
	private static final Transaction t = s.beginTransaction();
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			long phone = Long.parseLong(request.getParameter("phone"));
			int age = Integer.parseInt(request.getParameter("age"));
			String diagnosis = request.getParameter("diagnosis");
			String remark = request.getParameter("remark");
			char gender = request.getParameter("gender").toUpperCase()
					.charAt(0);

			Patients p = null;

			AddPatientServlet.addNewPatient(p, name, age, gender, email, phone,
					diagnosis, remark);

			response.sendRedirect("PatientDetailsServlet");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("AddPatient.jsp");

		}
	}
	private static void addNewPatient(Patients p, String name, int age,
			char gender, String email, long phone, String diagnosis,
			String remark) {

		p = new Patients();
		p.setPatientName(name);
		p.setAge(age);
		p.setGender(gender);
		p.setEmail(email);
		p.setPhone(phone);
		p.setDiagnosis(diagnosis);
		p.setRemark(remark);

		s.persist(p);
		t.commit();
	}
}
