package entity;
// author: CHONG PEI LEE
public class Job implements Comparable<Job> {
    private String jobID;
    private String title;
    private String description;
    private String companyID;
    private Company company;
    private String location;
    private String category;
    private String salaryRange;
    private String[] requiredSkills;
    private String studyLevelRequired;
    private String postedDate;
    private String deadline;
    private String status;
    private String[] benefits;
    private Posting posting;
    private boolean isRemote;

    public Job(String jobID, String title, String description, String companyID, String location, String category, String salaryRange, String[] requiredSkills, String studyLevelRequired, String postedDate, String deadline, String status, String[] benefits) {
        this.jobID = jobID;
        this.title = title;
        this.description = description;
        this.companyID = companyID;
        this.location = location;
        this.category = category;
        this.salaryRange = salaryRange;
        this.requiredSkills = requiredSkills;
        this.studyLevelRequired = studyLevelRequired;
        this.postedDate = postedDate;
        this.deadline = deadline;
        this.status = status;
        this.benefits = benefits;
        this.isRemote = isRemote;
    }


    @Override
    public int compareTo(Job other) {
        return this.jobID.compareTo(other.jobID);
    }

    // Getters and setters
    public String getJobID() { return jobID; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCompanyID() { return companyID; }
    public Company getCompany() { return company; }
    public String getLocation() { return location; }
    public String getCategory() { return category; }
    public String getSalaryRange() { return salaryRange; }
    public String[] getRequiredSkills() { return requiredSkills; }
    public String getStudyLevelRequired() { return studyLevelRequired; }
    public String getPostedDate() { return postedDate; }
    public String getDeadline() { return deadline; }
    public String getStatus() { return status; }
    public String[] getBenefits() { return benefits; }
    public Posting getPosting() { return posting; }
    public boolean isRemote() { return isRemote; }

    public void setJobID(String jobID) { this.jobID = jobID; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setCompanyID(String companyID) { this.companyID = companyID; }
    public void setCompany(Company company) { this.company = company; }
    public void setLocation(String location) { this.location = location; }
    public void setCategory(String category) { this.category = category; }
    public void setSalaryRange(String salaryRange) { this.salaryRange = salaryRange; }
    public void setRequiredSkills(String[] requiredSkills) { this.requiredSkills = requiredSkills; }
    public void setStudyLevelRequired(String studyLevelRequired) { this.studyLevelRequired = studyLevelRequired; }
    public void setPostedDate(String postedDate) { this.postedDate = postedDate; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    public void setStatus(String status) { this.status = status; }
    public void setBenefits(String[] benefits) { this.benefits = benefits; }
    public void setPosting(Posting posting) { this.posting = posting; }
    public void setRemote(boolean remote) { isRemote = remote; }

    @Override
    public String toString() {
        return "Job ID: " + jobID + "\n" +
               "Title: " + title + "\n" +
               "Company: " + (company != null ? company.getName() : companyID) + "\n" +
               "Location: " + location + (isRemote ? " (Remote)" : "") + "\n" +
               "Category: " + category + "\n" +
               "Salary Range: " + salaryRange + "\n" +
               "Study Level Required: " + studyLevelRequired;
    }
}