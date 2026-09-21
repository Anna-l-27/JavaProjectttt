package edu.frederick.cmsc230;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import java.util.Date;

public class PatientTest {

    @Test
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
}