package edu.frederick.cmsc230;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        //Create Name objects
        Name name1 = new Name("Anna", "Lee");
        Name name2 = new Name("anna", "lee");


        //  SimpleDateFormat (Date Formatter)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Create Date objects from strings (time is automatically set to 00:00:00)
        Date dob1 = null;
        Date dob2 = null;

        // Handle ParseException if the date string format is invalid.
        try {
            dob1 = sdf.parse("2005-11-27");
            dob2 = sdf.parse("2005-11-27");
        } catch (Exception e) {
            System.out.println("Failed to parse date: " + e.getMessage());
        }

        //Create patient identity
        PatientIdentity id1 = new PatientIdentity(name1, dob1);
        PatientIdentity id2 = new PatientIdentity(name2, dob2);

        // Create Patient object
        Patient patient = new Patient(id1);

        // Print results 
        System.out.println(patient.toString());
        System.out.println("Match test: " + id1.match(id2)); // Expected output: true/false
    
        // Create and add to PatientList
        PatientList list = new PatientList();
        list.add(patient);

        // Search for patient in the list
        Patient found = list.find(id1);
        if (found != null) {
            System.out.println("Found in list: " + found.toString());
        } else {
            System.out.println("Patient not found in list.");
        }

        // create a list and put patient data into the list
        PatientList list1 = new PatientList();
        PatientList list2 = new PatientList();

        // add first patient
        list1.add(patient);

        // add second patient
        PatientIdentity id3 = new PatientIdentity(new Name("Bob", "Smith"), dob1);
        Patient patient2 = new Patient(id3);
        list2.add(patient2);

        // Create a new list for the merged patients
        PatientList merged = new PatientList();
        // Create iterators for both sorted lists
        PatientList.Iterator iter1 = list1.new Iterator();
        PatientList.Iterator iter2 = list2.new Iterator();

        // Compare patients from both lists and add the smaller one to the merged list
        while (iter1.peek() != null && iter2.peek() != null) {
            if (iter1.peek().getIdentity().isLessThan(iter2.peek().getIdentity())) {
                merged.add(iter1.next());
            } else {
                merged.add(iter2.next());
            }
        }

        // Append any remaining patients from list1
        while (iter1.peek() != null) {
            merged.add(iter1.next());
        }

        // Append any remaining patients from list2
        while (iter2.peek() != null) {
            merged.add(iter2.next());
        }

        // Save the sorted patient list to a CSV file and display status
        boolean saveSuccess = merged.saveToFile("patients.csv");
        System.out.println("Save to file successful: " + saveSuccess);

        // Initialize a new list to import and verify saved patient records
        PatientList importedList = new PatientList();
        boolean importSuccess = importedList.importFromFile("patients.csv");
        System.out.println("Import from file successful: " + importSuccess);
    
    }
}

