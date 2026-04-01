/**
 * Book My Stay Application
 * Use Case 9: Error Handling & Validation
 *
 * @author Krishan
 * @version 9.0
 */

import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory Class
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public void validateRoomType(String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected: " + roomType);
        }
    }

    public void validateAvailability(String roomType) throws InvalidBookingException {
        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }

    public void bookRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String room : inventory.keySet()) {
            System.out.println(room + " -> " + inventory.get(room));
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 9.0           ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        String[] bookingRequests = {"Single Room", "Suite Room", "Deluxe Room"};

        for (String roomType : bookingRequests) {
            try {
                System.out.println("\nProcessing booking for: " + roomType);

                inventory.validateRoomType(roomType);
                inventory.validateAvailability(roomType);

                inventory.bookRoom(roomType);
                System.out.println("Booking confirmed for: " + roomType);

            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }

        inventory.displayInventory();

        System.out.println("\nSystem handled errors safely and continues running.");
    }
}