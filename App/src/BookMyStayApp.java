import java.util.List;

/**
 * use case 12:  Data Persistence & System Recovery
 *
 *
 *
 * @author  Developer
 * @version 1.0
 */
import java.util.*;
import java.io.*;

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String type : inventory.getRoomAvailability().keySet()) {
                int count = inventory.getRoomAvailability().get(type);
                writer.write(type + "=" + count);
                writer.newLine();
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                String type = parts[0];
                int count = Integer.parseInt(parts[1]);
                inventory.updateAvailability(type, count);
            }

            System.out.println("Inventory loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading inventory.");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService service = new FilePersistenceService();

        String filePath = "inventory.txt";

        service.loadInventory(inventory, filePath);

        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.getRoomAvailability().keySet()) {
            System.out.println(type + ": " + inventory.getRoomAvailability().get(type));
        }

        service.saveInventory(inventory, filePath);
    }
}