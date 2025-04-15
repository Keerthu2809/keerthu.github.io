package entity;
// Author Lim Yin Ze
public class Weighting implements Comparable<Weighting> {
    private String criteria;
    private double weight;
    private String importance; // "High", "Medium", "Low"

    public Weighting(String criteria, double weight, String importance) {
        this.criteria = criteria;
        this.weight = weight;
        this.importance = importance;
    }

    // Getters and Setters
    public String getCriteria() { return criteria; }
    public double getWeight() { return weight; }
    public String getImportance() { return importance; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setImportance(String importance) { this.importance = importance; }

    @Override
    public int compareTo(Weighting other) {
        // First by importance (High > Medium > Low)
        int importanceCompare = Integer.compare(
            this.getImportanceLevel(), 
            other.getImportanceLevel()
        );
        if (importanceCompare != 0) return -importanceCompare; // Descending
        
        // Then by weight (higher first)
        int weightCompare = Double.compare(this.weight, other.weight);
        if (weightCompare != 0) return -weightCompare; // Descending
        
        // Finally by criteria name (A-Z)
        return this.criteria.compareTo(other.criteria);
    }

    private int getImportanceLevel() {
        return switch (this.importance.toLowerCase()) {
            case "high" -> 3;
            case "medium" -> 2;
            case "low" -> 1;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return String.format(
            "Weighting[criteria=%s, weight=%.2f, importance=%s]",
            criteria, weight, importance
        );
    }
}
