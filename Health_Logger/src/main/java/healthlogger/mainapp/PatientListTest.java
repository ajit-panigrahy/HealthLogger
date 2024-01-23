package healthlogger.mainapp;


import healthlogger.entity.Patients;

import java.util.List;

public class PatientListTest {
    public static void main(String[] args) {
        PatientList patientList = new PatientList();

        try {
            List<Patients> patients = patientList.getAllPatients();

            // Print the fetched patients
            for (Patients patient : patients) {
                System.out.println(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
