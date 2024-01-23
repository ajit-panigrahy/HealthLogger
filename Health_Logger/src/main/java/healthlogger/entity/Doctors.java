package healthlogger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Doctors_Info")
public class Doctors {
	private static final int MIN_PASSWORD_LENGTH = 8;
	private static final int MAX_PASSWORD_LENGTH = 20;
	@Id
	@GeneratedValue
	private int DoctorId;
	@Column(length = 40, nullable = false)
	private String DoctorName;
	@Column(length = 40, nullable = false, unique = true)
	private String Email;
	@Column(name = "Password", length = 16, nullable = false)
	private String Password;

	public int getDoctorId() {
		return DoctorId;
	}

	public void setDoctorId(int doctorId) {
		DoctorId = doctorId;
	}

	public String getDoctorName() {
		return DoctorName;
	}

	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
			throw new IllegalArgumentException(
					"Password must be between " + MIN_PASSWORD_LENGTH + " and " + MAX_PASSWORD_LENGTH + " characters.");
		}

		this.Password = password;
	}

	public Doctors(int doctorId, String doctorName, String email, String password) {
		super();
		DoctorId = doctorId;
		DoctorName = doctorName;
		Email = email;
		Password = password;
	}

	public Doctors() {
		super();
	}

	@Override
	public String toString() {
		return String.format("DoctorId=%s,\n  " + "DoctorName=%s,\n  " + "Email=%s,\n  " + "Password=%s\n", DoctorId,
				DoctorName, Email, Password);
	}

}
