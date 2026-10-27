package edu.frederick.cmsc230;

//linked list class to store and manage prescription records in sorted order by issue date.
public class PrescriptionList {
    // Private inner class representing a single node in the linked list
    private class ListRecord {
        public Prescription data;
        public ListRecord next;

        public ListRecord(Prescription pr){
            data=pr;
            next=null;
        }
        
    }
    // Head node pointing to the start of the prescription list
    private  ListRecord head;
    // Constructor initializing an empty prescription list
    public PrescriptionList(){
        head=null;
    }
    // compare two prescription dates.
    //Returns true if pr1 has a later issue date than pr2.
    private static boolean comesBefore(Prescription pr1, Prescription pr2){
        if (pr1 == null || pr1.getDateOfIssue() == null) {
            return false;
        }
        if (pr2 == null || pr2.getDateOfIssue() == null) {
            return true;
        }
        // Place later dates before older dates
        return pr1.getDateOfIssue().after(pr2.getDateOfIssue());
    }
    // Adds a new prescription into the linked list in descending chronological order.
    public void add(Prescription pr){
        if (pr == null) {
            return;
        }
        ListRecord newRecord = new ListRecord(pr);

        // Case 1: List is empty or new prescription should be placed before current head
        if (head == null || comesBefore(pr, head.data)) {
            newRecord.next = head;
            head = newRecord;
            return;
        }

        // Case 2: Traverse list to find correct insertion point
        ListRecord pBefore = head;
        while (pBefore.next != null && !comesBefore(pr, pBefore.next.data)) {
            pBefore = pBefore.next;
        }

        // Insert new node between pBefore and pBefore.next
        newRecord.next = pBefore.next;
        pBefore.next = newRecord;
    }
    // Pointer for internal iteration cursor
    private ListRecord current;

    //Initializes internal iterator cursor to point to head of list.
    public void init() {
        current = head;
    }

    // Returns current prescription data and advances cursor to next record.
    public Prescription next() {
        if (current == null) {
            return null;
        }
        Prescription data = current.data;
        current = current.next;
        return data;
    }
    

}
