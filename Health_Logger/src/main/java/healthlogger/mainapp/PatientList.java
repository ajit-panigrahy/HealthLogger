package healthlogger.mainapp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;

public class PatientList {

	public List<Patients> getAllPatients() {
		List<Patients> patientsList = null;
		try {
			SessionFactory sessionFactory = PatientsConnection
					.getSessionFactory();
			Session session = sessionFactory.openSession();

			Query<Patients> q = session.createQuery("FROM Patients",
					Patients.class);
			patientsList = q.list();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return patientsList;
	}
}
