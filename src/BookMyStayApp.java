/**
 * Book My Stay Application
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * @author Krishan
 * @version 6.0
 */

import java.util.*;

// Reservation Class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Booking Request Queue
class BookingRequestQueue {
    private Queue<Reservation> requestQueue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasRequests() {
        return !requestQueue.isEmpty();
    }
}

// Room Inventory
class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

// Room Allocation Service
class RoomAllocationService {
    private Set<String> allocatedRoomIds = new HashSet<>();
    private HashMap<String, Set<String>> roomTypeToIds = new HashMap<>();
    private int roomCounter = 1;

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.roomType;

        if (inventory.getAvailability(roomType) > 0) {
            String roomId = generateRoomId(roomType);

            allocatedRoomIds.add(roomId);

            roomTypeToIds.putIfAbsent(roomType, new HashSet<>());
            roomTypeToIds.get(roomType).add(roomId);

            inventory.decrementRoom(roomType);

            System.out.println("Reservation Confirmed for " + reservation.guestName +
                    " | Room Type: " + roomType +
                    " | Room ID: " + roomId);
        } else {
            System.out.println("No rooms available for " + reservation.guestName +
                    " | Room Type: " + roomType);
        }
    }

    private String generateRoomId(String roomType) {
        String roomId = roomType.replace(" ", "").substring(0, 2).toUpperCase() + roomCounter;
        roomCounter++;
        return roomId;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 6.0           ");
        System.out.println("=================================");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocator = new RoomAllocationService();

        // Add booking requests
        queue.addRequest(new Reservation("Krishan", "Single Room"));
        queue.addRequest(new Reservation("Rahul", "Double Room"));
        queue.addRequest(new Reservation("Anita", "Suite Room"));
        queue.addRequest(new Reservation("John", "Suite Room")); // No availability

        // Process requests FIFO
        while (queue.hasRequests()) {
            Reservation request = queue.getNextRequest();
            allocator.allocateRoom(request, inventory);
        }

        System.out.println("\nRoom allocation completed.");
    }
}