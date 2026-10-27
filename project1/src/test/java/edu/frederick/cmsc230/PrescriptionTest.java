package edu.frederick.cmsc230;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class PrescriptionTest {

    @Test
    // Test prescription creation and getter methods
    public void testPrescriptionCreationAndGetters() {
        // Create a prescription object
        Date today = new Date();
        Prescription pr = new Prescription("Amoxicillin", today, 500, "Dr. Smith");

        // Verify all attribute values
        assertEquals("Amoxicillin", pr.getMedicineName());
        assertEquals(today, pr.getDateOfIssue());
        assertEquals(500, pr.getDosage());
        assertEquals("Dr. Smith", pr.getPrescriber());
    }
}
