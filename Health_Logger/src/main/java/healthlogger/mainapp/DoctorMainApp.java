package healthlogger.mainapp;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import healthlogger.connection.DoctorConnection;
import healthlogger.entity.Doctors;

public class DoctorMainApp {
	private static final Scanner sc = new Scanner(System.in);
	private static final SessionFactory sf = DoctorConnection.getSessionFactory();
	private static final Session session = sf.openSession();
	private static final Transaction t = session.beginTransaction();

	public static void main(String[] args) {
		try {
			String addAnother;
			do {
				Doctors d1 = null;
				System.out.println("Enter Doctor's Name : ");
				String name = sc.nextLine();
				System.out.println("Enter Doctor's Email : ");
				String email = sc.nextLine();
				System.out.println("Enter Doctor's Password : ");
				String password = sc.nextLine();

//				System.out.println(name + "\n" + email + "\n" + password);

				addDoctor(d1, name, email, password);

				System.out.println("Do you want to add another doctor? (Y/N): ");
				addAnother = sc.nextLine().trim().toUpperCase();
			} while (addAnother.equals("Y") || addAnother.equals("YES"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (t != null) {
				t.commit();
			}
			if (session != null) {
				session.close();
			}
			if (sf != null) {
				sf.close();
			}
			if (sc != null) {
				sc.close();
			}
		}
	}

	private static void addDoctor(Doctors d, String name, String email, String password) {
		d = new Doctors();
		d.setDoctorName(name);
		d.setEmail(email);
		d.setPassword(password);
		System.out.println("Are You Sure To Add Doctor ?\nY\tN");
		String add = sc.nextLine().trim().toUpperCase();
		if (add.equals("Y") || add.equals("YES")) {
			session.persist(d);
		} else {
			System.out.println("Doctor Not Added");
			System.exit(0);
		}
	}
}
