package edu.frederick.cmsc230;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Date;

public class PrescriptionListTest {
    @Test
    // Test creation of an empty prescription list
    public void testCreateEmptyList() {
        // Create an empty prescription list
        PrescriptionList list = new PrescriptionList();
        // Initialize iterator
        list.init();
        // Empty list should return null
        assertNull(list.next());
    }
    @Test
    // Test adding a single prescription to the list
    public void testAddOnePrescription() {
        // Create a prescription list
        PrescriptionList list = new PrescriptionList();
        // Create a prescription and add it to the list
        Prescription p = new Prescription("Tylenol",new Date(),2,"Dr. Smith");

        list.add(p);
        // Initialize iteration
        list.init();
        // Verify that the prescription was added correctly
        assertEquals(p, list.next());
    }
    @Test
    // Test that adding a null prescription does not modify the list
    public void testAddNullPrescription() {
        // Create an empty prescription list
        PrescriptionList list = new PrescriptionList();
        // Attempt to add a null prescription
        list.add(null);
        // Initialize iteration
        list.init();
        // List should remain empty
        assertNull(list.next());
    }
    @Test
    // Test that prescriptions are stored in descending date order
    public void testSortedOrder() {
        // Create a prescription list
        PrescriptionList list = new PrescriptionList();

        Calendar cal = Calendar.getInstance();
        // Create an older prescription
        cal.set(2025, Calendar.JANUARY, 1);
        Prescription oldRx =new Prescription("A", cal.getTime(), 1, "Dr");
        // Create a newer prescription
        cal.set(2025, Calendar.JUNE, 1);
        Prescription newRx =new Prescription("B", cal.getTime(), 1, "Dr");
        // Add prescriptions out of order
        list.add(oldRx);
        list.add(newRx);
        // Initialize iterator
        list.init();
        // Verify prescriptions are stored in descending date order
        assertEquals(newRx, list.next());
        assertEquals(oldRx, list.next());
    }

    
}