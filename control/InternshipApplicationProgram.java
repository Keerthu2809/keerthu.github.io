package control;

import adt.ListInterface;
import adt.SortedList;
import boundary.InterviewUI;
import boundary.JobManagementUI;
import boundary.PostingManagementUI;
import boundary.StudentManagementUI;
import boundary.MatchingEngineUI;
import dao.JobInitializer;
import dao.StudentInitializer;
import dao.ApplicationInitializer;
import dao.CompanyInitializer;
import dao.InterviewInitializer;
import dao.PostingInitializer;
import entity.Job;
import entity.Student;
import entity.Application;
import entity.Company;
import entity.Interview;
import entity.InterviewSchedule;
import entity.Posting;
import entity.Recruitment;
import java.util.Scanner;

public class InternshipApplicationProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize Companies
        CompanyInitializer companyInitializer = new CompanyInitializer();
        ListInterface<Company> companyList = companyInitializer.initializeCompanies();

        // Initialize Jobs
        JobInitializer jobInitializer = new JobInitializer();
        ListInterface<Job> jobList = initializeAndSort(jobInitializer.initializeJobs());

        // Initialize Students
        StudentInitializer studentInitializer = new StudentInitializer();
        ListInterface<Student> studentList = initializeAndSort(studentInitializer.initializeStudents());

        // Initialize ApplicationList
        ApplicationInitializer applicationInitializer = new ApplicationInitializer();
        ListInterface<Application> applicationList = new SortedList<>(); // Keep the list persistent

        // Initialize Postings
        PostingInitializer postingInitializer = new PostingInitializer();
        ListInterface<Posting> postingList = postingInitializer.initializePostings(jobList, companyList);


        // 2. Initialize all control classes
        JobControl jobControl = new JobControl(jobList, companyList);
        PostingControl postingControl = new PostingControl(postingList);
        StudentManagementControl studentControl = new StudentManagementControl(studentList);
        ApplicationControl applicationControl = new ApplicationControl(applicationList);
        MatchingEngineControl matchingControl = new MatchingEngineControl();
        InterviewControl interviewControl = new InterviewControl(); // Initialize InterviewControl

        // Initialize postings in job control
        initializePostings(jobControl, postingList);

        // 3. Initialize all UI classes
        JobManagementUI jobUI = new JobManagementUI(jobControl, scanner);
        PostingManagementUI postingUI = new PostingManagementUI(jobControl, postingControl, scanner);
        StudentManagementUI studentUI = new StudentManagementUI(studentControl, applicationControl, jobControl,
                scanner);
        MatchingEngineUI matchingUI = new MatchingEngineUI(matchingControl, scanner);
        InterviewUI interviewUI = new InterviewUI(interviewControl, matchingControl); // Initialize InterviewUI

        // 4. Display welcome message
        displayWelcomeMessage();

        // 5. Main program loop
        runMainMenu(scanner, jobUI, postingUI, studentUI, matchingUI, interviewUI);

        // 6. Clean up before exiting
        scanner.close();
        System.out.println("Program terminated successfully.");
    }

    // Helper method to initialize and sort any list
    private static <T extends Comparable<T>> ListInterface<T> initializeAndSort(ListInterface<T> initialList) {
        ListInterface<T> sortedList = new SortedList<>();
        if (initialList != null) {
            for (T item : initialList) {
                sortedList.add(item);
            }
        }
        return sortedList;
    }

    // Helper method to initialize postings
    private static void initializePostings(JobControl jobControl, ListInterface<Posting> postings) {
        for (Posting posting : postings) {
            jobControl.createJobPosting(
                    posting.getPostingID(),
                    posting.getJob().getJobID(),
                    posting.getCompany().getCompanyID(),
                    posting.getPostingDate(),
                    posting.getExpirationDate()
            );

            if ("PUBLISHED".equals(posting.getStatus())) {
                jobControl.publishJobPosting(posting.getPostingID());
            }
        }
    }

    // Display welcome message
    private static void displayWelcomeMessage() {
        System.out.println("\n=========================================");
        System.out.println("  TAR UMT Internship Application Program");
        System.out.println("=========================================");
    }

    // Main menu loop
    private static void runMainMenu(Scanner scanner, JobManagementUI jobUI, PostingManagementUI postingUI, StudentManagementUI studentUI, MatchingEngineUI matchingUI, InterviewUI interviewUI) {
        int choice;
        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Job Management");
            System.out.println("2. Posting Management");
            System.out.println("3. Student Management");
            System.out.println("4. Matching Engine");
            System.out.println("5. Interview Management"); // Add Interview Management option
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice (1-5): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        jobUI.start();
                        break;
                    case 2:
                        postingUI.start();
                        break;
                    case 3:
                        studentUI.displayMenu();
                        break;
                    case 4:
                        matchingUI.displayMenu();
                        break;
                    case 5:
                        interviewUI.run(); // Call the start method for InterviewUI
                        break;
                    case 6:
                        System.out.println("\nExiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                choice = -1; // Force loop to continue
            }
        } while (choice != 6);
    }
}
