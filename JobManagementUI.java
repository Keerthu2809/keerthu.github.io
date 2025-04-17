/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.JobManagement;
import entity.JobPosting; 
import adt.LinkedList;

import java.util.Scanner;
/**
 *
 * @author Keerthu_2809
 * User interface for job management.
 */
public class JobManagementUI {
    public static void manageJobs(Scanner scanner, JobManagement jobManagement) {
        int choice;
        do {
            System.out.println("\n--- Job Management ---");
            System.out.println("1. Display All Job Postings");
            System.out.println("2. Create Job Posting");
            System.out.println("3. Update Job Posting");
            System.out.println("4. Delete Job Posting");
            System.out.println("5. Filter Job Postings");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    jobManagement.displayAll();
                    break;
                case 2:
                    createJobPosting(scanner, jobManagement);
                    break;
                case 3:
                    updateJobPosting(scanner, jobManagement);
                    break;
                case 4:
                    removeJobPosting(scanner, jobManagement);
                    break;
                case 5:
                    filterJobPostings(scanner, jobManagement);
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Creates a new job posting
    private static void createJobPosting(Scanner scanner, JobManagement jobManagement) {
        System.out.println("Enter Job ID:");
        String jobId = scanner.nextLine();
        System.out.println("Enter Job Title:");
        String jobTitle = scanner.nextLine();
        System.out.println("Enter Company:");
        String company = scanner.nextLine();
        System.out.println("Enter Location:");
        String location = scanner.nextLine();
        System.out.println("Enter Job Type:");
        String jobType = scanner.nextLine();
        System.out.println("Enter Salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Required Skills (comma-separated):");
        String skillsInput = scanner.nextLine();
        String[] requiredSkills = skillsInput.split(",");
        for (int i = 0; i < requiredSkills.length; i++) {
            requiredSkills[i] = requiredSkills[i].trim();
        }

        JobPosting jobPosting = new JobPosting(jobId, jobTitle, company, location, jobType, salary, requiredSkills);
        if (jobManagement.createJobPosting(jobPosting)) {
            System.out.println("Job Posting Created Successfully.");
        } else {
            System.out.println("Job Posting with the same ID already exists.");
        }
    }

    // Updates an existing job posting
    private static void updateJobPosting(Scanner scanner, JobManagement jobManagement) {
        System.out.println("Enter Job ID to update:");
        String jobId = scanner.nextLine();
        System.out.println("Enter Updated Job Title:");
        String jobTitle = scanner.nextLine();
        System.out.println("Enter Updated Company:");
        String company = scanner.nextLine();
        System.out.println("Enter Updated Location:");
        String location = scanner.nextLine();
        System.out.println("Enter Updated Job Type:");
        String jobType = scanner.nextLine();
        System.out.println("Enter Updated Salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Updated Required Skills (comma-separated):");
        String skillsInput = scanner.nextLine();
        String[] requiredSkills = skillsInput.split(",");
        for (int i = 0; i < requiredSkills.length; i++) {
            requiredSkills[i] = requiredSkills[i].trim();
        }

        JobPosting updatedJobPosting = new JobPosting(jobId, jobTitle, company, location, jobType, salary, requiredSkills);
        if (jobManagement.updateJobPosting(jobId, updatedJobPosting)) {
            System.out.println("Job Posting Updated Successfully.");
        } else {
            System.out.println("Job Posting with the given ID does not exist.");
        }
    }

    // Deletes a job posting
    private static void removeJobPosting(Scanner scanner, JobManagement jobManagement) {
        System.out.println("Enter Job ID to delete:");
        String jobId = scanner.nextLine();
        if (jobManagement.removeJobPosting(jobId)) {
            System.out.println("Job Posting Deleted Successfully.");
        } else {
            System.out.println("Job Posting with the given ID does not exist.");
        }
    }

    // Filters job postings based on criteria
    private static void filterJobPostings(Scanner scanner, JobManagement jobManagement) {
        System.out.print("Enter Location (or leave blank): ");
        String location = scanner.nextLine();
        location = location.isEmpty() ? null : location;

        System.out.print("Enter Company (or leave blank): ");
        String company = scanner.nextLine();
        company = company.isEmpty() ? null : company;

        System.out.print("Enter Job Type (or leave blank): ");
        String jobType = scanner.nextLine();
        jobType = jobType.isEmpty() ? null : jobType;

        System.out.print("Enter Minimum Salary (or 0 for no minimum): ");
        double minSalary = scanner.nextDouble();

        System.out.print("Enter Maximum Salary (or 0 for no maximum): ");
        double maxSalary = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        LinkedList<JobPosting> filteredPostings = jobManagement.filterJobPostings(location, company, jobType, minSalary, maxSalary);
        System.out.println("\nFiltered Job Postings:");
        for (int i = 1; i <= filteredPostings.getNumberOfEntries(); i++) {
            System.out.println(filteredPostings.getEntry(i));
        }
    }
}