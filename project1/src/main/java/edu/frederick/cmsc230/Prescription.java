package edu.frederick.cmsc230;
import java.util.Date;
// prescription record containing medication, issue date, dosage, and prescriber details.
public class Prescription {
    // Private instance fields for prescription attributes
    private  String medicineName;
    private  Date dateOfIssue;
    private int dosage;
    private String prescriber;

    //Constructor to initialize a prescription object with given details.
    public  Prescription(String med, Date dat, int dos, String pre){
        medicineName=med;
        dateOfIssue=dat;
        dosage=dos;
        prescriber=pre;
    }
    // Getter method for medicine name
    public String getMedicineName(){
        return medicineName;
    }
    // Getter method for issue date
    public Date getDateOfIssue(){
        return dateOfIssue;
    }
    // Getter method for dosage amount (in mg)
    public int getDosage(){
        return dosage;
    }
    // Getter method for prescriber name
    public String getPrescriber(){
        return  prescriber;
    }
}
