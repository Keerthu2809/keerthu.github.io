package entity;
// author: CHONG PEI LEE
import adt.ListInterface;
import adt.SortedList;

public class Company implements Comparable<Company> {
    private String companyID;
    private String name;
    private String industry;
    private String location;
    private String description;
    private final ListInterface<Posting> postings = new SortedList<>();

    public Company(String companyID, String name, String industry, String location) {
        this.companyID = companyID;
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = "";
    }

    // Getters and setters
    public String getCompanyID() { return companyID; }
    public String getName() { return name; }
    public String getIndustry() { return industry; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public ListInterface<Posting> getPostings() { 
        ListInterface<Posting> copy = new SortedList<>();
        for (Posting posting : postings) {
            copy.add(posting);
        }
        return copy;
    }

    public void setCompanyID(String companyID) { this.companyID = companyID; }
    public void setName(String name) { this.name = name; }
    public void setIndustry(String industry) { this.industry = industry; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }

    public void addPosting(Posting posting) {
        if (posting != null && !postings.contains(posting)) {
            postings.add(posting);
        }
    }

    public boolean removePosting(Posting posting) {
        return posting != null && postings.remove(posting);
    }

    @Override
    public String toString() {
        return "Company ID: " + companyID + "\n" +
               "Name: " + name + "\n" +
               "Industry: " + industry + "\n" +
               "Location: " + location + "\n" +
               "Active Postings: " + postings.getNumberOfEntries();
    }
    
    @Override
    public int compareTo(Company other) {
        return this.companyID.compareTo(other.companyID);
    }
}