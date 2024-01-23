package healthlogger.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Vitals_Info")
public class Vitals {
	@Id
	@GeneratedValue()
	private int Vital_Id;

	@Column(name = "Bp_High")
	private int bpHigh;

	@Column(name = "Bp_Low")
	private int bpLow;

	@Column(name = "SPO2")
	private int spo2;

	@Column(name = "Date_Added")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	@ManyToOne
	@JoinColumn(name = "PatientId")
	private Patients patient;

	public int getVitalId() {
		return Vital_Id;
	}

	public void setVital_Id(int vital_Id) {
		Vital_Id = vital_Id;
	}

	public int getBpHigh() {
		return bpHigh;
	}

	public void setBpHigh(int bpHigh) {
		this.bpHigh = bpHigh;
	}

	public int getBpLow() {
		return bpLow;
	}

	public void setBpLow(int bpLow) {
		this.bpLow = bpLow;
	}

	public int getSpo2() {
		return spo2;
	}

	public void setSpo2(int spo2) {
		this.spo2 = spo2;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Patients getPatient() {
		return patient;
	}

	public void setPatient(Patients patient) {
		this.patient = patient;
	}

	public Vitals(int vital_Id, int bpHigh, int bpLow, int spo2, Date dateAdded,
			Patients patient) {
		super();
		Vital_Id = vital_Id;
		this.bpHigh = bpHigh;
		this.bpLow = bpLow;
		this.spo2 = spo2;
		this.dateAdded = dateAdded;
		this.patient = patient;
	}

	public Vitals() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss a");

		return "Vitals{" + "Vital_Id=" + Vital_Id + ", bpHigh=" + bpHigh
				+ ", bpLow=" + bpLow + ", spo2=" + spo2 + ", dateAdded="
				+ (dateAdded != null ? dateFormat.format(dateAdded) : null)
				+ ", patient=" + patient + '}';
	}

}
