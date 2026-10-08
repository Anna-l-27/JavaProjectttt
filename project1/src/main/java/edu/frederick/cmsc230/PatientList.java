package edu.frederick.cmsc230;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    
    public class Iterator {
        // Current position of the iterator
        private int iterationIndex;

        // Constructs an iterator starting at index 0
        public Iterator() {
            iterationIndex = 0;
        }

        // Peeks at the current patient without moving the iterator forward
        public Patient peek() {
            if (iterationIndex < patients.length) {
                return patients[iterationIndex];
            } else {
                return null;
            }
        }

        // Returns the current patient and advances the iterator index 
        public Patient next() {
            // Return null if index is out of bounds or patient is null
            if (iterationIndex >= patients.length || patients[iterationIndex] == null) {
                return null;
            } else {
                Patient patient = patients[iterationIndex++];
                return patient;
            }
        }
    }


    // Saves all patient records from the list to a CSV file
    public boolean saveToFile(String filename) {
        // Try-with-resources automatically closes the FileWriter upon completion
        try (FileWriter writer = new FileWriter(filename)) {
            Iterator iter = new Iterator();
            // Loop through the list using iterator until no more patients remain
            while (iter.peek() != null) {
                Patient p = iter.next();
                writer.write(p.toCSV() + "\n");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
    }

    //importFromFile
    public boolean importFromFile(String filename) {
        File file = new File(filename);
        // Check if the target file exists before reading
        if (!file.exists()) {
            return false;
        }
        
        try (Scanner scanner = new Scanner(file)) {
            // Read lines sequentially until the end of the file is reached
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty lines to prevent parsing errors
                if (line.isEmpty()) continue;
                
                // Split the CSV line by commas into data elements
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String lastName = parts[0];
                    String firstName = parts[1];
                    String dobStr = parts[2];
                    
                    // Create Name object
                    Name name = new Name(firstName, lastName);
                    // Convert the birthdate string into a Date object and construct PatientIdentity 
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date dob = sdf.parse(dobStr);
                        PatientIdentity identity = new PatientIdentity(name, dob);
                        Patient p = new Patient(identity);
                        // Append the new patient to the end of the array without sorting
                        this.addUnsorted(p);
                    } catch (Exception e) {
                        // Skip line if date parsing fails
                    }
                }
            }
            // Perform merge sort after importing data
            this.mergesort();
            return true;
        } catch (IOException e) {
            return false;
        }     
    }
    // Adds a patient to the end of the array without sorting
    public boolean addUnsorted(Patient pat) {
        // Return false if the provided patient is null or the array has reached maximum capacity
        if (pat == null || count >= MAX_PATIENTS) {
            return false;
        }
        // Place patient at current count index and increment patient count
        patients[count] = pat;
        count++;
        return true;
    }
    // Main entry point for performing recursive merge sort on the patient list
    public void mergesort() {
        // lists with 0 or 1 element are already sorted
        if (this.count <= 1) {
            return;
        }

        // Split the list into two halves
        int mid = this.count / 2;
        PatientList group1 = new PatientList();
        PatientList group2 = new PatientList();

        // Populate group1 with elements from the first half (index 0 to mid-1)
        for (int i = 0; i < mid; i++) {
            if (patients[i] != null) {
                group1.add(patients[i]); 
            }
        }
        // Populate group2 with elements from the second half (index mid to count-1)
        for (int i = mid; i < this.count; i++) {
            if (patients[i] != null) {
                group2.add(patients[i]);
            }
        }

        // Recursively sort both sublists
        group1.mergesort();
        group2.mergesort();

        // Combine the two sorted sublists back into the current list
        merge(group1, group2);
    }

    // merging two sorted sublists into a single ordered array
    private void merge(PatientList group1, PatientList group2) {
        Iterator iter1 = group1.new Iterator();
        Iterator iter2 = group2.new Iterator();
        int index = 0;

        // Compare heads of both lists and insert the smaller element until one list is exhausted
        while (iter1.peek() != null && iter2.peek() != null) {
            if (iter1.peek().getIdentity().isLessThan(iter2.peek().getIdentity())) {
                this.patients[index++] = iter1.next();
            } else {
                this.patients[index++] = iter2.next();
            }
        }

        // Append remaining elements from group1, if any
        while (iter1.peek() != null) {
            this.patients[index++] = iter1.next();
        }

        // Append remaining elements from group2, if any
        while (iter2.peek() != null) {
            this.patients[index++] = iter2.next();
        }

        // Update patient count after merging
        this.count = index;
    }
}


    