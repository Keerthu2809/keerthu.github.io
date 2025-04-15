package utility;
// author: CHONG PEI LEE
import entity.Company;  
import entity.Job;
import java.util.Scanner;

public class InputUtility {
    public static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public static int getIntInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return getIntInput(scanner);
    }

    public static String getStringInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static Job readJobDetails(Scanner scanner) {
        System.out.println("\nEnter Job Details:");
        String jobID = getStringInput("Job ID: ", scanner);
        String title = getStringInput("Title: ", scanner);
        String description = getStringInput("Description: ", scanner);
        String companyID = getStringInput("Company ID: ", scanner);
        String location = getStringInput("Location: ", scanner);
        String jobType = getStringInput("Job Type: ", scanner);
        String salaryRange = getStringInput("Salary Range (e.g., RM1000-RM2000): ", scanner);
        
        System.out.print("Required Skills (comma separated): ");
        String[] requiredSkills = scanner.nextLine().split("\\s*,\\s*");
        
        String experienceLevel = getStringInput("Experience Level: ", scanner);
        String postedDate = getStringInput("Posted Date (YYYY-MM-DD): ", scanner);
        String deadline = getStringInput("Application Deadline (YYYY-MM-DD): ", scanner);
        String status = getStringInput("Status: ", scanner);
        
        System.out.print("Benefits (comma separated): ");
        String[] benefits = scanner.nextLine().split("\\s*,\\s*");
        
        return new Job(jobID, title, description, companyID, location, 
                      jobType, salaryRange, requiredSkills, experienceLevel,
                      postedDate, deadline, status, benefits);
    }
}