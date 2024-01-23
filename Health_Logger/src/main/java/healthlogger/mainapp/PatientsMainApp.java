package healthlogger.mainapp;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import healthlogger.connection.PatientsConnection;
import healthlogger.entity.Patients;

public class PatientsMainApp {

	private static final Scanner sc = new Scanner(System.in);
	private static final SessionFactory sf = PatientsConnection
			.getSessionFactory();
	private static final Session session = sf.openSession();
	private static final Transaction t = session.beginTransaction();

	public static void main(String[] args) {
		try {
			String addAnother;
			do {
				Patients p = null;
				System.out.println("Enter Patient's Name : ");
				String name = sc.nextLine();
				System.out.println("Enter Patient's Age : ");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Patient's Gender : ");
				char gender = sc.next().toUpperCase().charAt(0);
				sc.nextLine();
				System.out.println("Enter Patient's Email : ");
				String email = sc.nextLine();
				System.out.println("Enter Patient's Phone Number : ");
				long phone = sc.nextLong();
				sc.nextLine();
				System.out.println("Enter Diagnosis of The Patient : ");
				String diagnosis = sc.nextLine();
				System.out.println("Enter Remarks For The Patient : ");
				String remark = sc.nextLine();

				addPatient(p, name, age, gender, email, phone, diagnosis,
						remark);

				System.out
						.println("Do you want to add another patient? (Y/N): ");
				addAnother = sc.nextLine().trim().toUpperCase();
			} while (addAnother.equals("Y") || addAnother.equals("YES"));

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if (t != null) {
				t.commit();
			}
		}
	}

	private static void addPatient(Patients p, String name, int age,
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

		System.out.println("Are You Sure To Add The Patient ?\nY\tN");
		String add = sc.nextLine().trim().toUpperCase();
		if (add.equals("Y") || add.equals("YES")) {
			session.persist(p);
		} else {
			System.out.println("Doctor Not Added");
			System.exit(0);
		}

	}


}
