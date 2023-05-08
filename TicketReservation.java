package udemy;

import java.util.*;

public class TicketReservation {


    private static final int CONFIRMEDLIST_LIMIT = 3;
    private static final int WAITINGLIST_LIMIT = 2;

    private static List<Passenger> confirmedList = new ArrayList<>();
    private static Deque<Passenger> waitingList = new ArrayDeque<>();

    // This getter is used only by the junit test case.
    public void getConfirmedList() {
        for(Passenger pass: confirmedList)
        {
            System.out.println("\t"+pass);
        }
    }
    public void getWaitingList() {
        for(Passenger pass: waitingList)
        {
            System.out.println("\t"+pass);
        }
    }

    /**
     * Returns true if passenger could be added into either confirmedList or
     * waitingList. Passenger will be added to waitingList only if confirmedList
     * is full.
     * <p>
     * Return false if both passengerList & waitingList are full
     */
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {

        Passenger p=new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
        if (confirmedList.size() < CONFIRMEDLIST_LIMIT)
        {
            confirmedList.add(p);
            return true;
        }
        else if(waitingList.size()<WAITINGLIST_LIMIT)
        {
            waitingList.add(p);
            return true;
        }
        else
        {
            System.out.println("No seats available");
            return false;
        }
}

    /**
     * Removes passenger with the given confirmationNumber. Passenger could be
     * in either confirmedList or waitingList. The implementation to remove the
     * passenger should go in removePassenger() method and removePassenger method
     * will be tested separately by the uploaded test scripts.
     * <p>
     * If passenger is in confirmedList, then after removing that passenger, the
     * passenger at the front of waitingList (if not empty) must be moved into
     * passengerList. Use poll() method (not remove()) to extract the passenger
     * from waitingList.
     */
    public boolean cancel(String confirmationNumber) {

        if (removePassenger(confirmedList.iterator(), confirmationNumber)) {

            System.out.println(confirmationNumber + " canceled the booking from Confirmed List\nReaminig seats in waiting list " + (WAITINGLIST_LIMIT - waitingList.size()) + "\nRemaining seats in Confimred list " + (CONFIRMEDLIST_LIMIT - confirmedList.size()) + "\n");
            System.out.println();
            return true;
        }
        removePassenger(waitingList.iterator(), confirmationNumber);
            System.out.println(confirmationNumber + " canceled the booking from Waiting List\nReaminig seats in waiting list " + (WAITINGLIST_LIMIT - waitingList.size()) + "\nRemaining seats in Confimred list " + (CONFIRMEDLIST_LIMIT - confirmedList.size()) + "\n");
            return true;
        }
    /*
     * Removes passenger with the given confirmation number.
     * Returns true only if passenger was present and removed. Otherwise, return false.
     */
    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {

        //to remove the passanger from the list
        while(iterator.hasNext())
        {
                if(iterator.next().getConfirmationNumber().contains(confirmationNumber)) {
                   iterator.remove();
                }
        }

        //to add the first passenger from waiting list to confirmed list
        if(confirmedList.size()<CONFIRMEDLIST_LIMIT && waitingList.size()!=0)
        {
                confirmedList.add(waitingList.pollFirst());
                return true;
        }
        return false;
    }
    public void printSize(String s1)
    {
        System.out.println(s1);
        System.out.println("Confiremed list size is "+confirmedList.size());
        System.out.println("Waiting list size is "+waitingList.size()+"\n--------------------------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {

        int cn=1;
        TicketReservation tr=new TicketReservation();

        Passenger p=new Passenger("jaydeep","Desai",21,"Male","Economy","C"+cn++);
        Passenger p1=new Passenger("Vaibhav","Navale",24,"Male","Business","C"+cn++);
        Passenger p2=new Passenger("Rushiraj","Bedage",23,"Male","Business","C"+cn++);
        Passenger p3=new Passenger("Ajit","Jangate",22,"Male","Economy","C"+cn++);
        Passenger p4=new Passenger("Sidharth","Sharma",24,"Male","Business","C"+cn++);
        Passenger p5=new Passenger("Akash","Thakur",24,"Male","Business","C"+cn++);

        tr.printSize("At First");

        //Adding passengers to confirmed list
        System.out.println("Booking Status : "+tr.bookFlight(p.getFirstName(),p.getLastName(),p.getAge(),p.getGender(),p.getTravelClass(),p.getConfirmationNumber()));
        System.out.println("Booking Status : "+tr.bookFlight(p1.getFirstName(),p1.getLastName(),p1.getAge(),p1.getGender(),p1.getTravelClass(),p1.getConfirmationNumber()));
        System.out.println("Booking Status : "+tr.bookFlight(p2.getFirstName(),p2.getLastName(),p2.getAge(),p2.getGender(),p2.getTravelClass(),p2.getConfirmationNumber()));

        //Adding passengers to waiting list
        System.out.println("Booking Status : "+tr.bookFlight(p3.getFirstName(),p3.getLastName(),p3.getAge(),p3.getGender(),p3.getTravelClass(),p3.getConfirmationNumber()));
        System.out.println("Booking Status : "+tr.bookFlight(p4.getFirstName(),p4.getLastName(),p4.getAge(),p4.getGender(),p4.getTravelClass(),p4.getConfirmationNumber()));

        // To check whether it adds passenger when the both confirmed and waiting list are full
        System.out.println("Booking Status : "+tr.bookFlight(p5.getFirstName(),p5.getLastName(),p5.getAge(),p5.getGender(),p5.getTravelClass(),p5.getConfirmationNumber())+"\n--------------------------------------------------------------------------------------------------------------------");

        //Confirmed list after adding passenger
        System.out.println("Confirmed List");
        tr.getConfirmedList();
        System.out.println();

        //Waiting list after adding passenger
        System.out.println("Waiting List");
        tr.getWaitingList();
        System.out.println();

        //Updated list size
        tr.printSize("After adding passenger");

        //Removing passenger
        System.out.println(tr.cancel("C3" ));

        //Updated Confirmed list
        System.out.println("Updated Confirmed List");
        tr.getConfirmedList();
        System.out.println();

        //Updated Waiting list
        System.out.println("Updated Waiting List");
        tr.getWaitingList();
        System.out.println();

        //Updated list size
        tr.printSize("After removing passenger");

        //Adding new passenger
        tr.bookFlight(p5.getFirstName(),p5.getLastName(),p5.getAge(),p5.getGender(),p5.getTravelClass(),p5.getConfirmationNumber());
//        tr.bookFlight(p3.getFirstName(),p3.getLastName(),p3.getAge(),p3.getGender(),p3.getTravelClass(),p3.getConfirmationNumber());

        System.out.println("Updated Confirmed List");
        tr.getConfirmedList();
        System.out.println("Updated Waiting List");
        tr.getWaitingList();
        System.out.println();
        tr.printSize("After adding new passenger");

    }
}

