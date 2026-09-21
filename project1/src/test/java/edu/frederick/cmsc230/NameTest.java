package edu.frederick.cmsc230;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NameTest{

    @Test
    public void testNameEquals() {
        Name name1 = new Name("Anna", "Lee");
        Name name2 = new Name("Anna", "Lee");
        Name name3 = new Name("anna", "lee"); 
        Name name4 = new Name("Anne", "Lee");

        // Test same name = true
        assertTrue(name1.match(name2));

        // Test Anna = anna（toLowerCase)
        assertTrue(name1.match(name3));

        // Test different name = false 
        assertFalse(name1.match(name4));
    }

    @Test
    public void testNameIsLessThan() {
        Name nameA = new Name("Alice", "Brown"); // Compare with last name
        Name nameB = new Name("Bob", "Smith");    // Compare with last name
        Name nameC = new Name("Charlie", "Smith");// Compare with first name

        // Compare with last name: 1 Brown 2 Smith = true
        assertTrue(nameA.isLessThan(nameB));
        assertFalse(nameB.isLessThan(nameA));

        // If same last name, compare with first name: 1 Alice 2 Charlie = true
        assertTrue(nameA.isLessThan(nameC));
    }


 

}
