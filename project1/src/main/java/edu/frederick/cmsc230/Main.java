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

        //Create patient name
        PatientIdentity id1 = new PatientIdentity(name1, dob1);
        PatientIdentity id2 = new PatientIdentity(name2, dob2);

        // Create Patient object
        Patient patient = new Patient(id1);

        // Print results 
        System.out.println(patient.toString());
        System.out.println("Match test: " + id1.match(id2)); // Expected output: true/false
    }
}

