package entity;
// author: CHONG PEI LEE
public class Posting implements Comparable<Posting> {
    private String postingID;
    private Job job;
    private Company company;
    private String postingDate;
    private String expirationDate;
    private String status; // DRAFT, PUBLISHED, CLOSED
    private int applicationCount;
    private int viewCount;
    private String postingNotes;
    
    public Posting(String postingID, Job job, Company company, String postingDate, 
                  String expirationDate) {
        this.postingID = postingID;
        this.job = job;
        this.company = company;
        this.postingDate = postingDate;
        this.expirationDate = expirationDate;
        this.status = "DRAFT";
        this.applicationCount = 0;
        this.viewCount = 0;
        this.postingNotes = "";
        
        // Establish bidirectional relationship
        if (job != null) {
            job.setCompany(company);
        }
    }

    // Getters
    public String getPostingID() { return postingID; }
    public Job getJob() { return job; }
    public Company getCompany() { return company; }
    public String getPostingDate() { return postingDate; }
    public String getExpirationDate() { return expirationDate; }
    public String getStatus() { return status; }
    public int getApplicationCount() { return applicationCount; }
    public int getViewCount() { return viewCount; }
    public String getPostingNotes() { return postingNotes; }

    // Setters
    public void setPostingID(String postingID) { this.postingID = postingID; }
    public void setJob(Job job) { 
        this.job = job;
        if (job != null) {
            job.setCompany(this.company);
        }
    }
    public void setCompany(Company company) { 
        this.company = company;
        if (this.job != null) {
            this.job.setCompany(company);
        }
    }
    public void setPostingDate(String postingDate) { this.postingDate = postingDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
    public void setStatus(String status) { this.status = status; }

    // Business methods
    public void publish() {
        this.status = "PUBLISHED";
    }
    
    public void close() {
        this.status = "CLOSED";
    }
    
    public void recordView() {
        this.viewCount++;
    }
    
    public void addApplication() {
        this.applicationCount++;
    }
    
    public boolean isActive() {
        return "PUBLISHED".equals(this.status);
    }
    
    public void addNote(String note) {
        if (postingNotes == null || postingNotes.isEmpty()) {
            postingNotes = note;
        } else {
            postingNotes += "\n" + note;
        }
    }

    
    @Override
    public int compareTo(Posting other) {
        // Primary sorting by posting date (newest first)
        int dateComparison = other.postingDate.compareTo(this.postingDate);
        if (dateComparison != 0) {
            return dateComparison;
        }
        // Secondary sorting by job title
        return this.job.getTitle().compareTo(other.job.getTitle());
    }
    
    @Override
    public String toString() {
        return "Posting ID: " + postingID + "\n" +
               "Job Title: " + job.getTitle() + "\n" +
               "Company: " + company.getName() + "\n" +
               "Posted: " + postingDate + "\n" +
               "Expires: " + expirationDate + "\n" +
               "Status: " + status + "\n" +
               "Applications: " + applicationCount + "\n" +
               "Views: " + viewCount;
    }
}