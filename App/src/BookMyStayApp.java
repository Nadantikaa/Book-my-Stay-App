/**
 * use case 6: Reservation Confirmation & Room Allocation
 *
 *
 *
 * @author  Developer
 * @version 1.0
 */
import java.util.*;

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

        super(1,250,150.0);

    }
}

//class represents a double room in the hotel.
class DoubleRoom extends Room{

    public DoubleRoom(){

        super(2,400,2500.0);

    }
}

//class represents a suite room in the hotel
class SuiteRoom extends Room{

    public SuiteRoom(){

        super(3,750,5000.0);

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
        roomAvailability.put("Double", 0);
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

class RoomSearchService{
    public void searchAvailableRooms(
            RoomInventory inventory,
            Room SingleRoom,
            Room DoubleRoom,
            Room SuiteRoom){
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("Single") > 0) {
            System.out.println("\nSingle Room Available:");
            SingleRoom.displayRoomDetails();
        }

        if (availability.get("Double") > 0) {
            System.out.println("\nDouble Room Available:");
            DoubleRoom.displayRoomDetails();
        }

        if (availability.get("Suite") > 0) {
            System.out.println("\nSuite Room Available:");
            SuiteRoom.displayRoomDetails();
        }

    }
}

class  Reservation{
    private String guestName;
    private  String roomType;

    public Reservation(String guestName,String roomType){
        this.guestName=guestName;
        this.roomType=roomType;
    }

    public String getGuestName(){ return guestName;}
    public String getRoomType(){ return roomType;}
}

class BookingRequestQueue{
    private Queue<Reservation> requestQueue;
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation){
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest(){ return  requestQueue.poll();}

    public boolean hasPendingRequests(){ return !requestQueue.isEmpty();}
}

class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        int available = inventory.getRoomAvailability().get(roomType);

        if (available <= 0) {
            System.out.println("No rooms available for " + roomType);
            return;
        }

        // generate unique ID
        String roomId = generateRoomId(roomType);

        // store allocated ID
        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        // update inventory
        inventory.updateAvailability(roomType, available - 1);

        // confirmation
        System.out.println("Booking confirmed for Guest: "
                + reservation.getGuestName()
                + ", Room ID: " + roomId);
    }

    private String generateRoomId(String roomType) {

        int count = assignedRoomsByType
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;

        return roomType + "-" + count;
    }
}

public class BookMyStayApp {
    public static void main(String[] args){

        System.out.println("Room Allocation Processing");

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Queue
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Allocation service
        RoomAllocationService service = new RoomAllocationService();

        // Process FIFO queue
        while (queue.hasPendingRequests()) {

            Reservation req = queue.getNextRequest();

            service.allocateRoom(req, inventory);

        }



    }
}