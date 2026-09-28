package edu.frederick.cmsc230;

public class PatientList {

    // Constant for the maximum capacity of the patient list
    private static final int MAX_PATIENTS = 1000;
    // Internal array to store patient records
    private Patient[] patients; 
    // Counter to track the current number of patients in the list
    private int count;          

    // Constructor (Initializes the patient array with maximum capacity and resets count to zero.)
    public PatientList() {
        patients = new Patient[MAX_PATIENTS];
        count = 0;
    }

    // add a new patient.
    public boolean add(Patient pat) {
        //maintain sorted order.
        return addOrdered(pat);
    }

    // find a patient by identity
    public Patient find(PatientIdentity id) {
        //performing binary search.
        return binarySearch(id);
    }

    
    //insert a patient into the array in ascending order.
    //Shifts existing elements to the right to create space for the new entry.
    private boolean addOrdered(Patient pat) {
        // Return false if patient is null or array is full
        if (pat == null || count >= MAX_PATIENTS) {
            return false;
        }

        // Start checking from the last element currently in the array
        int currentIndex = count - 1;

        // Shift elements to the right as long as they are greater than the new patient
        while (currentIndex >= 0 && pat.getIdentity().isLessThan(patients[currentIndex].getIdentity())) {
            patients[currentIndex + 1] = patients[currentIndex]; //Shift elements to the right
            currentIndex--;
        }

        // Insert the new patient into the correct position
        patients[currentIndex + 1] = pat;
        count++; // Increment patient count (patient +1)
        return true;
    }

    //search for a patient identity using binary search.
    //Assumes the array is sorted in ascending order.
    private Patient binarySearch(PatientIdentity targetIdentity) {
        // Return null if target identity is null or array is empty
        if (targetIdentity == null || count == 0) {
            return null;
        }

        int lower = 0;          // first index
        int upper = count - 1;  // last index

        // Loop until search boundary closes
        while (upper >= lower) {
            int mid = lower + (upper - lower) / 2; 
            PatientIdentity midIdentity = patients[mid].getIdentity();

            // If match found: return the patient
            if (midIdentity.match(targetIdentity)) {
                return patients[mid];
            }

            // If target is smaller: search the left half
            if (targetIdentity.isLessThan(midIdentity)) {
                upper = mid - 1;
            } 
            // If target is larger: search the right half
            else {
                lower = mid + 1;
            }
        }
        // Patient not found
        return null; 
    }
}