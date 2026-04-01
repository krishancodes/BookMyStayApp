/**
 * Book My Stay Application
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 *
 * @author Krishan
 * @version 11.0
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

// Thread-safe Inventory
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public synchronized boolean allocateRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }

    public synchronized void displayInventory() {
        System.out.println("\nFinal Inventory State:");
        for (String room : inventory.keySet()) {
            System.out.println(room + " -> " + inventory.get(room));
        }
    }
}

// Shared Booking Queue
class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addReservation(Reservation r) {
        queue.add(r);
    }

    public synchronized Reservation getReservation() {
        return queue.poll();
    }
}

// Booking Processor Thread
class BookingProcessor extends Thread {
    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            Reservation reservation;

            synchronized (queue) {
                reservation = queue.getReservation();
            }

            if (reservation == null) break;

            boolean allocated = inventory.allocateRoom(reservation.roomType);

            if (allocated) {
                System.out.println(Thread.currentThread().getName() +
                        " allocated " + reservation.roomType +
                        " to " + reservation.guestName);
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " failed to allocate " + reservation.roomType +
                        " to " + reservation.guestName);
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 11.0          ");
        System.out.println("=================================");

        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();

        // Simulate concurrent booking requests
        queue.addReservation(new Reservation("Krishan", "Single Room"));
        queue.addReservation(new Reservation("Rahul", "Single Room"));
        queue.addReservation(new Reservation("Anita", "Suite Room"));
        queue.addReservation(new Reservation("John", "Suite Room"));
        queue.addReservation(new Reservation("Meena", "Double Room"));

        // Create multiple threads
        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);
        BookingProcessor t3 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.displayInventory();

        System.out.println("\nConcurrent booking simulation completed safely.");
    }
}