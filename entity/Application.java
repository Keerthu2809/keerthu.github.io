package entity;

public class Application implements Comparable<Application> {

    private String applicationID;
    private Student student;
    private Job job;
    private Company company;
    private String applicationDate;

    // Constructor
    public Application(String applicationID, Student student, Job job, Company company, String applicationDate) {
        this.applicationID = applicationID;
        this.student = student;
        this.job = job;
        this.company = company;
        this.applicationDate = applicationDate;

    }

    // Getters
    public String getApplicationID() {
        return applicationID;
    }

    public Student getStudent() {
        return student;
    }

    public Job getJob() {
        return job;
    }

    public Company getCompany() {
        return company;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    // Setters
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

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

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Override
    public String toString() {
        return "Application{"
                + "applicationID='" + applicationID + '\''
                + ", studentID='" + student.getStudentID() + '\''
                + ", studentName='" + student.getStudentName() + '\''
                + ", jobID='" + job.getJobID() + '\''
                + ", jobName='" + job.getTitle() + '\''
                + ", companyID='" + company.getCompanyID() + '\''
                + ", companyName='" + company.getName() + '\''
                + ", jobLocation='" + job.getLocation() + '\''
                + ", jobCategory='" + job.getCategory() + '\''
                + ", salaryRange='" + job.getSalaryRange() + '\''
                + ", applicationDate='" + applicationDate + '\''
                + ", status='" + job.getStatus() + '\''
                + '}';
    }

    // Implement compareTo method
    @Override
    public int compareTo(Application other) {
        return this.applicationID.compareTo(other.applicationID);
    }
}
