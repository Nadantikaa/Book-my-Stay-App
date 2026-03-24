/**
 * use case 3: Centralized room inventory management
 *
 * This class acts as a single spurce of truth for room availability in the hotel.
 * @author  Developer
 * @version 1.0
 */
import java.util.Map;
import java.util.HashMap;
abstract class Room{
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;
    public Room(int numberOfBeds,int squareFeet,double pricePerNight){
        this.numberOfBeds=numberOfBeds;
        this.squareFeet=squareFeet;
        this.pricePerNight=pricePerNight;
    }
    public void displayRoomDetails(){
        System.out.println("Number of beds:"+numberOfBeds);
        System.out.println("Square Feet:"+squareFeet);
        System.out.println("Price per night:"+ pricePerNight);
    }
}
//class represents a single room in the hotel
class SingleRoom extends Room{

    public SingleRoom(){
        System.out.println("Single Room:");
        super(1,250,150.0);
        super.displayRoomDetails();
    }
}

//class represents a double room in the hotel.
class DoubleRoom extends Room{

    public DoubleRoom(){
        System.out.println("Double Room:");
        super(2,400,2500.0);
        super.displayRoomDetails();
    }
}

//class represents a suite room in the hotel
class SuiteRoom extends Room{

    public SuiteRoom(){
        System.out.println("Suite Room:");
        super(3,750,5000.0);
        super.displayRoomDetails();
    }
}

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    // Constructor
    public RoomInventory() {
        initializeInventory();
    }

    // Initialize default values
    private void initializeInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 10);
        roomAvailability.put("Double", 5);
        roomAvailability.put("Suite", 2);
    }

    // Get availability
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\nRoom Availability:");
        for (String type : roomAvailability.keySet()) {
            System.out.println(type + " Rooms: " + roomAvailability.get(type));
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args){
        System.out.println("Welcome to the Hotel Management System");
        System.out.println("System initialized successfully.");
        SingleRoom obj1=new SingleRoom();
        DoubleRoom obj2=new DoubleRoom();
        SuiteRoom obj3=new SuiteRoom();

        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
        // Update example
        inventory.updateAvailability("Single", 8);
        System.out.println("\nAfter update:");
        inventory.displayInventory();
    }
}