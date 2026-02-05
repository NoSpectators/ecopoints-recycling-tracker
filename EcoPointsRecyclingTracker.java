import java.io.*;
import java.util.*;
import java.time.LocalDate;

/**
 * Main app to run the Eco-Points Recycling Tracker
 */

public class EcoPointsRecyclingTracker {

    private static Scanner scanner = new Scanner (System.in);
    private static Map<String, Household> households = new HashMap<>(); // hashmap for households

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Eco-Points Recycling Tracker ===");
            System.out.println("1. Register Household");
            System.out.println("2. Log Recycling Event");
            System.out.println("3. Display Households");
            System.out.println("4. Display Household Recycling Events");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    registerHousehold();
                    break;
                case "2":
                    logRecyclingEvent();
                    break;
                case "3":
                    displayHouseholds();
                    break;
                case "4":
                    displayHouseholdEvents();
                    break;
                case "5":
                    generateReports();
                    break;
                case "6":
                    saveHouseholdsToFile();
                    running = false;
                    System.out.println("Data saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 - 6.");
            }
        }
    }

    private static void registerHousehold() {
        // promt user to enter a unique household ID
        System.out.print("Enter a household ID: ");
        String id = scanner.nextLine().trim();

        // check if a household with this id already exists in the map
        if (households.containsKey(id)) {
            System.out.println("Error: Household ID already exists.");
            return; // stop execution and return early
        }

        // prompt user to enter the household's name
        System.out.print("Enter household name: ");
        String name = scanner.nextLine().trim();

        // address
        System.out.println("Enter household address: ");
        String address = scanner.nextLine().trim();

        // create a new HouseHold object using the inputs
        Household household = new Household(id, name, address);

        // add the new household to the households map
        households.put(id, household);

        // confirm to the user the household registration
        System.out.println("Household registered successfully on " + household.getJoinDate());
    }

    private static void logRecyclingEvent() {
        // prompt user for household id
        System.out.println("Enter household ID: ");
        String id = scanner.nextLine().trim();

        // look up household in hashmap by id
        Household household = households.get(id);

        // if not found show error and exit
        if (household == null) {
            System.out.println("Error: Household ID not found.");
            return;
        }

        // ask user for material type they recycled
        System.out.println("Enter material type (plastic/glass/metal/paper): ");
        String material = scanner.nextLine();

        double weight = 0.0;
        // loop until a valid weight is entered
        while (true) {
            try {
                System.out.print("Enter weight of material recycled (in kg): ");
                weight = Double.parseDouble(scanner.nextLine().trim());

                if (weight <= 0) throw new IllegalArgumentException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight. Must be a positive number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid weight. Must be a positive number.");
            }

        }

        // create new RecyclingEvent using the material and weight
        RecyclingEvent event = new RecyclingEvent(material, weight);

        // add event to RecyclingEvents list
        household.addEvent(event);

        // show success message
        System.out.println("Recycling event logged! Points earned: " + event.getEcoPoints());

    }

}