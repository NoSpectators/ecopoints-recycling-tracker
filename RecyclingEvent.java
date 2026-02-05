import java.io.Serializable; // lets the class be serializable (can save to file)
import java.time.LocalDate;

/**
 * Represents a single recycling event for a household
 */
public class RecyclingEvent {
    private String materialType;
    private double weight; // kilograms
    private LocalDate date;
    private double ecoPoints;

    public RecyclingEvent(String materialType, double weight) {
        this.materialType = materialType;
        this.weight = weight;
        this.date = LocalDate.now();
        this.ecoPoints = weight * 10; // 10 points per kg
    }

    // getters
    public String getMaterialType() {
        return this.materialType;
    }

    public double getWeight() {
        return this.weight;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public double getEcoPoints() {
        return this.ecoPoints;
    }

    @Override
    public String toString() {
        return "Date: " + this.date +
                "\nMaterial Type: " + this.materialType +
                "\nWeight: " + this.weight +
                "\nEcopoints: " + this.ecoPoints;
    }

}