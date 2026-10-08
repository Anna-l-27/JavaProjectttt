package edu.frederick.cmsc230;

public class Patient {
    private PatientIdentity identity = null;

    // Constructor (initialize patient with an identity)
    public Patient(PatientIdentity id) {
        identity = id;
    }

    // Accessor method (retrieve patient identity)
    public PatientIdentity getIdentity() {
        return identity;
    }
    
    // Returns string representation of patient
    public  String toString(){
        return "identity: " + identity.toString();
    }

   
    // Converts patient record into CSV format string (LastName,FirstName,DateOfBirth)
    public String toCSV() {
        // Ensure patient identity is present before extracting fields
        if (identity != null) {
            // Concatenate name and birthdate with comma separators
            return identity.getName().getLastName() + "," 
                 + identity.getName().getFirstName() + "," 
                 + identity.getDateOfBirth();
        }
        // Return empty string if identity data is missing
        return "";
    }
}
