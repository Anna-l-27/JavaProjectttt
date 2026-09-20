package edu.frederick.cmsc230;

public class Name {
    private String firstName;
    private String lastName;

    //Constructer initialize firstname and lastname
    public Name(String first, String last) {
        firstName = first;
        lastName = last;
    }
    
    //Getter (firstname)
    public String getFirstName() {
        return firstName;
    }
    //Getter (lastname)
    public String getLastName() {
        return lastName;
    }

    // Returns full name in "Last, First" format
    public String fullname() {
        return lastName + ", " + firstName;
    }

    // Checks if two names match, ignoring case ("Smith"="smith")
    public boolean match(Name other) {
       // If null, dont match（prevent error）
        if (other == null) {
            return false;
        }
        boolean firstMatch = firstName.equalsIgnoreCase(other.getFirstName());
        boolean lastMatch = lastName.equalsIgnoreCase(other.getLastName());

        return firstMatch && lastMatch;
    }
    // Compares two names alphabetically (last name first, then first name)
    public boolean isLessThan(Name other) {
        // Return false if the provided object is null
        if (other == null) {
            return false;
        }
        int lastCompare = getLastName().compareToIgnoreCase(other.getLastName());
        //If different lastname, compare the size of lastname
        if (lastCompare != 0) {
            return lastCompare < 0;
            
        }
        //when same last name, compare firstname
        return getFirstName().compareToIgnoreCase(other.getFirstName()) < 0;
         
    }
}
