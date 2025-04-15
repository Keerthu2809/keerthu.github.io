package boundary;
// author: CHONG PEI LEE
import adt.ListInterface;
import adt.SortedList;
import control.JobControl;
import control.PostingControl;
import entity.Company;
import entity.Job;
import entity.Posting;
import utility.InputUtility;
import java.util.Scanner;

public class PostingManagementUI {
    private final JobControl jobControl;
    private final PostingControl postingControl;
    private final Scanner scanner;
    private static final int CONSOLE_WIDTH = 120;
    private static final int MIN_PADDING = 10;

    public PostingManagementUI(JobControl jobControl, PostingControl postingControl, Scanner scanner) {
        this.jobControl = jobControl;
        this.postingControl = postingControl;
        this.scanner = scanner;
    }

    public void start() {
        displayWelcome();
        
        int choice;
        do {
            displayMainMenu();
            System.out.print(centerText("Enter your choice (0-11): "));
            choice = InputUtility.getIntInput(scanner);
            handleChoice(choice);
        } while (choice != 0);
    }

    private String centerText(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        int totalPadding = (CONSOLE_WIDTH - text.length()) / 2;
        totalPadding = Math.max(totalPadding, MIN_PADDING);
        return String.format("%" + (totalPadding + text.length()) + "s", text);
    }

    private void displayWelcome() {
        System.out.println("\n" + centerText("=== TARUMT INTERNSHIP APPLICATION PROGRAM ==="));
        System.out.println(centerText("=== Posting Management System ==="));
    }

    private void displayMainMenu() {
        System.out.println("\n" + centerText("=== POSTING MANAGEMENT ==="));
        System.out.println(centerText("1. Create New Posting"));
        System.out.println(centerText("2. View All Postings"));
        System.out.println(centerText("3. View Active Postings"));
        System.out.println(centerText("4. Publish Posting"));
        System.out.println(centerText("5. Close Posting"));
        System.out.println(centerText("6. Update Expiration Date"));
        System.out.println(centerText("7. Record Posting View"));
        System.out.println(centerText("8. Add Application to Posting"));
        System.out.println(centerText("9. Add Note to Posting"));
        System.out.println(centerText("10. View Posting Details"));
        System.out.println(centerText("11. Generate Posting Report"));
        System.out.println(centerText("0. Exit"));
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createPosting();
                break;
            case 2:
                viewAllPostings();
                break;
            case 3:
                viewActivePostings();
                break;
            case 4:
                publishPosting();
                break;
            case 5:
                closePosting();
                break;
            case 6:
                updatePostingExpiration();
                break;
            case 7:
                recordPostingView();
                break;
            case 8:
                addPostingApplication();
                break;
            case 9:
                addPostingNote();
                break;
            case 10:
                viewPostingDetails();
                break;
            case 11:
                generatePostingReport();
                break;
            case 0:
                System.out.println(centerText("Exiting Posting Management System..."));
                break;
            default:
                System.out.println(centerText("Invalid choice! Please try again."));
        }
    }

    private void createPosting() {
        System.out.println("\n" + centerText("=== CREATE NEW POSTING ==="));
        
        // Display available jobs
        ListInterface<Job> jobs = jobControl.getAllJobs();
        if (jobs.isEmpty()) {
            System.out.println(centerText("No jobs available to post. Please create jobs first."));
            return;
        }
        
        System.out.println(centerText("=== AVAILABLE JOBS ==="));
        printSimpleJobList(jobs);
        
        String jobID = InputUtility.getStringInput("Enter Job ID for posting: ", scanner);
        Job job = jobControl.getJobById(jobID);
        if (job == null) {
            System.out.println(centerText("Invalid Job ID!"));
            return;
        }
        
        // Display available companies
        ListInterface<Company> companies = jobControl.getAllCompanies();
        System.out.println("\n" + centerText("=== AVAILABLE COMPANIES ==="));
        printSimpleCompanyList(companies);
        
        String companyID = InputUtility.getStringInput("Enter Company ID for posting: ", scanner);
        Company company = jobControl.getCompanyById(companyID);
        if (company == null) {
            System.out.println(centerText("Invalid Company ID!"));
            return;
        }
        
        String postingID = InputUtility.getStringInput("Enter unique Posting ID: ", scanner);
        if (postingControl.getPostingById(postingID) != null) {
            System.out.println(centerText("Posting ID already exists!"));
            return;
        }
        
        String postingDate = InputUtility.getStringInput("Posting date (YYYY-MM-DD): ", scanner);
        String expirationDate = InputUtility.getStringInput("Expiration date (YYYY-MM-DD): ", scanner);
        
        if (jobControl.createJobPosting(postingID, jobID, companyID, postingDate, expirationDate)) {
            System.out.println(centerText("Posting created successfully as DRAFT!"));
        } else {
            System.out.println(centerText("Failed to create posting!"));
        }
    }

    private void viewAllPostings() {
        System.out.println("\n" + centerText("=== ALL POSTINGS ==="));
        ListInterface<Posting> postings = postingControl.getAllPostings();
        
        if (postings.isEmpty()) {
            System.out.println(centerText("No postings available."));
            return;
        }
        
        String format = "| %-10s | %-25s | %-20s | %-12s | %-10s | %-10s | %-8s | %-8s |";
        String divider = "+------------+---------------------------+----------------------+--------------+------------+------------+----------+----------+";
        
        System.out.println(centerText(divider));
        System.out.println(centerText(String.format(format, 
            "Posting ID", "Job Title", "Company", "Status", "Posted", "Expires", "Views", "Apps")));
        System.out.println(centerText(divider));
        
        for (Posting posting : postings) {
            String row = String.format(format,
                posting.getPostingID(),
                truncate(posting.getJob().getTitle(), 25),
                truncate(posting.getCompany().getName(), 20),
                posting.getStatus(),
                posting.getPostingDate(),
                posting.getExpirationDate(),
                posting.getViewCount(),
                posting.getApplicationCount());
            System.out.println(centerText(row));
        }
        
        System.out.println(centerText(divider));
        System.out.println(centerText("Total Postings: " + postings.getNumberOfEntries()));
    }

    private void viewActivePostings() {
        System.out.println("\n" + centerText("=== ACTIVE POSTINGS ==="));
        ListInterface<Posting> activePostings = postingControl.getActivePostings();
        
        if (activePostings.isEmpty()) {
            System.out.println(centerText("No active postings available."));
            return;
        }
        
        String format = "| %-10s | %-25s | %-20s | %-12s | %-10s | %-8s | %-8s |";
        String divider = "+------------+---------------------------+----------------------+--------------+------------+----------+----------+";
        
        System.out.println(centerText(divider));
        System.out.println(centerText(String.format(format, 
            "Posting ID", "Job Title", "Company", "Status", "Expires", "Views", "Apps")));
        System.out.println(centerText(divider));
        
        for (Posting posting : activePostings) {
            String row = String.format(format,
                posting.getPostingID(),
                truncate(posting.getJob().getTitle(), 25),
                truncate(posting.getCompany().getName(), 20),
                posting.getStatus(),
                posting.getExpirationDate(),
                posting.getViewCount(),
                posting.getApplicationCount());
            System.out.println(centerText(row));
        }
        
        System.out.println(centerText(divider));
        System.out.println(centerText("Total Active: " + activePostings.getNumberOfEntries()));
    }

    private void publishPosting() {
        System.out.println("\n" + centerText("=== PUBLISH POSTING ==="));
        String postingID = InputUtility.getStringInput("Enter Posting ID to publish: ", scanner);
        
        if (postingControl.publishPosting(postingID)) {
            System.out.println(centerText("Posting published successfully!"));
        } else {
            System.out.println(centerText("Failed to publish posting. Invalid ID or posting not in DRAFT status."));
        }
    }

    private void closePosting() {
        System.out.println("\n" + centerText("=== CLOSE POSTING ==="));
        String postingID = InputUtility.getStringInput("Enter Posting ID to close: ", scanner);
        
        if (postingControl.closePosting(postingID)) {
            System.out.println(centerText("Posting closed successfully!"));
        } else {
            System.out.println(centerText("Failed to close posting. Invalid ID or posting not in PUBLISHED status."));
        }
    }

    private void updatePostingExpiration() {
        System.out.println("\n" + centerText("=== UPDATE POSTING EXPIRATION ==="));
        String postingID = InputUtility.getStringInput("Enter Posting ID: ", scanner);
        String newExpirationDate = InputUtility.getStringInput("Enter new expiration date (YYYY-MM-DD): ", scanner);
        
        if (postingControl.updateExpirationDate(postingID, newExpirationDate)) {
            System.out.println(centerText("Posting expiration date updated successfully!"));
        } else {
            System.out.println(centerText("Failed to update expiration date. Invalid ID or date format."));
        }
    }

    private void recordPostingView() {
        System.out.println("\n" + centerText("=== RECORD POSTING VIEW ==="));
        String postingID = InputUtility.getStringInput("Enter Posting ID: ", scanner);
        
        if (postingControl.recordView(postingID)) {
            System.out.println(centerText("View recorded successfully!"));
        } else {
            System.out.println(centerText("Failed to record view. Invalid posting ID."));
        }
    }

    private void addPostingApplication() {
        System.out.println("\n" + centerText("=== ADD POSTING APPLICATION ==="));
        String postingID = InputUtility.getStringInput("Enter Posting ID: ", scanner);
        
        if (postingControl.addApplication(postingID)) {
            System.out.println(centerText("Application added successfully!"));
        } else {
            System.out.println(centerText("Failed to add application. Invalid posting ID."));
        }
    }

    private void addPostingNote() {
        System.out.println("\n" + centerText("=== ADD POSTING NOTE ==="));
        String postingID = InputUtility.getStringInput("Enter Posting ID: ", scanner);
        String note = InputUtility.getStringInput("Enter note to add: ", scanner);
        
        if (postingControl.addNote(postingID, note)) {
            System.out.println(centerText("Note added successfully!"));
        } else {
            System.out.println(centerText("Failed to add note. Invalid posting ID or empty note."));
        }
    }

    private void viewPostingDetails() {
        System.out.println("\n" + centerText("=== POSTING DETAILS ==="));
        String postingID = InputUtility.getStringInput("Enter Posting ID: ", scanner);
        Posting posting = postingControl.getPostingById(postingID);
        
        if (posting == null) {
            System.out.println(centerText("Posting not found!"));
            return;
        }
        
        System.out.println("\n" + centerText("=== POSTING INFORMATION ==="));
        System.out.println(centerText("Posting ID: " + posting.getPostingID()));
        System.out.println(centerText("Job Title: " + posting.getJob().getTitle()));
        System.out.println(centerText("Company: " + posting.getCompany().getName()));
        System.out.println(centerText("Status: " + posting.getStatus()));
        System.out.println(centerText("Posted Date: " + posting.getPostingDate()));
        System.out.println(centerText("Expiration Date: " + posting.getExpirationDate()));
        System.out.println(centerText("Views: " + posting.getViewCount()));
        System.out.println(centerText("Applications: " + posting.getApplicationCount()));
        
        if (posting.getPostingNotes() != null && !posting.getPostingNotes().isEmpty()) {
            System.out.println("\n" + centerText("=== NOTES ==="));
            System.out.println(centerText(posting.getPostingNotes()));
        }
        
        System.out.print(centerText("Press Enter to continue..."));
        scanner.nextLine();
    }

    private void generatePostingReport() {
        System.out.println("\n" + centerText("=== POSTING REPORT ==="));
        
    // Get current date and time (whole seconds, no decimals)
    java.time.LocalDateTime now = java.time.LocalDateTime.now();
    String formattedTime = String.format(
        "%04d-%02d-%02d %02d:%02d:%02d",  // %02d for whole seconds
        now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
        now.getHour(), now.getMinute(), now.getSecond()  // Just getSecond() without nanos
    );
    System.out.println(centerText("Generated on: " + formattedTime));
    System.out.println();
        
        // Get all postings
        ListInterface<Posting> allPostings = postingControl.getAllPostings();
        
        if (allPostings.isEmpty()) {
            System.out.println(centerText("No posting data available to generate report."));
            return;
        }
        
        // Ask user for report preferences
        System.out.println(centerText("=== REPORT OPTIONS ==="));
        System.out.println(centerText("What would you like to include in the report?"));
        System.out.println(centerText("1. Summary Statistics"));
        System.out.println(centerText("2. Status Counts"));
        System.out.println(centerText("3. Company-wise Postings"));
        System.out.println(centerText("4. All Active Postings"));
        System.out.println(centerText("5. All Postings (Detailed)"));
        System.out.print(centerText("Enter your choices (comma-separated, e.g. 1,2,3): "));
        
        String choicesInput = scanner.nextLine();
        String[] choices = choicesInput.split("\\s*,\\s*");
        
        // Process each selected option
        for (String choiceStr : choices) {
            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        displayPostingSummaryStatistics(allPostings);
                        break;
                    case 2:
                        displayPostingStatusCounts(allPostings);
                        break;
                    case 3:
                        displayCompanyWisePostings(allPostings);
                        break;
                    case 4:
                        displayActivePostingsReport(allPostings);
                        break;
                    case 5:
                        displayAllPostingsDetailed(allPostings);
                        break;
                    default:
                        System.out.println(centerText("Invalid choice skipped: " + choice));
                }
            } catch (NumberFormatException e) {
                System.out.println(centerText("Invalid input skipped: " + choiceStr));
            }
        }
        
        System.out.println("\n" + centerText("=== END OF REPORT ==="));
        System.out.print(centerText("Press Enter to continue..."));
        scanner.nextLine();
    }

    private void displayPostingSummaryStatistics(ListInterface<Posting> postings) {
        System.out.println("\n" + centerText("=== POSTING SUMMARY STATISTICS ==="));
        
        int totalPostings = postings.getNumberOfEntries();
        int activePostings = 0;
        int draftPostings = 0;
        int closedPostings = 0;
        int totalViews = 0;
        int totalApplications = 0;
        
        for (Posting p : postings) {
            totalViews += p.getViewCount();
            totalApplications += p.getApplicationCount();
            
            switch (p.getStatus()) {
                case "PUBLISHED":
                    activePostings++;
                    break;
                case "DRAFT":
                    draftPostings++;
                    break;
                case "CLOSED":
                    closedPostings++;
                    break;
            }
        }
        
        System.out.println(centerText("Total Postings: " + totalPostings));
        System.out.println(centerText("Active Postings: " + activePostings));
        System.out.println(centerText("Draft Postings: " + draftPostings));
        System.out.println(centerText("Closed Postings: " + closedPostings));
        System.out.println(centerText("Total Views: " + totalViews));
        System.out.println(centerText("Total Applications: " + totalApplications));
        
        if (totalPostings > 0) {
            System.out.println(centerText("Avg Views per Posting: " + 
                String.format("%.1f", (double)totalViews/totalPostings)));
            System.out.println(centerText("Avg Applications per Posting: " + 
                String.format("%.1f", (double)totalApplications/totalPostings)));
        }
    }

    private void displayPostingStatusCounts(ListInterface<Posting> postings) {
        System.out.println("\n" + centerText("=== POSTING STATUS COUNTS ==="));
        
        // Using sorting to group by status
        Posting[] postingArray = convertPostingsToArray(postings);
        quickSortPostingsByStatus(postingArray, 0, postingArray.length - 1);
        
        String currentStatus = null;
        int count = 0;
        
        for (Posting p : postingArray) {
            if (currentStatus == null || !currentStatus.equals(p.getStatus())) {
                if (currentStatus != null) {
                    System.out.println(centerText(currentStatus + ": " + count));
                }
                currentStatus = p.getStatus();
                count = 1;
            } else {
                count++;
            }
        }
        
        if (currentStatus != null) {
            System.out.println(centerText(currentStatus + ": " + count));
        }
    }

    private void displayCompanyWisePostings(ListInterface<Posting> postings) {
        System.out.println("\n" + centerText("=== COMPANY-WISE POSTINGS ==="));
        
        // Sort postings by company using quick sort
        Posting[] postingArray = convertPostingsToArray(postings);
        quickSortPostingsByCompany(postingArray, 0, postingArray.length - 1);
        
        String currentCompany = null;
        int count = 0;
        
        for (Posting p : postingArray) {
            String companyName = p.getCompany() != null ? p.getCompany().getName() : "Unknown";
            if (currentCompany == null || !currentCompany.equals(companyName)) {
                if (currentCompany != null) {
                    System.out.println(centerText(currentCompany + ": " + count + " postings"));
                }
                currentCompany = companyName;
                count = 1;
            } else {
                count++;
            }
        }
        
        if (currentCompany != null) {
            System.out.println(centerText(currentCompany + ": " + count + " postings"));
        }
    }

    private void displayActivePostingsReport(ListInterface<Posting> postings) {
        System.out.println("\n" + centerText("=== ACTIVE POSTINGS REPORT ==="));
        
        // Filter active postings
        ListInterface<Posting> activePostings = new SortedList<>();
        for (Posting p : postings) {
            if ("PUBLISHED".equals(p.getStatus())) {
                activePostings.add(p);
            }
        }
        
        if (activePostings.isEmpty()) {
            System.out.println(centerText("No active postings available."));
            return;
        }
        
        // Sort by expiration date (soonest first)
        Posting[] activeArray = convertPostingsToArray(activePostings);
        quickSortPostingsByExpiration(activeArray, 0, activeArray.length - 1);
        
        printPostingTable(activeArray);
    }

    private void displayAllPostingsDetailed(ListInterface<Posting> postings) {
        System.out.println("\n" + centerText("=== ALL POSTINGS (DETAILED) ==="));
        
        if (postings.isEmpty()) {
            System.out.println(centerText("No postings available."));
            return;
        }
        
        // Sort by status then by posting date (newest first)
        Posting[] postingArray = convertPostingsToArray(postings);
        quickSortPostingsByStatusAndDate(postingArray, 0, postingArray.length - 1);
        
        printPostingTable(postingArray);
    }

    // Helper methods for sorting and data processing
    private Posting[] convertPostingsToArray(ListInterface<Posting> list) {
        Posting[] array = new Posting[list.size()];
        int i = 0;
        for (Posting p : list) {
            array[i++] = p;
        }
        return array;
    }

    private void quickSortPostingsByStatus(Posting[] array, int low, int high) {
        if (low < high) {
            int pi = partitionPostingsByStatus(array, low, high);
            quickSortPostingsByStatus(array, low, pi - 1);
            quickSortPostingsByStatus(array, pi + 1, high);
        }
    }

    private int partitionPostingsByStatus(Posting[] array, int low, int high) {
        String pivot = array[high].getStatus();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j].getStatus().compareTo(pivot) <= 0) {
                i++;
                swapPostings(array, i, j);
            }
        }
        swapPostings(array, i + 1, high);
        return i + 1;
    }

    private void quickSortPostingsByCompany(Posting[] array, int low, int high) {
        if (low < high) {
            int pi = partitionPostingsByCompany(array, low, high);
            quickSortPostingsByCompany(array, low, pi - 1);
            quickSortPostingsByCompany(array, pi + 1, high);
        }
    }

    private int partitionPostingsByCompany(Posting[] array, int low, int high) {
        String pivot = array[high].getCompany() != null ? 
            array[high].getCompany().getName() : "";
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            String currentCompany = array[j].getCompany() != null ? 
                array[j].getCompany().getName() : "";
            if (currentCompany.compareTo(pivot) <= 0) {
                i++;
                swapPostings(array, i, j);
            }
        }
        swapPostings(array, i + 1, high);
        return i + 1;
    }

    private void quickSortPostingsByExpiration(Posting[] array, int low, int high) {
        if (low < high) {
            int pi = partitionPostingsByExpiration(array, low, high);
            quickSortPostingsByExpiration(array, low, pi - 1);
            quickSortPostingsByExpiration(array, pi + 1, high);
        }
    }

    private int partitionPostingsByExpiration(Posting[] array, int low, int high) {
        String pivot = array[high].getExpirationDate();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j].getExpirationDate().compareTo(pivot) <= 0) {
                i++;
                swapPostings(array, i, j);
            }
        }
        swapPostings(array, i + 1, high);
        return i + 1;
    }

    private void quickSortPostingsByStatusAndDate(Posting[] array, int low, int high) {
        if (low < high) {
            int pi = partitionPostingsByStatusAndDate(array, low, high);
            quickSortPostingsByStatusAndDate(array, low, pi - 1);
            quickSortPostingsByStatusAndDate(array, pi + 1, high);
        }
    }

    private int partitionPostingsByStatusAndDate(Posting[] array, int low, int high) {
        String pivotStatus = array[high].getStatus();
        String pivotDate = array[high].getPostingDate();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            int statusCompare = array[j].getStatus().compareTo(pivotStatus);
            if (statusCompare < 0 || 
                (statusCompare == 0 && array[j].getPostingDate().compareTo(pivotDate) >= 0)) {
                i++;
                swapPostings(array, i, j);
            }
        }
        swapPostings(array, i + 1, high);
        return i + 1;
    }

    private void swapPostings(Posting[] array, int i, int j) {
        Posting temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void printPostingTable(Posting[] postings) {
        String format = "| %-10s | %-25s | %-20s | %-12s | %-10s | %-10s | %-8s | %-8s |";
        String divider = "+------------+---------------------------+----------------------+--------------+------------+------------+----------+----------+";
        
        System.out.println(centerText(divider));
        System.out.println(centerText(String.format(format, 
            "Posting ID", "Job Title", "Company", "Status", "Posted", "Expires", "Views", "Apps")));
        System.out.println(centerText(divider));
        
        for (Posting posting : postings) {
            String row = String.format(format,
                posting.getPostingID(),
                truncate(posting.getJob().getTitle(), 25),
                truncate(posting.getCompany() != null ? posting.getCompany().getName() : "N/A", 20),
                posting.getStatus(),
                posting.getPostingDate(),
                posting.getExpirationDate(),
                posting.getViewCount(),
                posting.getApplicationCount());
            System.out.println(centerText(row));
        }
        
        System.out.println(centerText(divider));
        System.out.println(centerText("Total: " + postings.length + " postings"));
    }

    private void printSimpleJobList(ListInterface<Job> jobs) {
        String format = "| %-8s | %-25s | %-15s | %-15s |";
        String divider = "+----------+---------------------------+-----------------+-----------------+";
        
        System.out.println(centerText(divider));
        System.out.println(centerText(String.format(format, "Job ID", "Title", "Category", "Location")));
        System.out.println(centerText(divider));
        
        for (Job job : jobs) {
            String row = String.format(format,
                job.getJobID(),
                truncate(job.getTitle(), 25),
                truncate(job.getCategory(), 15),
                truncate(job.getLocation(), 15));
            System.out.println(centerText(row));
        }
        
        System.out.println(centerText(divider));
    }

    private void printSimpleCompanyList(ListInterface<Company> companies) {
        String format = "| %-10s | %-25s | %-20s | %-15s |";
        String divider = "+------------+---------------------------+----------------------+-----------------+";
        
        System.out.println(centerText(divider));
        System.out.println(centerText(String.format(format, "Company ID", "Name", "Industry", "Location")));
        System.out.println(centerText(divider));
        
        for (Company company : companies) {
            String row = String.format(format,
                company.getCompanyID(),
                truncate(company.getName(), 25),
                truncate(company.getIndustry(), 20),
                truncate(company.getLocation(), 15));
            System.out.println(centerText(row));
        }
        
        System.out.println(centerText(divider));
    }

    private String truncate(String str, int length) {
        if (str == null || str.isEmpty()) return "";
        return str.length() > length ? str.substring(0, length-3) + "..." : str;
    }
}