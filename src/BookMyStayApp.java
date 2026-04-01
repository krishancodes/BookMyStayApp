/**
 * Book My Stay Application
 * Use Case 4: Room Search & Availability Check
 *
 * @author Krishan
 * @version 4.0
 */

import java.util.HashMap;

// Room Class
class Room {
    String roomType;
    int beds;
    double price;

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

// Inventory Class
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // Not available
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Search Service
class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory, Room[] rooms) {
        System.out.println("\nAvailable Rooms:\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.roomType);

            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println("-----------------------------");
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 4.0           ");
        System.out.println("=================================");

        // Create room objects
        Room single = new Room("Single Room", 1, 100);
        Room doubleRoom = new Room("Double Room", 2, 180);
        Room suite = new Room("Suite Room", 3, 300);

        Room[] rooms = {single, doubleRoom, suite};

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Search service
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, rooms);

        System.out.println("\nApplication terminated.");
    }
}