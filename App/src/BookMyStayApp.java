import java.util.List;

/**
 * use case 7: Add-On Service Selection
 *
 *
 *
 * @author  Developer
 * @version 1.0
 */
import java.util.*;
class AddOnService{
    private String serviceName;
    private  double cost;


    public AddOnService(String serviceName, double cost){
        this.serviceName=serviceName;
        this.cost=cost;
    }

    public String getServiceName(){return serviceName;}
    public double getCost(){return cost;}
}

class AddOnServiceManager {

    // Key -> Reservation ID
    // Value -> List of services added
    private Map<String, List<AddOnService>> servicesByReservation;

    // Constructor
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    // Add a service to a reservation
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    // Calculate total cost of services
    public double calculateTotalServiceCost(String reservationId) {

        double total = 0;

        List<AddOnService> services =
                servicesByReservation.getOrDefault(reservationId, new ArrayList<>());

        for (AddOnService s : services) {
            total += s.getCost();
        }

        return total;
    }
}
public class BookMyStayApp {
    public static void main(String[] args){
        System.out.println("Add-on Service Selection");

        // Reservation ID (already confirmed booking)
        String reservationId = "Single-1";

        // Create manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Spa", 1000));

        // Calculate total cost
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        // Display result
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);




    }
}