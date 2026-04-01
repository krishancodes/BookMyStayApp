/**
 * Book My Stay Application
 * Use Case 7: Add-On Service Selection
 *
 * @author Krishan
 * @version 7.0
 */

import java.util.*;

// Service Class
class AddOnService {
    String serviceName;
    double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
}

// Add-On Service Manager
class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);

        System.out.println("Service added: " + service.serviceName +
                " for Reservation ID: " + reservationId);
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null) {
            System.out.println("No services for Reservation ID: " + reservationId);
            return;
        }

        double totalCost = 0;
        System.out.println("\nServices for Reservation ID: " + reservationId);

        for (AddOnService s : services) {
            System.out.println("- " + s.serviceName + " : $" + s.cost);
            totalCost += s.cost;
        }

        System.out.println("Total Add-On Cost: $" + totalCost);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("      Hotel Booking System       ");
        System.out.println("           Version 7.0           ");
        System.out.println("=================================");

        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Example reservation IDs
        String reservation1 = "R101";
        String reservation2 = "R102";

        // Add services
        serviceManager.addService(reservation1, new AddOnService("Breakfast", 20));
        serviceManager.addService(reservation1, new AddOnService("Airport Pickup", 40));
        serviceManager.addService(reservation2, new AddOnService("Extra Bed", 30));

        // Display services
        serviceManager.displayServices(reservation1);
        serviceManager.displayServices(reservation2);

        System.out.println("\nAdd-On services handled without affecting booking or inventory.");
    }
}