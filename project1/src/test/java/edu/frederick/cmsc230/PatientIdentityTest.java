package edu.frederick.cmsc230;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import java.util.Date;

public class PatientIdentityTest {

    @Test
    public void testPatientIdentityMatch() {
        Name name1 = new Name("Anna", "Lee");
        Name name2 = new Name("Anne", "Lee");
        Date dob = new Date();

        PatientIdentity id1 = new PatientIdentity(name1, dob);
        PatientIdentity id2 = new PatientIdentity(name1, dob);
        PatientIdentity id3 = new PatientIdentity(name2, dob);

        // Should return true for identical name and DOB
        assertTrue(id1.match(id2));
        // Should return false for different names
        assertFalse(id1.match(id3));
    }

    @Test
    public void testPatientIdentityIsLessThan() {
        Name nameA = new Name("Alice", "Brown");
        Name nameB = new Name("Bob", "Smith");
        Date dob = new Date();

        PatientIdentity idA = new PatientIdentity(nameA, dob);
        PatientIdentity idB = new PatientIdentity(nameB, dob);

        // idA (Brown) should be less than idB (Smith)
        assertTrue(idA.isLessThan(idB));
        // idB (Smith) should not be less than idA (Brown)
        assertFalse(idB.isLessThan(idA));
    }
}