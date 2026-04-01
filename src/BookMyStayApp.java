/**
 * Book My Stay Application
 * Use Case 12: Data Persistence & System Recovery
 *
 * @author Krishan
 * @version 12.0
 */

import java.io.*;
import java.util.*;

// Reservation Class
class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// System State Class
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<Reservation> bookingHistory;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    public void saveState(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    public SystemState loadState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            SystemState state = (SystemState) ois.readObject();
            System.out.println("System state loaded successfully.");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return null;
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 12.0          ");
        System.out.println("=================================");

        PersistenceService persistenceService = new PersistenceService();

        // Try loading previous state
        SystemState state = persistenceService.loadState();

        Map<String, Integer> inventory;
        List<Reservation> bookingHistory;

        if (state != null) {
            inventory = state.inventory;
            bookingHistory = state.bookingHistory;
        } else {
            inventory = new HashMap<>();
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);

            bookingHistory = new ArrayList<>();
        }

        // Simulate booking
        Reservation r1 = new Reservation("R201", "Krishan", "Single Room");
        bookingHistory.add(r1);
        inventory.put("Single Room", inventory.get("Single Room") - 1);

        System.out.println("\nCurrent Inventory:");
        for (String room : inventory.keySet()) {
            System.out.println(room + " -> " + inventory.get(room));
        }

        System.out.println("\nBooking History:");
        for (Reservation r : bookingHistory) {
            System.out.println(r.reservationId + " | " + r.guestName + " | " + r.roomType);
        }

        // Save state before shutdown
        SystemState newState = new SystemState(inventory, bookingHistory);
        persistenceService.saveState(newState);

        System.out.println("\nSystem shutdown with state saved.");
    }
}