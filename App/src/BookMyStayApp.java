/**
 * use case 5: Booking Request (First-Come-First-Served)
 *
 * This class represents a booking request made by a guest.
 *
 * @author  Developer
 * @version 1.0
 */
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;

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

public class BookMyStayApp {
    public static void main(String[] args){

        SingleRoom obj1=new SingleRoom();
        DoubleRoom obj2=new DoubleRoom();
        SuiteRoom obj3=new SuiteRoom();
        System.out.println("Booking reuqest Queue");
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1= new Reservation("Abhi","Single");
        Reservation r2= new Reservation("Subha","Double");
        Reservation r3 = new Reservation("Vanmathi","Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequests()) {

            Reservation req = bookingQueue.getNextRequest();

            System.out.println("\nProcessing Booking:");
            System.out.println("Customer: " + req.getGuestName());
            System.out.println("Room Type: " + req.getRoomType());
        }





    }
}