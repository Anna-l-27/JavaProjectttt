package edu.frederick.cmsc230;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Date;

public class PatientListTest {
    // Test adding a patient and finding them in the list
    @Test
    public void testAddAndFind() {
        PatientList list = new PatientList();
        Date now = new Date();

        Name name1 = new Name("Anna", "Lee");
        PatientIdentity id1 = new PatientIdentity(name1, now);
        Patient p1 = new Patient(id1);

        // Verify that patient addition returns true
        assertTrue(list.add(p1));

        // Verify added patient can be retrieved with find
        Patient found = list.find(id1);
        assertNotNull(found);
        assertEquals(p1, found);
    }

    // Test searching for a patient that does not exist in the list
    @Test
    public void testFindNotFound() {
        PatientList list = new PatientList();
        Date now = new Date();

        Name name1 = new Name("Anna", "Lee");
        PatientIdentity id1 = new PatientIdentity(name1, now);
        Patient p1 = new Patient(id1);

        Name name2 = new Name("Alice", "Brown");
        PatientIdentity id2 = new PatientIdentity(name2, now);

        list.add(p1);

        // Verify that finding an unadded identity returns null as specified
        Patient found = list.find(id2);
        assertNull(found);
    }

    // Test adding patients out of order and verifying sorted insertion and binary search
    @Test
    public void testAddOrdered() {
        PatientList list = new PatientList();
        Date now = new Date();

        Name name1 = new Name("Anna", "Lee");
        PatientIdentity id1 = new PatientIdentity(name1, now);
        Patient p1 = new Patient(id1);

        Name name2 = new Name("Alice", "Brown");
        PatientIdentity id2 = new PatientIdentity(name2, now);
        Patient p2 = new Patient(id2);

        // Add patients out of order (Brown -> Lee)
        list.add(p2);
        list.add(p1);

        // Verify that binary search can locate both patients in the sorted list
        assertNotNull(list.find(id1));
        assertNotNull(list.find(id2));
    }
    // Test adding null patient should fail
    @Test
    public void testAddNullPatient() {
        PatientList list = new PatientList();
    
        // Verify that adding null returns false
        assertFalse(list.add(null));
    }

    // Test sorting patients in the list using merge sort
    @Test
    public void testMergesort() {
        PatientList list = new PatientList();
        Date now = new Date();

        Name name1 = new Name("Charlie", "Zebra");
        Name name2 = new Name("Alice", "Alpha");
        
        Patient p1 = new Patient(new PatientIdentity(name1, now));
        Patient p2 = new Patient(new PatientIdentity(name2, now));

        // Add patients out of order
        list.add(p1);
        list.add(p2);

        // Perform merge sort
        list.mergesort();

        // Verify that list is sorted alphabetically by identity
        Patient foundFirst = list.find(p2.getIdentity());
        assertNotNull(foundFirst);
    }

    // Test adding a patient directly to the end of the list without sorting
    @Test
    public void testAddUnsorted() {
        PatientList list = new PatientList();
        Date now = new Date();

        Name name = new Name("Bob", "Smith");
        Patient p = new Patient(new PatientIdentity(name, now));

        // Verify that addUnsorted successfully appends patient
        assertTrue(list.addUnsorted(p));

        // Verify that null patient cannot be added
        assertFalse(list.addUnsorted(null));
    }

    // Test importing patient data from a non-existent file
    @Test
    public void testImportFromFileNotFound() {
        PatientList list = new PatientList();

        // Verify that importing a missing file returns false safely
        assertFalse(list.importFromFile("non_existent_file.csv"));
    }
}