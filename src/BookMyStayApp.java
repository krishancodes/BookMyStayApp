/**
 * Book My Stay Application
 * Use Case 2: Basic Room Types & Static Availability
 *
 * @author Krishan
 * @version 2.1
 */

// Abstract Room class
abstract class Room {
    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price per night: $" + price);
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 100);
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 180);
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 300);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 2.1           ");
        System.out.println("=================================");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("\nRoom Details & Availability:\n");

        single.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailable);
        System.out.println("-----------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailable);
        System.out.println("-----------------------------");

        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailable);
        System.out.println("-----------------------------");

        System.out.println("Application terminated.");
    }
}