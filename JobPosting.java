/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ROG G14
 */
public class JobPosting {
    private String jobId;
    private String jobTitle;
    private String company;
    private String location;
    private String jobType;
    private double salary;
    private String[] requiredSkills;

    public JobPosting(String jobId, String jobTitle, String company, String location, String jobType, double salary, String[] requiredSkills) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.jobType = jobType;
        this.salary = salary;
        this.requiredSkills = requiredSkills;
    }

    // Getters
    public String getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getJobType() {
        return jobType;
    }

    public double getSalary() {
        return salary;
    }

    public String[] getRequiredSkills() {
        return requiredSkills;
    }
}          

    