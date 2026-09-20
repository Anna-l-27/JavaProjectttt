package edu.frederick.cmsc230;
import java.util.Date;

public class PatientIdentity {
    private Name name = null;
    private Date dateOfBirth = null;

    // Constructor (initialize name and date of birth)
    public PatientIdentity(Name nm, Date dob) {
        name = nm;
        dateOfBirth = dob;
    }

    // Getter ( Name object)
    public Name getName(){ 
        return name; 
    }
    
    // Getter (Date of Birth)
    public Date getDateOfBirth(){ 
        return dateOfBirth; 
    }

    // Compares two patient identities for ordering (by name, then by date of birth)
    public boolean isLessThan(PatientIdentity other) {
        // Return false if the provided object is null
        if (other==null) {
            return false;
        }
        if (name.isLessThan(other.getName())) {
            return true;
        } else if (name.match(other.getName())) {
            //if name match, compare birth
            return dateOfBirth.before(other.getDateOfBirth());
        }
        return false;
    }
    
    // Checks if two patient identities are identical (same name and date of birth)
    public boolean match(PatientIdentity other) {
        // If null, dont match（prevent error）
        if (other == null) {
        return false;
        }
    // Are the both name and bith matched?
        boolean nameMatches = name.match(other.getName());
        boolean dobMatches = dateOfBirth.equals(other.getDateOfBirth());

        return nameMatches && dobMatches;
    }
    
    // Returns string representation of patient identity
    public String toString() {
    return "name: " + name.fullname() + " dob: " + dateOfBirth.toString();
    }

}
