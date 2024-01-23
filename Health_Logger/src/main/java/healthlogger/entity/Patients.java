package healthlogger.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Table(name = "Patients_Info")
public class Patients {

	@Id
	@GeneratedValue
	private int PatientId;
	@Column(length = 40)
	private String PatientName;
	@Column(name = "Age")
	private int Age;
	@Check(constraints = "UPPER(Gender) IN ('M', 'F')")
	private char Gender;
	@Column(unique = true, nullable = false, length = 40)
	private String Email;
	@Check(constraints = "LENGTH(CAST(Phone AS STRING)) = 10")
	@Column(nullable = false)
	private long Phone;
	@Column(nullable = false)
	private String Diagnosis;
	@Column(nullable = false)
	private String Remark;
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Vitals> vitals = new ArrayList<>();
	public int getPatientId() {
		return PatientId;
	}
	public void setPatientId(int patientId) {
		PatientId = patientId;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public char getGender() {
		return Gender;
	}
	public void setGender(char gender) {
		Gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public long getPhone() {
		return Phone;
	}
	public void setPhone(long phone) {
		Phone = phone;
	}
	public String getDiagnosis() {
		return Diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		Diagnosis = diagnosis;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public List<Vitals> getVitals() {
		return vitals;
	}
	public void setVitals(List<Vitals> vitals) {
		this.vitals = vitals;
	}
	public Patients(String patientName, int age, char gender, String email,
			long phone, String diagnosis, String remark, List<Vitals> vitals) {
		super();
		PatientName = patientName;
		Age = age;
		Gender = gender;
		Email = email;
		Phone = phone;
		Diagnosis = diagnosis;
		Remark = remark;
		this.vitals = vitals;
	}
	public Patients() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Patients{" + "PatientId=" + PatientId + ", PatientName='"
				+ PatientName + '\'' + ", Age=" + Age + ", Gender='" + Gender
				+ '\'' + ", Email='" + Email + '\'' + ", Phone='" + Phone + '\''
				+ ", Diagnosis='" + Diagnosis + '\'' + ", Remark='" + Remark
				+ '\'' + ", vitals=" + getVitalsWithoutPatientReference() + '}';
	}

	// Helper method to get vitals without patient reference
	private List<Vitals> getVitalsWithoutPatientReference() {
		if (vitals != null) {
			List<Vitals> vitalsWithoutPatient = new ArrayList<>();
			for (Vitals vital : vitals) {
				// Create a new Vitals object without the patient reference
				Vitals vitalWithoutPatient = new Vitals();
				vitalWithoutPatient.setVital_Id(vital.getVitalId());
				vitalWithoutPatient.setBpHigh(vital.getBpHigh());
				vitalWithoutPatient.setBpLow(vital.getBpLow());
				vitalWithoutPatient.setSpo2(vital.getSpo2());
				vitalWithoutPatient.setDateAdded(vital.getDateAdded());

				// Add to the list
				vitalsWithoutPatient.add(vitalWithoutPatient);
			}
			return vitalsWithoutPatient;
		}
		return null;
	}

}
