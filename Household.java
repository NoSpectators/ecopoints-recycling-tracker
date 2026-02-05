import java.io.Serializable; // allows object to be serialized and deserialized (written to file and vice-versa)
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a household participating in the Eco-Points program
 */

public class Household implements Serializable {
    private String id;
    private String name;
    private String address;
    private LocalDate joinDate;
    private List<RecyclingEvent> events;
    private double totalPoints;

    public Household(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.joinDate = LocalDate.now();
        this.events = new ArrayList<>(); // initialized (empty, not null)
        this.totalPoints = 0.0;
    }

    // getters
    public String getId() { return this.id; }
    public String getName() { return this.name; }
    public String getAddress() { return this.address; }
    public LocalDate getJoinDate() { return this.joinDate; }
    public List<RecyclingEvent> getEvents() { return this.events; }
    public double getTotalPoints() { return this.totalPoints; }

    public void addEvent(RecyclingEvent event) {
        this.events.add(event);
        this.totalPoints += event.getEcoPoints();
    }

    public double getTotalWeight() {
        double total = 0.0;
        for (RecyclingEvent event : events) {
            total += event.getWeight();
        }
        return total;
    }

}