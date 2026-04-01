/**
 * Book My Stay Application
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * @author Krishan
 * @version 10.0
 */

import java.util.*;

// Reservation Class
class Reservation {
    String reservationId;
    String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

// Inventory Class
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void incrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String room : inventory.keySet()) {
            System.out.println(room + " -> " + inventory.get(room));
        }
    }
}

// Booking History
class BookingHistory {
    private Map<String, Reservation> confirmedReservations = new HashMap<>();

    public void addReservation(Reservation reservation) {
        confirmedReservations.put(reservation.reservationId, reservation);
    }

    public Reservation getReservation(String reservationId) {
        return confirmedReservations.get(reservationId);
    }

    public void removeReservation(String reservationId) {
        confirmedReservations.remove(reservationId);
    }

    public void displayReservations() {
        System.out.println("\nConfirmed Reservations:");
        for (Reservation r : confirmedReservations.values()) {
            System.out.println("Reservation ID: " + r.reservationId + " | Room Type: " + r.roomType);
        }
    }
}

// Cancellation Service
class CancellationService {
    private Stack<String> rollbackStack = new Stack<>();

    public void cancelReservation(String reservationId, BookingHistory history, RoomInventory inventory) {

        Reservation reservation = history.getReservation(reservationId);

        if (reservation == null) {
            System.out.println("Cancellation Failed: Reservation does not exist.");
            return;
        }

        rollbackStack.push(reservation.reservationId);

        inventory.incrementRoom(reservation.roomType);

        history.removeReservation(reservationId);

        System.out.println("Reservation Cancelled Successfully: " + reservationId);
    }

    public void displayRollbackStack() {
        System.out.println("\nRollback Stack (Cancelled Reservations):");
        for (String id : rollbackStack) {
            System.out.println("Cancelled Reservation ID: " + id);
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 10.0          ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        CancellationService cancellationService = new CancellationService();

        // Confirm some reservations
        Reservation r1 = new Reservation("R101", "Single Room");
        Reservation r2 = new Reservation("R102", "Double Room");

        history.addReservation(r1);
        history.addReservation(r2);

        history.displayReservations();
        inventory.displayInventory();

        // Cancel reservation
        System.out.println("\nCancelling Reservation R101...");
        cancellationService.cancelReservation("R101", history, inventory);

        history.displayReservations();
        inventory.displayInventory();
        cancellationService.displayRollbackStack();

        System.out.println("\nCancellation and rollback completed successfully.");
    }
}