/**
 * Book My Stay Application
 * Use Case 3: Centralized Room Inventory Management
 *
 * @author Krishan
 * @version 3.1
 */

import java.util.HashMap;

// Room Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor initializes inventory
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " -> Available Rooms: " + inventory.get(roomType));
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 3.1           ");
        System.out.println("=================================");

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Display Inventory
        inventory.displayInventory();

        // Update availability example
        System.out.println("\nUpdating Single Room availability to 4...\n");
        inventory.updateAvailability("Single Room", 4);

        // Display Updated Inventory
        inventory.displayInventory();

        System.out.println("\nApplication terminated.");
    }
}