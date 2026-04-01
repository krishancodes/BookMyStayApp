/**
 * Book My Stay Application
 * Use Case 8: Booking History & Reporting
 *
 * @author Krishan
 * @version 8.0
 */

import java.util.*;

// Reservation Class
class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

// Booking History
class BookingHistory {
    private List<Reservation> reservationHistory = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservationHistory.add(reservation);
        System.out.println("Reservation stored in history: " + reservation.reservationId);
    }

    public List<Reservation> getAllReservations() {
        return reservationHistory;
    }
}

// Booking Report Service
class BookingReportService {

    public void generateReport(List<Reservation> reservations) {
        System.out.println("\nBooking History Report:");
        System.out.println("----------------------------");

        for (Reservation r : reservations) {
            r.displayReservation();
        }

        System.out.println("\nTotal Reservations: " + reservations.size());
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 8.0           ");
        System.out.println("=================================");

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Confirmed reservations
        Reservation r1 = new Reservation("R101", "Krishan", "Single Room");
        Reservation r2 = new Reservation("R102", "Rahul", "Double Room");
        Reservation r3 = new Reservation("R103", "Anita", "Suite Room");

        // Store in history
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Generate report
        reportService.generateReport(history.getAllReservations());

        System.out.println("\nBooking history stored and reported successfully.");
    }
}