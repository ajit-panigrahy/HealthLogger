package healthlogger.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import healthlogger.entity.Patients;

public class PatientsConnection {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				configuration.configure("healthloggerpatients.cfg.xml")
						.addAnnotatedClass(Patients.class);
				sessionFactory = configuration.buildSessionFactory();
			} catch (Exception e) {
				System.out.println("exception at PatientsConnection");
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}
