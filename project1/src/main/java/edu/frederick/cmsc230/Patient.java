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
}
