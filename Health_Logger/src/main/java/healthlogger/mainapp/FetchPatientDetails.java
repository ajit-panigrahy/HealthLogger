package healthlogger.mainapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;
import healthlogger.entity.Vitals;

import java.text.SimpleDateFormat;
import java.util.List;

public class FetchPatientDetails {

	public static void main(String[] args) {
		SessionFactory sessionFactory = PatientsConnection.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Query<Patients> q = session.createQuery("FROM Patients",
					Patients.class);
			List<Patients> patientsList = q.list();

			System.out.printf(
					"| %-15s | %-15s | %-8s | %-8s | %-6s | %-20s |%n",
					"Patient Name", "Phone", "BP Low", "BP High", "SPO2",
					"Record On");
			System.out.println(
					"|---------------|---------------|----------|----------|--------|----------------------|");

			for (Patients patient : patientsList) {
				String patientName = patient.getPatientName();
				long phone = patient.getPhone();

				// Check if patient has vitals
				if (patient.getVitals() != null
						&& !patient.getVitals().isEmpty()) {
					for (Vitals vital : patient.getVitals()) {
						int bpLow = vital.getBpLow();
						int bpHigh = vital.getBpHigh();
						int spo2 = vital.getSpo2();

						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss a");
						String recordOn = dateFormat
								.format(vital.getDateAdded());

						// Print patient details and vitals in table format
						System.out.printf(
								"| %-15s | %-15d | %-8d | %-8d | %-6d | %-20s |%n",
								patientName, phone, bpLow, bpHigh, spo2,
								recordOn);
					}
				} else {
					// If no vitals, print only patient details
					System.out.printf(
							"| %-15s | %-15d | %-8s | %-8s | %-6s | %-20s |%n",
							patientName, phone, "", "", "", "");
				}
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}
}
