package edu.frederick.cmsc230;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import java.util.Date;

public class PatientTest {
    
    @Test
    // Test patient creation and basic getter methods
    public void testPatientCreationAndGetters() {
        Name name = new Name("Anna", "Lee");
        Date dob = new Date();
        PatientIdentity identity = new PatientIdentity(name, dob);

        // Create a Patient 
        Patient patient = new Patient(identity);

        // Verify that the patient object and its identity are not null
        assertNotNull(patient);
        assertNotNull(patient.getIdentity());
    }

    
    @Test
    // Test matching logic between patient identities
    public void testPatientMatch() {
        Name name1 = new Name("Anna", "Lee");
        Name name2 = new Name("An", "Lee");
        Date dob = new Date();

        PatientIdentity id1 = new PatientIdentity(name1, dob);
        PatientIdentity id2 = new PatientIdentity(name1, dob);
        PatientIdentity id3 = new PatientIdentity(name2, dob);

        Patient patient1 = new Patient(id1);
        Patient patient2 = new Patient(id2);
        Patient patient3 = new Patient(id3);

        // Patients with same identities should match
        assertTrue(patient1.getIdentity().match(patient2.getIdentity()));

        // Patients with different identities should not match
        assertFalse(patient1.getIdentity().match(patient3.getIdentity()));
    }

    
    @Test
    // Test converting patient record into CSV format string
    public void testToCSV() {
        Name name = new Name("Anna", "Lee");
        Date dob = new Date();
        PatientIdentity identity = new PatientIdentity(name, dob);
        Patient patient = new Patient(identity);

       
        // Verify that CSV output contains formatted name and date
        String csvOutput = patient.toCSV(); // Expected format: "Lee,Anna," followed by date string
        assertNotNull(csvOutput);
        assertTrue(csvOutput.startsWith("Lee,Anna,"));

        // Test behavior when identity is null
        Patient nullPatient = new Patient(null);
        assertEquals("", nullPatient.toCSV());
    }

    @Test
    // Test retrieval of the associated prescription list
    public void testGetPrescriptions() {
        Name name = new Name("Anna", "Lee");
        PatientIdentity id =new PatientIdentity(name, new Date());

        Patient patient = new Patient(id);

        // Verify that the prescription list is properly initialized and not null
        assertNotNull(patient.getPrescriptions());
    }

    @Test
    // Test string representation of the patient record
    public void testToString() {
        Name name = new Name("Anna", "Lee");
        PatientIdentity id =new PatientIdentity(name, new Date());

        Patient patient = new Patient(id);

        // Verify that toString output contains the "identity" keyword
        assertTrue(patient.toString().contains("identity"));
    }
}