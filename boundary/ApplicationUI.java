package boundary;

import adt.SortedList;
import control.ApplicationControl;
import control.StudentManagementControl;
import control.JobControl;
import entity.Application;
import entity.Student;
import entity.Job;
import entity.Company;
import java.util.Iterator;
import java.util.Scanner;

public class ApplicationUI {

    private final ApplicationControl applicationControl;
    private final StudentManagementControl studentControl;
    private final JobControl jobControl;
    private final Scanner scanner;

    public ApplicationUI(ApplicationControl applicationControl, StudentManagementControl studentControl, JobControl jobControl) {
        this.applicationControl = applicationControl;
        this.studentControl = studentControl;
        this.jobControl = jobControl;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n===== Manage Applications Menu =====");
            System.out.println("1. Add Application");
            System.out.println("2. View Applications");
            System.out.println("3. Update Application");
            System.out.println("4. Remove Application");
            System.out.println("5. Filter Applications");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addApplication();
                    break;
                case 2:
                    viewApplications();
                    break;
                case 3:
                    updateApplication();
                    break;
                case 4:
                    removeApplication();
                    break;
                case 5:
                    filterApplications();
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void addApplication() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();

        Student student = studentControl.getStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Job ID: ");
        String jobID = scanner.nextLine();

        // Get the job by its ID
        Job job = null;
        for (Job j : jobControl.getAllJobs()) {
            if (j.getJobID().equals(jobID)) {
                job = j;
                break;
            }
        }

        if (job == null) {
            System.out.println("Job not found.");
            return;
        }

        // Get the companyID directly from the job
        String companyID = job.getCompanyID();

        // Get the company using the companyID from the job
        Company company = jobControl.getCompanyById(companyID);
        if (company == null) {
            System.out.println("Company not found.");
            return;
        }

        // Check if the student has already applied for this job
        if (applicationControl.hasApplied(studentID, jobID)) {
            System.out.println("You have already applied for this job.");
            return;
        }

        System.out.print("Enter Application Date (YYYY-MM-DD): ");
        String applicationDate = scanner.nextLine();

        String applicationID = generateNewApplicationID();
        Application newApplication = new Application(applicationID, student, job, company, applicationDate);

        applicationControl.addApplication(newApplication);
        System.out.println("Application submitted successfully.");
    }

    // Method to generate the next Application ID
    private String generateNewApplicationID() {
        int maxID = 1000; // Default starting ID

        Iterator<Application> iterator = applicationControl.getAllApplications().iterator();
        while (iterator.hasNext()) {
            Application application = iterator.next();
            String appID = application.getApplicationID();

            if (appID.startsWith("A")) {
                try {
                    int num = Integer.parseInt(appID.substring(1)); // Extract number part
                    if (num > maxID) {
                        maxID = num; // Get the highest existing ID
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid IDs that don't match "A####"
                }
            }
        }

        return "A" + (maxID + 1); // Generate next Application ID
    }

    public void viewApplications() {
        System.out.println(centerText("=== ALL APPLICATIONS ==="));
        if (applicationControl.getAllApplications().isEmpty()) {
            System.out.println(centerText("No applications available."));
        } else {
            printApplicationTable();
        }
    }

    private void printApplicationTable() {
        String separator = "+----------------+------------+------------------------------+--------+--------------------------------+------------+----------------------+--------------+----------------------------+--------------------+----------------+------------+";

        System.out.println(separator);
        System.out.printf("| %-14s | %-10s | %-28s | %-6s | %-32s | %-10s | %-20s | %-12s | %-26s | %-18s | %-14s | %-14s |%n", // Increased width for Status
                "Application ID", "Student ID", "Student Name", "Job ID", "Job Name", "Company ID", "Company Name", "Location", "Category", "Salary Range", "Application Date", "Status");
        System.out.println(separator);

        for (Application application : applicationControl.getAllApplications()) {
            System.out.printf("| %-14s | %-10s | %-28s | %-6s | %-32s | %-10s | %-20s | %-12s | %-26s | %-18s | %-14s | %-14s |%n", // Increased width for Status
                    formatString(application.getApplicationID(), 14),
                    formatString(application.getStudent().getStudentID(), 10),
                    formatString(application.getStudent().getStudentName(), 28),
                    formatString(application.getJob().getJobID(), 6),
                    formatString(application.getJob().getTitle(), 32), // Ensuring full job name display
                    formatString(application.getCompany().getCompanyID(), 10),
                    formatString(application.getCompany().getName(), 20),
                    formatString(application.getJob().getLocation(), 12),
                    formatString(application.getJob().getCategory(), 26),
                    formatString(application.getJob().getSalaryRange().replaceAll("\\s+", " "), 18),
                    formatString(application.getApplicationDate(), 14),
                    formatString(application.getJob().getStatus(), 14)); // Adjusted the width of Status column
            System.out.println(separator);
        }
    }

    // Helper method to ensure proper formatting
    private String formatString(String str, int length) {
        if (str == null) {
            str = ""; // Avoid null values
        }
        str = str.trim(); // Remove leading/trailing spaces
        return (str.length() > length) ? str.substring(0, length) : String.format("%-" + length + "s", str);
    }

    private String truncate(String str, int length) {
        return (str != null && str.length() > length) ? str.substring(0, length - 3) + "..." : str;
    }

    private String centerText(String text) {
        int terminalWidth = 80;
        int padding = (terminalWidth - text.length()) / 2;
        return padding > 0 ? String.format("%" + padding + "s%s", "", text) : text;
    }

    private void updateApplication() {
        System.out.print("Enter Application ID to update: ");
        String applicationID = scanner.nextLine();
        if (applicationControl.getApplication(applicationID) == null) {
            System.out.println("Application ID not found.");
            return;
        }
        applicationControl.updateApplication(applicationID, scanner);
    }

    private void removeApplication() {
        System.out.print("Enter Application ID to remove: ");
        String applicationID = scanner.nextLine();
        if (applicationControl.getApplication(applicationID) == null) {
            System.out.println("Application ID not found.");
            return;
        }
        applicationControl.removeApplication(applicationID);
    }

    private void filterApplications() {
        System.out.println("\n===== Filter Applications =====");
        System.out.println("1. Filter Applications by Location");
        System.out.println("2. Filter Applications by Job Type");
        System.out.println("3. Filter Applications by Skills");
        System.out.print("Enter your choice: ");

        int filterChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter filter value: ");
        String filterValue = scanner.nextLine();

        System.out.println(centerText("=== FILTERED APPLICATIONS ==="));
        System.out.println("+----------------+------------+------------------+--------+--------------------+-----------+");
        System.out.printf("| %-14s | %-10s | %-16s | %-6s | %-18s | %-9s |%n",
                "Application ID", "Student ID", "Student Name", "Job ID", "Salary Range", "Status");
        System.out.println("+----------------+------------+------------------+--------+--------------------+-----------+");

        // Call ApplicationControl's filter method
        SortedList<Application> filteredApplications = applicationControl.filterApplications(String.valueOf(filterChoice), filterValue, studentControl);

        if (filteredApplications.isEmpty()) {
            System.out.println("No applications found matching the filter.");
        } else {
            for (Application application : filteredApplications) {
                System.out.printf("| %-14s | %-10s | %-16s | %-6s | %-18s | %-9s |%n",
                        application.getApplicationID(),
                        truncate(application.getStudent().getStudentID(), 10),
                        truncate(application.getStudent().getStudentName(), 16),
                        truncate(application.getJob().getJobID(), 6),
                        truncate(application.getJob().getSalaryRange(), 20),
                        application.getJob().getStatus());
                System.out.println("+----------------+------------+------------------+--------+--------------------+-----------+");
            }
        }
    }

}
