package boundary;
// author: CHONG PEI LEE
import adt.ListInterface;
import adt.SortedList;
import control.JobControl;
import dao.JobInitializer;
import entity.Company;
import entity.Job;
import utility.InputUtility;
import java.util.Scanner;

public class JobManagementUI {
    private final JobControl jobControl;
    private final Scanner scanner;
    private static final int CONSOLE_WIDTH = 120;
    private static final int MIN_PADDING = 10;

    public JobManagementUI(JobControl jobControl, Scanner scanner) {
        this.jobControl = jobControl;
        this.scanner = scanner;
    }

    public void start() {
        displayWelcome();
        
        int choice;
        do {
            displayMainMenu();
            System.out.print(centerText("Enter your choice (0-10): "));
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
        System.out.println(centerText("=== Job Management System ==="));
    }

    private void displayMainMenu() {
        System.out.println("\n" + centerText("=== JOB MANAGEMENT ==="));
        System.out.println(centerText("1. Add New Job"));
        System.out.println(centerText("2. Remove Job"));
        System.out.println(centerText("3. View All Jobs"));
        System.out.println(centerText("4. Filter by Location"));
        System.out.println(centerText("5. Filter by Salary Range"));
        System.out.println(centerText("6. Filter by Category"));
        System.out.println(centerText("7. Filter by Study Level"));
        System.out.println(centerText("8. Search Jobs by Title"));
        System.out.println(centerText("9. Set Operations"));
        System.out.println(centerText("10. Generate Job Report"));
        System.out.println(centerText("0. Exit"));
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addJob();
                break;
            case 2:
                removeJob();
                break;
            case 3:
                viewAllJobs();
                break;
            case 4:
                filterByLocation();
                break;
            case 5:
                filterBySalaryRange();
                break;
            case 6:
                filterByCategory();
                break;
            case 7:
                filterByStudyLevel();
                break;
            case 8:
                searchJobsByTitle();
                break;
            case 9:
                showSetOperationsMenu();
                break;
            case 10:
                generateJobReport();
                break;
            case 0:
                System.out.println(centerText("Exiting Job Management System..."));
                break;
            default:
                System.out.println(centerText("Invalid choice! Please try again."));
        }
    }

    private void addJob() {
        System.out.println("\n" + centerText("=== ADD NEW JOB ==="));

        String jobID;
        do {
            jobID = getRequiredInput("Job ID: ");
            if (jobControl.jobExists(jobID)) {
                System.out.println(centerText("A job with this ID already exists!"));
            }
        } while (jobControl.jobExists(jobID));

        String title = getRequiredInput("Title: ");
        String description = InputUtility.getStringInput("Description (optional): ", scanner);

        displayCompanyList();
        String companyID;
        Company company;
        do {
            companyID = getRequiredInput("Company ID: ");
            company = jobControl.getCompanyById(companyID);
            if (company == null) {
                System.out.println(centerText("Invalid company ID! Please try again."));
            }
        } while (company == null);

        // Handle location input
        String location;
        boolean isRemote = false;
        System.out.println(centerText("Is this a remote position? (Y/N): "));
        String remoteChoice = scanner.nextLine().trim();

        if (remoteChoice.equalsIgnoreCase("Y")) {
            isRemote = true;
            location = "Remote";
        } else {
            // Show company location as default
            System.out.println(centerText("Company location: " + company.getLocation()));
            System.out.println(centerText("Enter job location (or press Enter to use company location): "));
            String inputLoc = scanner.nextLine().trim();
            location = inputLoc.isEmpty() ? company.getLocation() : inputLoc;

            // Validate location
            while (!JobInitializer.isValidLocation(location)) {
                System.out.println(centerText("Invalid location! Valid locations are: " + 
                    String.join(", ", JobInitializer.VALID_LOCATIONS)));
                location = getRequiredInput("Enter valid location: ");
            }
        }

        String salaryRange = getRequiredInput("Salary Range (e.g., RM1000-RM2000): ");

        System.out.print("Required Skills (comma separated): ");
        String[] requiredSkills = scanner.nextLine().split("\\s*,\\s*");

        String studyLevel = getStudyLevelInput();
        String postedDate = "2023-01-01"; // Could use current date
        String deadline = "2023-12-31";   // Could calculate based on posted date
        String status = "Open";

        System.out.print("Benefits (comma separated): ");
        String[] benefits = scanner.nextLine().split("\\s*,\\s*");

        String category = getCategoryInput();

        Job job = new Job(
            jobID, 
            title, 
            description, 
            company.getCompanyID(), 
            location, 
            category, 
            salaryRange, 
            requiredSkills, 
            studyLevel, 
            postedDate, 
            deadline, 
            status, 
            benefits);
        job.setCompany(company);

        if (jobControl.addJob(job)) {
            System.out.println(centerText("Job added successfully!"));
        } else {
            System.out.println(centerText("Failed to add job"));
        }
    }

    private void removeJob() {
        System.out.println("\n" + centerText("=== REMOVE JOB ==="));
        String jobID = InputUtility.getStringInput("Enter Job ID to remove: ", scanner);
        
        if (jobControl.removeJob(jobID)) {
            System.out.println(centerText("Job removed successfully!"));
        } else {
            System.out.println(centerText("Job not found or invalid ID!"));
        }
    }

    private void viewAllJobs() {
        System.out.println("\n" + centerText("=== ALL JOBS ==="));
        ListInterface<Job> jobs = jobControl.getAllJobs();
        if (jobs.isEmpty()) {
            System.out.println(centerText("No jobs available."));
        } else {
            printJobs(jobs);
        }
    }

    private void printJobs(ListInterface<Job> jobs) {
        String format = "| %-6s | %-30s | %-28s | %-18s | %-24s | %-14s | %-12s |";
        String divider = "+--------+--------------------------------+------------------------------+--------------------+--------------------------+----------------+-------------+";

        System.out.println(centerText(divider));
        System.out.println(centerText(String.format(format,
            "ID", "Title", "Company", "Location", "Category", "Salary", "Study Level")));
        System.out.println(centerText(divider));

        for (Job job : jobs) {
            if (job.getCompany() == null) {
                continue;
            }

            String row = String.format(format,
                job.getJobID(),
                truncate(job.getTitle(), 30),
                truncate(job.getCompany().getName(), 28),
                truncate(job.getLocation(), 18),
                truncate(job.getCategory(), 24),
                job.getSalaryRange(),
                job.getStudyLevelRequired());

            System.out.println(centerText(row));
            System.out.println(centerText(divider));
        }

        System.out.println(centerText("Total Jobs: " + jobs.getNumberOfEntries()));
        System.out.println();

        System.out.print(centerText("Enter Job ID to view company details (or 0 to continue): "));
        String input = scanner.nextLine();
        if (!input.equals("0")) {
            Job selectedJob = findJobById(jobs, input);
            if (selectedJob != null && selectedJob.getCompany() != null) {
                displayCompanyProfile(selectedJob.getCompany());
            } else {
                System.out.println(centerText("Job not found or company information unavailable!"));
            }
        }
    }

    private Job findJobById(ListInterface<Job> jobs, String jobId) {
        for (Job job : jobs) {
            if (job.getJobID().equals(jobId)) {
                return job;
            }
        }
        return null;
    }

    private void displayCompanyProfile(Company company) {
        System.out.println("\n" + centerText("=== COMPANY PROFILE ==="));
        System.out.println(centerText("Name: " + company.getName()));
        System.out.println(centerText("Industry: " + company.getIndustry()));
        System.out.println(centerText("Location: " + company.getLocation()));
        System.out.println(centerText("Description: " + company.getDescription()));

        ListInterface<Job> companyJobs = jobControl.getJobsByCompany(company.getCompanyID());
        System.out.println("\n" + centerText("=== POSTED JOBS (" + companyJobs.getNumberOfEntries() + ") ==="));

        if (!companyJobs.isEmpty()) {
            String format = "| %-8s | %-20s | %-19s | %-11s | %-8s |";
            String divider = "+----------+----------------------+---------------------+-------------+----------+";

            System.out.println(centerText(divider));
            System.out.println(centerText(String.format(format,
                "ID", "Title", "Category", "Salary", "Status")));
            System.out.println(centerText(divider));

            for (Job job : companyJobs) {
                String row = String.format(format,
                    job.getJobID(),
                    truncate(job.getTitle(), 20),
                    truncate(job.getCategory(), 19),
                    job.getSalaryRange(),
                    job.getStatus());

                System.out.println(centerText(row));
                System.out.println(centerText(divider));
            }
        }

        System.out.print(centerText("Press Enter to continue..."));
        scanner.nextLine();
    }

    private void filterByLocation() {
        System.out.println("\n" + centerText("=== FILTER BY LOCATION ==="));
        String location = InputUtility.getStringInput("Enter location (or part of location): ", scanner);
        ListInterface<Job> filtered = jobControl.filterJobsByLocation(location);

        if (filtered.isEmpty()) {
            System.out.println(centerText("No jobs found matching '" + location + "'"));
        } else {
            System.out.println(centerText("Jobs matching location '" + location + "':"));
            printJobs(filtered);
        }
    }

    private void filterBySalaryRange() {
        System.out.println("\n" + centerText("=== FILTER BY SALARY RANGE ==="));
        System.out.println(centerText("Enter numbers only (e.g., 800) or with currency (e.g., RM800)"));
        String min = InputUtility.getStringInput("Enter minimum salary: ", scanner);
        String max = InputUtility.getStringInput("Enter maximum salary: ", scanner);
        ListInterface<Job> filtered = jobControl.filterJobsBySalaryRange(min, max);

        if (filtered.isEmpty()) {
            System.out.println(centerText("No jobs found in salary range " + min + "-" + max));
        } else {
            System.out.println(centerText("Jobs in salary range " + min + "-" + max + ":"));
            printJobs(filtered);
        }
    }

    private void filterByCategory() {
        System.out.println("\n" + centerText("=== FILTER BY CATEGORY ==="));
        String[] categories = {
            "Software Development", 
            "Data Science", 
            "Cybersecurity", 
            "AI", 
            "Networking"
        };

        System.out.println(centerText("Available categories:"));
        for (int i = 0; i < categories.length; i++) {
            System.out.println(centerText((i+1) + ". " + categories[i]));
        }

        System.out.print(centerText("Enter category number (1-" + categories.length + "): "));
        int choice = InputUtility.getIntInput(scanner);

        if (choice < 1 || choice > categories.length) {
            System.out.println(centerText("Invalid category number!"));
            return;
        }

        String selectedCategory = categories[choice-1];
        ListInterface<Job> filtered = jobControl.filterJobsByCategory(selectedCategory);

        if (filtered.isEmpty()) {
            System.out.println(centerText("No jobs found in " + selectedCategory + " category"));
        } else {
            System.out.println(centerText("Jobs in " + selectedCategory + " category:"));
            printJobs(filtered);
        }
    }

    private void filterByStudyLevel() {
        System.out.println("\n" + centerText("=== FILTER BY STUDY LEVEL ==="));
        
        String[] studyLevels = {"Diploma", "Degree"};
        
        System.out.println(centerText("Available levels:"));
        for (int i = 0; i < studyLevels.length; i++) {
            System.out.println(centerText((i+1) + ". " + studyLevels[i]));
        }
        
        System.out.print(centerText("Enter your choice (1-" + studyLevels.length + "): "));
        int choice = InputUtility.getIntInput(scanner);

        if (choice < 1 || choice > studyLevels.length) {
            System.out.println(centerText("Invalid choice!"));
            return;
        }

        String selectedLevel = studyLevels[choice-1];
        ListInterface<Job> filtered = jobControl.filterJobsByStudyLevel(selectedLevel);
        
        if (filtered.isEmpty()) {
            System.out.println(centerText("No jobs found for " + selectedLevel + " students"));
        } else {
            System.out.println(centerText("Jobs for " + selectedLevel + " students:"));
            printJobs(filtered);
        }
    }

    private void searchJobsByTitle() {
        System.out.println("\n" + centerText("=== SEARCH JOBS BY TITLE ==="));
        System.out.print(centerText("Enter job title or part of title to search: "));
        String searchTerm = scanner.nextLine().toLowerCase().trim();
        
        if (searchTerm.isEmpty()) {
            System.out.println(centerText("Search term cannot be empty!"));
            return;
        }

        // Get all jobs and sort them by title using QuickSort
        ListInterface<Job> allJobs = jobControl.getAllJobs();
        Job[] jobsArray = convertToArray(allJobs);
        quickSort(jobsArray, 0, jobsArray.length - 1);
        
        // Binary search for exact matches first
        Job exactMatch = binarySearch(jobsArray, searchTerm);
        
        // Linear search for partial matches
        ListInterface<Job> matchingJobs = new SortedList<>();
        for (Job job : jobsArray) {
            if (job.getTitle().toLowerCase().contains(searchTerm)) {
                matchingJobs.add(job);
            }
        }

        displaySearchResults(matchingJobs, searchTerm, exactMatch != null);
    }

    private Job[] convertToArray(ListInterface<Job> list) {
        Job[] array = new Job[list.size()];
        int index = 0;
        for (Job job : list) {
            array[index++] = job;
        }
        return array;
    }

    private void quickSort(Job[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(Job[] array, int low, int high) {
        String pivot = array[high].getTitle().toLowerCase();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j].getTitle().compareToIgnoreCase(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(Job[] array, int i, int j) {
        Job temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private Job binarySearch(Job[] sortedJobs, String searchTerm) {
        int low = 0;
        int high = sortedJobs.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midTitle = sortedJobs[mid].getTitle().toLowerCase();
            int cmp = midTitle.compareTo(searchTerm);
            
            if (cmp == 0) {
                return sortedJobs[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    private void displaySearchResults(ListInterface<Job> matchingJobs, String searchTerm, boolean hasExactMatch) {
        if (matchingJobs.isEmpty()) {
            System.out.println(centerText("\nNo jobs found matching: " + searchTerm));
        } else {
            System.out.println("\n" + centerText("=== SEARCH RESULTS ==="));
            if (hasExactMatch) {
                System.out.println(centerText("Found exact match for: " + searchTerm));
            }
            System.out.println(centerText("Total matches found: " + matchingJobs.getNumberOfEntries()));
            
            String format = "| %-8s | %-30s | %-20s | %-15s | %-12s |";
            String divider = "+----------+--------------------------------+----------------------+-----------------+------------+";
            
            System.out.println(centerText(divider));
            System.out.println(centerText(String.format(format, 
                "Job ID", "Title", "Company", "Location", "Category")));
            System.out.println(centerText(divider));
            
            for (Job job : matchingJobs) {
                String title = job.getTitle();
                if (title.equalsIgnoreCase(searchTerm)) {
                    title = "**" + title + "**";
                }
                
                String row = String.format(format,
                    job.getJobID(),
                    truncate(title, 30),
                    truncate(job.getCompany() != null ? job.getCompany().getName() : "N/A", 20),
                    truncate(job.getLocation(), 15),
                    truncate(job.getCategory(), 12));
                System.out.println(centerText(row));
            }
            System.out.println(centerText(divider));
        }
        
        System.out.print(centerText("\nPress Enter to continue..."));
        scanner.nextLine();
    }

    private void showSetOperationsMenu() {
        int choice;
        do {
            System.out.println("\n" + centerText("=== SET OPERATIONS MENU ==="));
            System.out.println(centerText("1. Combine Two Job Lists (Union)"));
            System.out.println(centerText("2. Find Common Jobs (Intersection)"));
            System.out.println(centerText("3. Find Unique Jobs (Difference)"));
            System.out.println(centerText("4. Advanced Filter (Multiple Criteria)"));
            System.out.println(centerText("0. Back to Main Menu"));
            System.out.print(centerText("Enter your choice: "));
            
            choice = InputUtility.getIntInput(scanner);
            
            switch (choice) {
                case 1:
                    performUnionOperation();
                    break;
                case 2:
                    performIntersectionOperation();
                    break;
                case 3:
                    performDifferenceOperation();
                    break;
                case 4:
                    performAdvancedFilter();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(centerText("Invalid choice! Please try again."));
            }
        } while (choice != 0);
    }
    
    private void performUnionOperation() {
        System.out.println("\n" + centerText("=== UNION OPERATION ==="));
        
        ListInterface<Job> list1 = getFilteredJobList("first");
        ListInterface<Job> list2 = getFilteredJobList("second");
        
        ListInterface<Job> result = jobControl.getUnionOfJobs(list1, list2);
        
        System.out.println(centerText("\nUnion Result (" + result.size() + " jobs):"));
        printJobs(result);
    }
    
    private void performIntersectionOperation() {
        System.out.println("\n" + centerText("=== INTERSECTION OPERATION ==="));
        
        ListInterface<Job> list1 = getFilteredJobList("first");
        ListInterface<Job> list2 = getFilteredJobList("second");
        
        ListInterface<Job> result = jobControl.getIntersectionOfJobs(list1, list2);
        
        System.out.println(centerText("\nIntersection Result (" + result.size() + " jobs):"));
        printJobs(result);
    }
    
    private void performDifferenceOperation() {
        System.out.println("\n" + centerText("=== DIFFERENCE OPERATION ==="));
        
        ListInterface<Job> list1 = getFilteredJobList("first");
        ListInterface<Job> list2 = getFilteredJobList("second");
        
        ListInterface<Job> result = jobControl.getDifferenceOfJobs(list1, list2);
        
        System.out.println(centerText("\nDifference Result (Jobs in first list but not in second):"));
        System.out.println(centerText("(" + result.size() + " jobs found)"));
        printJobs(result);
    }
    
    private void performAdvancedFilter() {
        System.out.println("\n" + centerText("=== ADVANCED FILTER ==="));
        
        System.out.print(centerText("Enter location (leave empty to skip): "));
        String location = scanner.nextLine().trim();
        
        System.out.print(centerText("Enter category (leave empty to skip): "));
        String category = scanner.nextLine().trim();
        
        System.out.print(centerText("Enter study level (leave empty to skip): "));
        String studyLevel = scanner.nextLine().trim();
        
        ListInterface<Job> result = jobControl.getJobsMatchingAllFilters(location, category, studyLevel);
        
        System.out.println(centerText("\nAdvanced Filter Result (" + result.size() + " jobs):"));
        printJobs(result);
    }
    
    private ListInterface<Job> getFilteredJobList(String listName) {
        System.out.println("\n" + centerText("=== CREATE " + listName.toUpperCase() + " JOB LIST ==="));
        System.out.println(centerText("Filter options:"));
        System.out.println(centerText("1. By Location"));
        System.out.println(centerText("2. By Category"));
        System.out.println(centerText("3. By Study Level"));
        System.out.println(centerText("4. All Jobs"));
        System.out.print(centerText("Enter your choice: "));
        
        int choice = InputUtility.getIntInput(scanner);
        
        switch (choice) {
            case 1:
                System.out.print(centerText("Enter location: "));
                String location = scanner.nextLine();
                return jobControl.filterJobsByLocation(location);
            case 2:
                return filterByCategoryForSetOperation();
            case 3:
                System.out.print(centerText("Enter study level: "));
                String studyLevel = scanner.nextLine();
                return jobControl.filterJobsByStudyLevel(studyLevel);
            case 4:
                return jobControl.getAllJobs();
            default:
                System.out.println(centerText("Invalid choice! Showing all jobs by default."));
                return jobControl.getAllJobs();
        }
    }
    
    private ListInterface<Job> filterByCategoryForSetOperation() {
        String[] categories = {
            "Software Development", 
            "Data Science", 
            "Cybersecurity", 
            "AI", 
            "Networking"
        };

        System.out.println(centerText("Available categories:"));
        for (int i = 0; i < categories.length; i++) {
            System.out.println(centerText((i+1) + ". " + categories[i]));
        }

        System.out.print(centerText("Enter category number: "));
        int choice = InputUtility.getIntInput(scanner);

        if (choice < 1 || choice > categories.length) {
            System.out.println(centerText("Invalid category number!"));
            return new SortedList<>();
        }

        return jobControl.filterJobsByCategory(categories[choice-1]);
    }

    private void generateJobReport() {
        System.out.println("\n" + centerText("=== JOB REPORT ==="));
        
    // Get current date and time (whole seconds, no decimals)
    java.time.LocalDateTime now = java.time.LocalDateTime.now();
    String formattedTime = String.format(
        "%04d-%02d-%02d %02d:%02d:%02d",  // %02d for whole seconds
        now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
        now.getHour(), now.getMinute(), now.getSecond()  // Just getSecond() without nanos
    );
    System.out.println(centerText("Generated on: " + formattedTime));
    System.out.println();
        
        // Get all jobs
        ListInterface<Job> allJobs = jobControl.getAllJobs();
        
        if (allJobs.isEmpty()) {
            System.out.println(centerText("No job data available to generate report."));
            return;
        }
        
        // Ask user for report preferences
        System.out.println(centerText("=== REPORT OPTIONS ==="));
        System.out.println(centerText("What would you like to include in the report?"));
        System.out.println(centerText("1. Summary Statistics"));
        System.out.println(centerText("2. Category Breakdown"));
        System.out.println(centerText("3. Location Distribution"));
        System.out.println(centerText("4. All Jobs (Detailed)"));
        System.out.print(centerText("Enter your choices (comma-separated, e.g. 1,2,3): "));
        
        String choicesInput = scanner.nextLine();
        String[] choices = choicesInput.split("\\s*,\\s*");
        
        // Process each selected option
        for (String choiceStr : choices) {
            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        displayJobSummaryStatistics(allJobs);
                        break;
                    case 2:
                        displayJobCategoryBreakdown(allJobs);
                        break;
                    case 3:
                        displayJobLocationDistribution(allJobs);
                        break;
                    case 4:
                        displayAllJobsDetailed(allJobs);
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

    private void displayJobSummaryStatistics(ListInterface<Job> jobs) {
        System.out.println("\n" + centerText("=== JOB SUMMARY STATISTICS ==="));
        
        int totalJobs = jobs.getNumberOfEntries();
        int activeJobs = 0;
        int closedJobs = 0;
        
        for (Job job : jobs) {
            if ("Open".equals(job.getStatus())) {
                activeJobs++;
            } else if ("Closed".equals(job.getStatus())) {
                closedJobs++;
            }
        }
        
        System.out.println(centerText("Total Jobs: " + totalJobs));
        System.out.println(centerText("Active Jobs: " + activeJobs));
        System.out.println(centerText("Closed Jobs: " + closedJobs));
    }

    private void displayJobCategoryBreakdown(ListInterface<Job> jobs) {
        System.out.println("\n" + centerText("=== JOB CATEGORY BREAKDOWN ==="));
        
        // Sort jobs by category using quick sort
        Job[] jobsArray = convertToArray(jobs);
        quickSortJobsByCategory(jobsArray, 0, jobsArray.length - 1);
        
        String currentCategory = null;
        int count = 0;
        
        for (Job job : jobsArray) {
            if (currentCategory == null || !currentCategory.equals(job.getCategory())) {
                if (currentCategory != null) {
                    System.out.println(centerText(currentCategory + ": " + count + " jobs"));
                }
                currentCategory = job.getCategory();
                count = 1;
            } else {
                count++;
            }
        }
        
        if (currentCategory != null) {
            System.out.println(centerText(currentCategory + ": " + count + " jobs"));
        }
    }

    private void quickSortJobsByCategory(Job[] array, int low, int high) {
        if (low < high) {
            int pi = partitionJobsByCategory(array, low, high);
            quickSortJobsByCategory(array, low, pi - 1);
            quickSortJobsByCategory(array, pi + 1, high);
        }
    }

    private int partitionJobsByCategory(Job[] array, int low, int high) {
        String pivot = array[high].getCategory();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j].getCategory().compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void displayJobLocationDistribution(ListInterface<Job> jobs) {
        System.out.println("\n" + centerText("=== LOCATION DISTRIBUTION ==="));
        
        // Sort jobs by location using quick sort
        Job[] jobsArray = convertToArray(jobs);
        quickSortJobsByLocation(jobsArray, 0, jobsArray.length - 1);
        
        String currentLocation = null;
        int count = 0;
        
        for (Job job : jobsArray) {
            if (currentLocation == null || !currentLocation.equals(job.getLocation())) {
                if (currentLocation != null) {
                    System.out.println(centerText(currentLocation + ": " + count + " jobs"));
                }
                currentLocation = job.getLocation();
                count = 1;
            } else {
                count++;
            }
        }
        
        if (currentLocation != null) {
            System.out.println(centerText(currentLocation + ": " + count + " jobs"));
        }
    }

    private void quickSortJobsByLocation(Job[] array, int low, int high) {
        if (low < high) {
            int pi = partitionJobsByLocation(array, low, high);
            quickSortJobsByLocation(array, low, pi - 1);
            quickSortJobsByLocation(array, pi + 1, high);
        }
    }

    private int partitionJobsByLocation(Job[] array, int low, int high) {
        String pivot = array[high].getLocation();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j].getLocation().compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void displayAllJobsDetailed(ListInterface<Job> jobs) {
        System.out.println("\n" + centerText("=== ALL JOBS (DETAILED) ==="));
        
        if (jobs.isEmpty()) {
            System.out.println(centerText("No jobs available."));
            return;
        }
        
        // Sort by status then by title
        Job[] jobsArray = convertToArray(jobs);
        quickSortJobsByStatusAndTitle(jobsArray, 0, jobsArray.length - 1);
        
        printDetailedJobTable(jobsArray);
    }

    private void quickSortJobsByStatusAndTitle(Job[] array, int low, int high) {
        if (low < high) {
            int pi = partitionJobsByStatusAndTitle(array, low, high);
            quickSortJobsByStatusAndTitle(array, low, pi - 1);
            quickSortJobsByStatusAndTitle(array, pi + 1, high);
        }
    }

    private int partitionJobsByStatusAndTitle(Job[] array, int low, int high) {
        String pivotStatus = array[high].getStatus();
        String pivotTitle = array[high].getTitle();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            int statusCompare = array[j].getStatus().compareTo(pivotStatus);
            if (statusCompare < 0 || 
                (statusCompare == 0 && array[j].getTitle().compareToIgnoreCase(pivotTitle) <= 0)) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void printDetailedJobTable(Job[] jobs) {
        String format = "| %-6s | %-30s | %-12s | %-28s | %-18s | %-24s | %-14s | %-10s |";
        String divider = "+--------+--------------------------------+--------------+------------------------------+--------------------+--------------------------+----------------+------------+";

        System.out.println(centerText(divider));
        System.out.println(centerText(String.format(format,
            "ID", "Title", "Status", "Company", "Location", "Category", "Salary", "Study Level")));
        System.out.println(centerText(divider));

        for (Job job : jobs) {
            String row = String.format(format,
                job.getJobID(),
                truncate(job.getTitle(), 30),
                job.getStatus(),
                truncate(job.getCompany() != null ? job.getCompany().getName() : "N/A", 28),
                truncate(job.getLocation(), 18),
                truncate(job.getCategory(), 24),
                job.getSalaryRange(),
                job.getStudyLevelRequired());

            System.out.println(centerText(row));
            System.out.println(centerText(divider));
        }

        System.out.println(centerText("Total: " + jobs.length + " jobs"));
    }

    private void displayCompanyList() {
        System.out.println("\n" + centerText("=== AVAILABLE COMPANIES ==="));
        ListInterface<Company> companies = jobControl.getAllCompanies();
        
        if (companies.isEmpty()) {
            System.out.println(centerText("No companies available."));
            return;
        }
        
        String format = "| %-10s | %-30s | %-20s | %-15s |";
        String divider = "+------------+--------------------------------+----------------------+-----------------+";
        
        System.out.println(centerText(divider));
        System.out.println(centerText(String.format(format, "ID", "Name", "Industry", "Location")));
        System.out.println(centerText(divider));
        
        for (Company company : companies) {
            String row = String.format(format,
                company.getCompanyID(),
                truncate(company.getName(), 30),
                truncate(company.getIndustry(), 20),
                truncate(company.getLocation(), 15));
            System.out.println(centerText(row));
        }
        
        System.out.println(centerText(divider));
    }

    private String getRequiredInput(String prompt) {
        String input;
        do {
            System.out.print(centerText(prompt));
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(centerText("This field is required!"));
            }
        } while (input.isEmpty());
        return input;
    }

    private String getCategoryInput() {
        String[] categories = {
            "Software Development", 
            "Data Science", 
            "Cybersecurity", 
            "AI", 
            "Networking"
        };

        System.out.println(centerText("Available categories:"));
        for (int i = 0; i < categories.length; i++) {
            System.out.println(centerText((i+1) + ". " + categories[i]));
        }

        int choice;
        do {
            System.out.print(centerText("Enter category number (1-" + categories.length + "): "));
            choice = InputUtility.getIntInput(scanner);
            if (choice < 1 || choice > categories.length) {
                System.out.println(centerText("Invalid choice!"));
            }
        } while (choice < 1 || choice > categories.length);

        return categories[choice-1];
    }

    private String getStudyLevelInput() {
        String[] levels = {"Diploma", "Degree"};
        
        System.out.println(centerText("Available study levels:"));
        for (int i = 0; i < levels.length; i++) {
            System.out.println(centerText((i+1) + ". " + levels[i]));
        }

        int choice;
        do {
            System.out.print(centerText("Enter your choice (1-" + levels.length + "): "));
            choice = InputUtility.getIntInput(scanner);
            if (choice < 1 || choice > levels.length) {
                System.out.println(centerText("Invalid choice!"));
            }
        } while (choice < 1 || choice > levels.length);

        return levels[choice-1];
    }

    private String truncate(String str, int length) {
        if (str == null || str.isEmpty()) return "";
        return str.length() > length ? str.substring(0, length-3) + "..." : str;
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
}