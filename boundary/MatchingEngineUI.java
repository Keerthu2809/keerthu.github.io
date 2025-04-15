package boundary;
// Author Lim Yin Ze
import control.MatchingEngineControl;
import entity.Match;
import entity.Weighting;
import entity.Student;
import entity.Job;
import dao.StudentInitializer;
import dao.JobInitializer;
import adt.ListInterface;
import adt.SortedList;
import entity.InterviewSchedule;
import java.util.Scanner;

public class MatchingEngineUI {
    private final MatchingEngineControl control;
    private final Scanner scanner;
    private final StudentInitializer studentInitializer;
    private final JobInitializer jobInitializer;

    public MatchingEngineUI(MatchingEngineControl control, Scanner scanner) {
        this.control = control;
        this.scanner = scanner;
        this.studentInitializer = new StudentInitializer();
        this.jobInitializer = new JobInitializer();
    }

      public void displayMenu() {
        while (true) {
            System.out.println("\n=== Matching Engine ===");
            System.out.println("1. Calculate Matches");
            System.out.println("2. View Matches");
            System.out.println("3. View Weightings");
            System.out.println("4. Add Weighting Criteria");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Select option: ");

            int choice = getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1 -> calculateMatches();
                case 2 -> displayMatches();
                case 3 -> displayWeightings();
                case 4 -> addWeightingCriteria();
                case 5 -> displayReport();
                case 6 -> {
                    System.out.println("Exiting system...");
                    return;
                }

                default -> System.out.println("Invalid selection");
            }
        }
    }
      
    private void displayReport() {
        // Simple file saving using basic Java I/O
        System.out.print("\nSave to file? (Y/N): ");
        String choice = scanner.nextLine();

        String report = null;  // Initialize report variable to null by default

        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("\nGenerating Matching Report...\n");

            // Store the report in a variable to avoid generating it twice
            report = control.generateReport();

            // Print the report to the console
            System.out.println(report);

            System.out.print("\nEnter filename: ");
            String filename = scanner.nextLine();

            try {
                // Write the report to the file
                java.io.PrintWriter writer = new java.io.PrintWriter(filename);
                writer.print(report);  // Use the already generated report
                writer.close();
                System.out.println("Report saved to " + filename);
            } catch (java.io.FileNotFoundException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        } else {
            System.out.println("Report was not saved.");
        }
    }

  public void displayMatches() {    
    ListInterface<Match> matches = control.getAllMatches();
    
    if (matches.isEmpty()) {
        System.out.println("\nNo matches found. Calculate matches first.");
        return;
    }
    
    System.out.println("\nAll Matches:");
    System.out.println("==================================================================================================================");
    System.out.printf("%-10s %-20s %-25s %-20s %-8s %-15s %-12s %-6s\n", 
        "Match ID", "Student", "Job Position", "Matched Skills", "CGPA", "Location", "Experience", "Score");
    System.out.println("==================================================================================================================");
    
    for (Match match : matches) {
        Student student = match.getStudent();
        Job job = match.getJob();
        
        // Highlight good matches (score > 0.7)
        String scoreColor = match.getMatchScore() > 0.7 ? "\u001B[32m" : ""; // Green for good matches
        String resetColor = "\u001B[0m";
        
        System.out.printf("%-10s %-20s %-25s %-20s %-8.2f %-15s %-12s %s%-6.2f%s\n",
            match.getMatchID(),
            student.getStudentName(),
            job.getTitle(),
            match.getMatchedSkills(),
            student.getCgpa(),
            student.getLocation(),
            student.getStudyLevel(),
            scoreColor,
            match.getMatchScore(),
            resetColor);
    }
}

    private void calculateMatches() {
    System.out.println("\nCalculating matches...");
    
    ListInterface<Student> students = new SortedList<>();
    ListInterface<Job> jobs = new SortedList<>();
    
    // Load from initializers
    int studentCount = 0;
    int jobCount = 0;
    
    for (Student student : studentInitializer.initializeStudents()) {
        students.add(student);
        studentCount++;
    }
    
    for (Job job : jobInitializer.initializeJobs()) {
        jobs.add(job);
        jobCount++;
    }
    
    System.out.println("Students to match: " + studentCount);
    System.out.println("Jobs available: " + jobCount);
    
    control.calculateMatches(students, jobs);
    
    int matchCount = control.getAllMatches().getNumberOfEntries();
    System.out.println("Match calculation completed!");
    System.out.println("Found " + matchCount + " quality matches.");
}

    private void displayWeightings() {
        System.out.println("\nCurrent Weightings:");
        System.out.println("----------------------------------");
        System.out.printf("%-15s %-10s %-10s\n", "Criteria", "Weight", "Importance");
        System.out.println("----------------------------------");
        
        for (Weighting w : control.getWeightings()) {
            System.out.printf("%-15s %-10.2f %-10s\n",
                w.getCriteria(), w.getWeight(), w.getImportance());
        }
    }

    private void addWeightingCriteria() {
        System.out.println("\nAdd New Weighting Criteria");
        System.out.print("Enter criteria name: ");
        String criteria = scanner.nextLine();
        
        double weight = getValidatedDouble("Enter weight (0.0-1.0): ", 0.0, 1.0);
        
        String importance = getValidatedImportance();
        
        control.addWeighting(new Weighting(criteria, weight, importance));
        System.out.println("New weighting added successfully!");
    }

    private double getValidatedDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            double value = getDoubleInput();
            if (value >= min && value <= max) return value;
            System.out.printf("Value must be between %.1f and %.1f\n", min, max);
        }
    }

    private String getValidatedImportance() {
        while (true) {
            System.out.print("Enter importance (High/Medium/Low): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("high") || 
                input.equalsIgnoreCase("medium") || 
                input.equalsIgnoreCase("low")) {
                return input;
            }
            else
                System.out.println("Invalid importance level");
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a decimal number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
