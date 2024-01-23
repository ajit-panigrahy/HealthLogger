package healthlogger.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import healthlogger.entity.Doctors;

public class DoctorConnection {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				configuration.configure("healthloggerdoctor.cfg.xml").addAnnotatedClass(Doctors.class);
				sessionFactory = configuration.buildSessionFactory();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}
	}
}
