/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.JobPosting;
import adt.LinkedList;
/**
 *
 * @author Keerthu_2809
 */
public class JobManagement {
    private LinkedList<JobPosting> jobPostings = new LinkedList<>();

    // Getter method to access the job postings list
    public LinkedList<JobPosting> getJobPostings() {
        return jobPostings;
    }

    // Create a new job posting
    public boolean createJobPosting(JobPosting jobPosting) {
        if (!containsJobPosting(jobPosting.getJobId())) {
            jobPostings.add(jobPosting);
            return true;
        }
        return false; // Job posting with the same ID already exists
    }

    // Update an existing job posting
    public boolean updateJobPosting(String jobId, JobPosting updatedJobPosting) {
        for (int i = 1; i <= jobPostings.getNumberOfEntries(); i++) {
            JobPosting currentJob = jobPostings.getEntry(i);
            if (currentJob.getJobId().equals(jobId)) {
                jobPostings.replace(i, updatedJobPosting); // Replace the job posting at the specified index
                return true;
            }
        }
        return false; // Job posting not found
    }

    // Remove a job posting
    public boolean removeJobPosting(String jobId) {
        for (int i = 1; i <= jobPostings.getNumberOfEntries(); i++) {
            JobPosting currentJob = jobPostings.getEntry(i);
            if (currentJob.getJobId().equals(jobId)) {
                jobPostings.remove(i); // Remove the job posting at the specified index
                return true;
            }
        }
        return false; // Job posting not found
    }

    // Filter job postings based on criteria (e.g., location, job type, salary range)
    public LinkedList<JobPosting> filterJobPostings(String location, String company, String jobType, double minSalary, double maxSalary) {
        LinkedList<JobPosting> filteredJobs = new LinkedList<>();
        for (int i = 1; i <= jobPostings.getNumberOfEntries(); i++) {
            JobPosting job = jobPostings.getEntry(i);
            if ((location == null || job.getLocation().equalsIgnoreCase(location)) &&
                (company == null || job.getCompany().equalsIgnoreCase(company)) &&
                (jobType == null || job.getJobType().equalsIgnoreCase(jobType)) &&
                (job.getSalary() >= minSalary && job.getSalary() <= maxSalary)) {
                filteredJobs.add(job);
            }
        }
        return filteredJobs;
    }


    // Display all job postings
    public void displayAll() {
        System.out.println("Job Postings:");
        for (int i = 1; i <= jobPostings.getNumberOfEntries(); i++) {
            System.out.println(jobPostings.getEntry(i));
        }
    }

    // Helper method to check if a job posting exists by ID
    private boolean containsJobPosting(String jobId) {
        for (int i = 1; i <= jobPostings.getNumberOfEntries(); i++) {
            if (jobPostings.getEntry(i).getJobId().equals(jobId)) {
                return true;
            }
        }
        return false;
    }
}