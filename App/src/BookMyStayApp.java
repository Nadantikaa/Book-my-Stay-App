/**
 * use case 2: Basic Room types & static availability
 *
 * This abstract class represents generic hotel rooms.
 * it models attributes that are intrinsic to a room type and remain constant
 * regardless of availability.
 * @author  Developer
 * @version 1.0
 */
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

public class BookMyStayApp {
    public static void main(String[] args){
        System.out.println("Welcome to the Hotel Management System");
        System.out.println("System initialized successfully.");
        SingleRoom obj1=new SingleRoom();
        DoubleRoom obj2=new DoubleRoom();
        SuiteRoom obj3=new SuiteRoom();
    }
}