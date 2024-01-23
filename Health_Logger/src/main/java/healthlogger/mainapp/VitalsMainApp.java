package healthlogger.mainapp;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;
import healthlogger.entity.Vitals;

public class VitalsMainApp {

	public static void main(String[] args) {

		String patientName = "John";
		int bpLow = 75;
		int bpHigh = 153;
		int spo2 = 95;

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

				// Add the new Vital record to the patient's list of vitals
				patient.getVitals().add(vital);

				session.saveOrUpdate(patient);

				transaction.commit();

				System.out.print("added");
			} else {
				System.out.println("Patient not found.");
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
