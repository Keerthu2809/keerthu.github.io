/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.ApplicantManagement;
import control.JobManagement;
import control.InterviewManagement;
import control.MatchingEngine;

import java.util.Scanner;
/**
 *
 * @author ROG G14
 */
public class Main {
    public static void main(String[] args) {
        // Initialize management modules
        ApplicantManagement applicantManagement = new ApplicantManagement();
        JobManagement jobManagement = new JobManagement();
        InterviewManagement interviewManagement = new InterviewManagement();

        // Initialize matching engine
        MatchingEngine matchingEngine = new MatchingEngine(
                applicantManagement.getApplicants(),  // Get the list of applicants
                jobManagement.getJobPostings()        // Get the list of job postings
        );

        // Menu-driven interface
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== Internship Application System ===");
            System.out.println("1. Applicant Management");
            System.out.println("2. Job Management");
            System.out.println("3. Interview Management");
            System.out.println("4. Run Matching Engine");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ApplicantManagementUI.manageApplicants(scanner, applicantManagement);
                    break;
                case 2:
                    JobManagementUI.manageJobs(scanner, jobManagement);
                    break;
                case 3:
                    InterviewManagementUI.manageInterviews(scanner, interviewManagement);
                    break;
                case 4:
                    MatchingEngineUI.runMatchingEngine(matchingEngine);
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}