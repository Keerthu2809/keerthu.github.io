/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.InterviewControl;
import entity.Interview;
import entity.InterviewReport;
import entity.InterviewSchedule;
import entity.Recruitment;
import adt.ListInterface;
import adt.SortedList;
import control.MatchingEngineControl;
import entity.Match;
import java.util.Scanner;

/**
 *
 * @author rscma
 */
public class InterviewUI {

    private InterviewControl interviewControl; // Reference to InterviewControl
    private InterviewReport interviewReport;
    private MatchingEngineControl matchControl;
    private Scanner scanner;

    private int matchIDCounter = 1011; // Initialize the counter
    private int currentMatchIDCounter = 1011;

    public InterviewUI(InterviewControl interviewControl, MatchingEngineControl matchControl) {
        this.interviewControl = interviewControl; // Pass the InterviewControl instance
        this.matchControl = matchControl; // Pass the InterviewControl instance

        this.interviewReport = interviewReport; // Pass the InterviewReport instance

        this.scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            System.out.println("                       ");
            System.out.println("Interview Schedule Menu");
            System.out.println("1. Display All Interview Schedules");
            System.out.println("2. Add New Schedule");
            System.out.println("3. Update Schedule Details");
            System.out.println("4. Cancel Schedule");
            System.out.println("5. Manage Interview");
            System.out.println("6. Filter by Successful Recruitment/ Ranking");
            System.out.println("7. Generate All Report");
            System.out.println("8. Back to Student Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayAllSchedules();
                    break;
                case 2:
                    addNewSchedule();
                    break;
                case 3:
                    updateScheduleDetails();
                    break;
                case 4:
                    cancelSchedule();
                    break;
                case 5:
                    manageInterview();
                    break;
                case 6:
                    filterBySuccessfulRecruitment();
                    break;
                case 7:
                    manageReport();
                    break;
                case 8:
                    System.out.println("Returning to Student Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    private void displayAllSchedules() {
        ListInterface<Match> reportMatchList = matchControl.getReportedMatches();
        ListInterface<InterviewSchedule> scheduleList = interviewControl.getAllSchedules();

        if (reportMatchList == null || reportMatchList.isEmpty()) {
            System.out.println("No reported matches.");
            return;
        }

        if (scheduleList == null || scheduleList.isEmpty()) {
            System.out.println("No scheduled interviews.");
            return;
        }

        System.out.println("Scheduled Matches with Interview Schedules:\n");

        // Loop through the schedule list and print all schedules with associated Match IDs
        for (int i = 0; i < scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule schedule = scheduleList.get(i);
            String matchID = schedule.getMatchID(); // Get the associated Match ID

            // Check if the match ID exists in the reportMatchList
            Match associatedMatch = matchControl.getMatchByID(matchID); // Assuming you have this method

            if (associatedMatch != null) {
                // Print schedule and match details
                System.out.println("Schedule ID: " + schedule.getScheduleID());
                System.out.println("Match ID: " + associatedMatch.getMatchID());
                System.out.println("Schedule Location: " + schedule.getScheduleLocation());
                System.out.println("Schedule Date: " + schedule.getScheduleDate());
                System.out.println("Schedule Time: " + schedule.getScheduleTime());
                System.out.println("Match Student: " + associatedMatch.getStudent().getStudentName());
                System.out.println("Match Job Position: " + associatedMatch.getStudent().getDesiredJobType());
                System.out.println("Matched Skills: " + associatedMatch.getMatchedSkills());
                System.out.println("CGPA: " + associatedMatch.getStudent().getCgpa());
                System.out.println("Study Level: " + associatedMatch.getStudent().getStudyLevel());
                System.out.println("Match Score: " + String.format("%.2f", associatedMatch.getMatchScore()));
                System.out.println();
            }
        }
    }

    private void addNewSchedule() {
        // Step 1: Prompt for Match ID
        String matchID = getValidInput("Enter Match ID: ");
        System.out.println();

        // Step 2: Check if the Match ID exists
        Match existingMatch = matchControl.getMatchByID(matchID); // Assuming you have a method to get a match by ID
        if (existingMatch == null) {
            // Step 3: If no match found, indicate that
            System.out.println("Match ID " + matchID + " not found. Cannot assign schedule.");
            return; // Exit the method if the match ID is not found
        }

        // Step 4: Display match details
        System.out.println("Match Details Found:");
        System.out.println("Match ID: " + existingMatch.getMatchID());
        System.out.println("Match Student: " + existingMatch.getStudent().getStudentName());
        System.out.println("Match Job Position: " + existingMatch.getStudent().getDesiredJobType());
        System.out.println("Matched Skills: " + existingMatch.getMatchedSkills());
        System.out.println("CGPA: " + existingMatch.getStudent().getCgpa());
        System.out.println("Study Level: " + existingMatch.getStudent().getStudyLevel());
        System.out.println("Match Score: " + String.format("%.2f", existingMatch.getMatchScore()));
        System.out.println();

        // Step 5: Now continue with schedule entry
        String scheduleID = getValidInput("Enter Schedule ID: ");

        // Check if the Schedule ID already exists
        if (interviewControl.getScheduleByID(scheduleID) != null) {
            System.out.println("Schedule ID " + scheduleID + " already exists. Please enter a different Schedule ID.");
            return; // Exit if the Schedule ID already exists
        }

        String scheduleLocation = getValidInput("Enter Interview Location: ");
        String date = getValidDate(); // Get a valid date
        String time = getValidTime(date); // Get a valid time

        // Create a new InterviewSchedule with the associated Match ID
        InterviewSchedule newSchedule = new InterviewSchedule(scheduleID, scheduleLocation, date, time, matchID);
        interviewControl.addnewSchedule(newSchedule); // Add the new schedule to the interview control

        // Step 6: Display the newly added schedule along with the match ID
        System.out.println("\nNew Schedule Added:");
        System.out.println("Match ID: " + matchID); // Display the entered match ID
        System.out.println("Schedule ID: " + newSchedule.getScheduleID());
        System.out.println("Schedule Location: " + newSchedule.getScheduleLocation());
        System.out.println("Schedule Date: " + newSchedule.getScheduleDate());
        System.out.println("Schedule Time: " + newSchedule.getScheduleTime());
        System.out.println();
    }

    private void updateScheduleDetails() {
        // Step 1: Prompt for Schedule ID
        String scheduleID = getValidInput("Enter Schedule ID to update: ");

        // Step 2: Check if the Schedule ID exists
        InterviewSchedule existingSchedule = interviewControl.getScheduleByID(scheduleID); // Assuming you have a method to get a schedule by ID
        if (existingSchedule == null) {
            System.out.println("Schedule ID " + scheduleID + " not found. Cannot update schedule.");
            return; // Exit the method if the schedule ID is not found
        }

        // Step 3: Display current schedule details
        System.out.println("Current Schedule Details:");
        System.out.println("Schedule ID: " + existingSchedule.getScheduleID());
        System.out.println("Schedule Location: " + existingSchedule.getScheduleLocation());
        System.out.println("Schedule Date: " + existingSchedule.getScheduleDate());
        System.out.println("Schedule Time: " + existingSchedule.getScheduleTime());
        System.out.println();

        // Step 4: Prompt for new details
        String newLocation = getValidInput("Enter new Schedule Location (or press Enter to keep current): ");
        if (!newLocation.isEmpty()) {
            existingSchedule.setScheduleLocation(newLocation); // Update location if provided
        }

        String newDate = getValidDate(); // Get a valid date or keep current
        existingSchedule.setScheduleDate(newDate); // Update date

        String newTime = getValidTime(existingSchedule.getScheduleDate()); // Get a valid time or keep current
        existingSchedule.setScheduleTime(newTime); // Update time

        // Step 5: Confirm the update
        System.out.println("\nSchedule Updated Successfully:");
        System.out.println("Schedule ID: " + existingSchedule.getScheduleID());
        System.out.println("Updated Schedule Location: " + existingSchedule.getScheduleLocation());
        System.out.println("Updated Schedule Date: " + existingSchedule.getScheduleDate());
        System.out.println("Updated Schedule Time: " + existingSchedule.getScheduleTime());
        System.out.println();
    }
    private SortedList<InterviewSchedule> scheduleList = new SortedList<>(); // Your SortedList implementation

    private void cancelSchedule() {
        // Step 1: Prompt for Schedule ID
        System.out.print("Enter Schedule ID to remove: ");
        String scheduleID = scanner.nextLine().trim(); // Trim whitespace

        // Step 2: Check if the Schedule ID exists
        InterviewSchedule existingSchedule = interviewControl.getScheduleByID(scheduleID);
        if (existingSchedule == null) {
            System.out.println("Schedule ID " + scheduleID + " not found. Cannot remove schedule.");
            return; // Exit the method if the schedule ID is not found
        }

        // Step 3: Remove the schedule
        interviewControl.removeSchedule(existingSchedule); // Call the method to remove the schedule

        // Step 4: Confirm removal
        if (interviewControl.getScheduleByID(scheduleID) == null) { // Check if the schedule is no longer present
            System.out.println("Schedule removed successfully: Match ID: " + existingSchedule.getMatchID() + ", Schedule ID: " + scheduleID);
        } else {
            System.out.println("Failed to remove schedule: " + scheduleID);
        }
    }

    private InterviewSchedule findScheduleById(String scheduleID) {
        ListInterface<InterviewSchedule> scheduleList = interviewControl.getAllSchedules();
        for (int i = 0; i < scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule schedule = scheduleList.get(i);
            if (schedule.getScheduleID().equals(scheduleID)) {
                return schedule; // Schedule found
            }
        }
        return null; // Schedule not found
    }

    private String getValidInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input; // Valid input, return it
            } else {
                System.out.println("Input cannot be empty. Please enter a valid input.");
            }
        }
    }

    private String getValidDate() {
        String date;
        while (true) {
            System.out.print("Enter Interview Date (YYYY-MM-DD): ");
            date = scanner.nextLine();

            // Check if the input is empty
            if (date.isEmpty()) {
                System.out.println("Date cannot be empty. Please enter a valid date.");
                continue; // Prompt again
            }

            if (interviewControl.checkValidDate(date)) {
                return date; // Valid date, exit the loop
            } else {
                // Check if the year is the issue
                String yearStr = date.substring(0, 4);
                int year = Integer.parseInt(yearStr);
                if (year < 2025) {
                    System.out.println("Year cannot be before 2025. Please enter a valid date.");
                } else {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }
        }
    }

    private String getValidTime(String date) {
        String time;
        while (true) {
            System.out.print("Enter Interview Time (HH.MM): ");
            time = scanner.nextLine();
            if (interviewControl.checkValidTime(time) && !interviewControl.checkTimeExists(date, time)) {
                return time; // Valid time, return it
            } else {
                System.out.println("Invalid time format or time already exists. Please enter a different time.");
            }
        }
    }

    private String getValidStatus() {
        String interviewStatus;
        while (true) {
            System.out.print("Enter Interview Status (Pending/Completed): ");
            interviewStatus = scanner.nextLine().trim(); // Read input and trim whitespace
            if (interviewStatus.equalsIgnoreCase("Pending") || interviewStatus.equalsIgnoreCase("Completed")) {
                return interviewStatus; // Valid status, return it
            } else {
                System.out.println("Invalid status. Please enter 'Pending' or 'Completed'.");
            }
        }
    }

    private void manageInterview() {
        int choice;
        do {
            while (true) { // Loop to keep showing the menu until the user chooses to exit
                System.out.println("                     ");
                System.out.println("Manage Interview Menu");
                System.out.println("1. Display All Interviews");
                System.out.println("2. Add Interview");
                System.out.println("3. Update Interview Detail");
                System.out.println("4. Remove Interview");
                System.out.println("5. Back to Interview Schedule Menu");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        displayAllInterviews();
                        break; // Add break to prevent fall-through
                    case 2:
                        addNewInterview();
                        break; // Add break to prevent fall-through
                    case 3:
                        updateInterviewDetails();
                        break; // Add break to prevent fall-through
                    case 4:
                        removeInterview();
                        break; // Add break to prevent fall-through
                    case 5:
                        return; // Exit the loop and return to the previous menu
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } while (choice != 5);
    }

    private void displayAllInterviews() {
        System.out.println("\nAll Interviews:");
        ListInterface<Interview> interviews = interviewControl.getAllInterviews();

        if (interviews.isEmpty()) {
            System.out.println("No interviews found.");
        } else {
            // Print each interview's details
            for (int i = 0; i < interviews.getNumberOfEntries(); i++) {
                Interview interview = interviews.get(i);
                // Get the schedule ID and match ID from the associated schedule
                String scheduleID = (interview.getSchedule() != null) ? interview.getSchedule().getScheduleID() : "N/A";
                String matchID = interview.getMatchID(); // Assuming you have a getMatchID() method in Interview

                // Get the associated schedule
                InterviewSchedule schedule = interview.getSchedule(); // Assuming getSchedule() returns the InterviewSchedule
                String scheduleLocation = (schedule != null) ? schedule.getScheduleLocation() : "N/A"; // Handle null cases

                // Get the associated match (assuming you have a method to retrieve it)
                Match associatedMatch = matchControl.getMatchByID(matchID); // Assuming you have this method
                String studentName = (associatedMatch != null && associatedMatch.getStudent() != null)
                        ? associatedMatch.getStudent().getStudentName()
                        : "N/A"; // Handle null cases

                // Print interview details
                System.out.println("Interview ID: " + interview.getInterviewID());
                System.out.println("Schedule ID: " + scheduleID);
                System.out.println("Schedule Location: " + scheduleLocation);
                System.out.println("Schedule Date: " + (schedule != null ? schedule.getScheduleDate() : "N/A"));
                System.out.println("Schedule Time: " + (schedule != null ? schedule.getScheduleTime() : "N/A"));
                System.out.println("Match ID: " + matchID);
                System.out.println("Match Student: " + studentName);
                System.out.println("Interviewer: " + interview.getInterviewer());
                System.out.println("Interview Status: " + interview.getInterviewStatus());
                System.out.println("Feedback: " + interview.getFeedback());
                System.out.println(); // Add a blank line for better readability
            }
        }
    }

    private void addNewInterview() {
        // Step 1: Prompt for Schedule ID
        String interviewScheduleID = getValidInput("Enter Schedule ID: ");
        System.out.println();

        InterviewSchedule selectedSchedule = interviewControl.getScheduleByID(interviewScheduleID); // Assuming you have a method to get a schedule by ID

        // Step 2: Check if the Schedule ID exists
        if (selectedSchedule == null) {
            System.out.println("No schedule found with ID " + interviewScheduleID + ". Cannot proceed.");
            return; // Exit the method if the schedule ID is not found
        }

        // Step 3: Display schedule details
        System.out.println("Schedule Details Found:");
        System.out.println("Schedule ID: " + selectedSchedule.getScheduleID());
        System.out.println("Schedule Location: " + selectedSchedule.getScheduleLocation());
        System.out.println("Schedule Date: " + selectedSchedule.getScheduleDate());
        System.out.println("Schedule Time: " + selectedSchedule.getScheduleTime());

        // Step 4: Retrieve Match ID from the selected schedule
        String matchID = selectedSchedule.getMatchID(); // Get the Match ID from the selected schedule
        Match existingMatch = matchControl.getMatchByID(matchID); // Assuming you have a method to get a match by ID

        // Step 5: Check if the Match ID exists
        if (existingMatch != null) {
            System.out.println("Match ID: " + matchID);
            System.out.println("Match Student: " + existingMatch.getStudent().getStudentName());
            System.out.println("Match Job Position: " + existingMatch.getStudent().getDesiredJobType());
            System.out.println("Matched Skills: " + existingMatch.getMatchedSkills());
            System.out.println("CGPA: " + existingMatch.getStudent().getCgpa());
            System.out.println("Study Level: " + existingMatch.getStudent().getStudyLevel());
            System.out.println("Match Score: " + String.format("%.2f", existingMatch.getMatchScore()));
        } else {
            System.out.println("No match found with ID " + matchID + ". Cannot proceed.");
            return; // Exit the method if the match ID is not found
        }

        System.out.println();

        // Step 6: Prompt for Interview ID
        String interviewID = getValidInput("Enter Interview ID: ");
        System.out.println();

        // Step 7: Check if the Interview ID already exists
        if (interviewControl.checkInterviewFound(interviewID)) {
            System.out.println("An interview with ID " + interviewID + " already exists. Please enter a different Interview ID.");
            return; // Exit the method if the interview ID already exists
        }

        // Step 8: Prompt for additional interview details
        String interviewer = getValidInput("Enter Interviewer Name: ");
        String interviewStatus = getValidStatus(); // Assuming you have a method to get a valid status
        String feedback = getValidInput("Enter feedback: ");

        // Step 9: Create the Interview object
        Interview interview = new Interview(interviewID, selectedSchedule, matchID, interviewer, interviewStatus, feedback);

        // Step 10: Add the interview using the method defined in your class
        if (interviewControl.addInterview(interview)) {
            System.out.println("Interview added successfully.");
        } else {
            System.out.println("Failed to add interview. It may already exist.");
        }
    }

    private void updateInterviewDetails() {
        // Step 1: Prompt for Interview ID
        String interviewID = getValidInput("Enter Interview ID to update: ");
        System.out.println();

        Interview existingInterview = interviewControl.getInterviewByID(interviewID); // Assuming you have a method to get an interview by ID

        // Step 2: Check if the Interview ID exists
        if (existingInterview == null) {
            System.out.println("No interview found with ID " + interviewID + ". Cannot proceed.");
            return; // Exit the method if the interview ID is not found
        }

        // Step 3: Display current interview details
        System.out.println("Current Interview Details:");
        System.out.println("Interview ID: " + existingInterview.getInterviewID());

        // Retrieve the associated schedule
        InterviewSchedule schedule = existingInterview.getSchedule(); // Assuming getSchedule() returns the InterviewSchedule
        if (schedule != null) {
            System.out.println("Schedule ID: " + schedule.getScheduleID());
            System.out.println("Schedule Location: " + schedule.getScheduleLocation());
            System.out.println("Schedule Date: " + schedule.getScheduleDate());
            System.out.println("Schedule Time: " + schedule.getScheduleTime());
        } else {
            System.out.println("No schedule found for this interview.");
        }

        System.out.println("Match ID: " + existingInterview.getMatchID());

        // Step 4: Retrieve and display match student details
        String matchID = existingInterview.getMatchID();
        Match associatedMatch = matchControl.getMatchByID(matchID); // Assuming you have a method to get a match by ID
        if (associatedMatch != null && associatedMatch.getStudent() != null) {
            System.out.println("Match Student: " + associatedMatch.getStudent().getStudentName());
            System.out.println("Match Job Position: " + associatedMatch.getStudent().getDesiredJobType());
            System.out.println("Matched Skills: " + associatedMatch.getMatchedSkills());
            System.out.println("CGPA: " + associatedMatch.getStudent().getCgpa());
            System.out.println("Study Level: " + associatedMatch.getStudent().getStudyLevel());
        } else {
            System.out.println("No match student found for Match ID " + matchID + ".");
        }

        System.out.println("Interviewer: " + existingInterview.getInterviewer());
        System.out.println("Interview Status: " + existingInterview.getInterviewStatus());
        System.out.println("Feedback: " + existingInterview.getFeedback());
        System.out.println();

        // Step 5: Prompt for new details
        String newInterviewer = getValidInput("Enter new Interviewer Name (or press Enter to keep current): ");
        if (newInterviewer.isEmpty()) {
            newInterviewer = existingInterview.getInterviewer(); // Keep the current interviewer if no new name is provided
        }

        String newInterviewStatus = getValidStatus(); // Assuming you have a method to get a valid status
        String newFeedback = getValidInput("Enter new feedback (or press Enter to keep current): ");
        if (newFeedback.isEmpty()) {
            newFeedback = existingInterview.getFeedback(); // Keep the current feedback if no new feedback is provided
        }

        // Step 6: Update the Interview object
        existingInterview.setInterviewer(newInterviewer);
        existingInterview.setInterviewStatus(newInterviewStatus);
        existingInterview.setFeedback(newFeedback);

        // Step 7: Save the updated interview using the method defined in your class
        if (interviewControl.updateInterview(existingInterview)) { // Assuming you have an update method
            System.out.println("Interview updated successfully.");
        } else {
            System.out.println("Failed to update interview. Please try again.");
        }
    }

    private void removeInterview() {
        String interviewID = getValidInput("Enter Interview ID to remove: ");
        Interview interviewToRemove = findInterviewById(interviewID);

        if (interviewToRemove != null) {
            interviewControl.removeInterview(interviewToRemove);
            System.out.println("Interview removed successfully.");
        } else {
            System.out.println("Interview ID not found.");
        }
    }

    private Interview findInterviewById(String interviewID) {
        ListInterface<Interview> interviews = interviewControl.getAllInterviews();
        for (int i = 0; i < interviews.getNumberOfEntries(); i++) {
            Interview interview = interviews.get(i);
            if (interview.getInterviewID().equals(interviewID)) {
                return interview; // Interview found
            }
        }
        return null; // Interview not found
    }

    private void filterBySuccessfulRecruitment() {
        int filterChoice;
        do {
            System.out.println("\nFilter by Successful Recruitment/ Ranking Menu");
            System.out.println("1. Display All Candidates");
            System.out.println("2. Filter Successful Candidates Only");
            System.out.println("3. Display All Successful Recruitments with Ranking");
            System.out.println("4. Back to Interview Management Menu");
            System.out.print("Enter your choice: ");
            filterChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (filterChoice) {
                case 1:
                    displayAllRecruitments();
                    break;
                case 2:
                    System.out.print("Enter the passing score: ");
                    double passingScore = scanner.nextDouble();
                    interviewControl.filterSuccessfulCandidates(passingScore);
                    break;
                case 3:
                    // Display all successful candidates with the previously set passing score
                    interviewControl.displayAllSuccessfulRecruitments();
                    break;
                case 4:
                    System.out.println("Returning to Interview Management Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (filterChoice != 4);
    }

    private void displayAllRecruitments() {
        System.out.println("                            ");
        System.out.println("All Recruitments:");
        ListInterface<Recruitment> recruitmentList = interviewControl.getAllRecruitments();
        if (recruitmentList.isEmpty()) {
            System.out.println("No Candidate found.");
        } else {
            for (int i = 0; i < recruitmentList.getNumberOfEntries(); i++) {
                Recruitment recruitment = recruitmentList.get(i);
                System.out.println(recruitment.getRecruitmentDetails());
            }
        }
    }

    private void manageReport() {
        int choice;
        do {
            while (true) { // Loop to keep showing the menu until the user chooses to exit
                System.out.println("                     ");
                System.out.println("Manage Report Menu");
                System.out.println("1. Generate All Report");
                System.out.println("2. Generate Union Report");
                System.out.println("3. Generate Intersection Report");
                System.out.println("4. Generate Difference Report");
                System.out.println("5. Back to Interview Schedule Menu");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        interviewControl.generateMultipleComprehensiveReports(interviewControl, matchControl); // Call the method to generate multiple reports
                        break; // Add break to prevent fall-through
                    case 2:
                        interviewControl.generateMultipleComprehensiveReports(interviewControl, matchControl); // Call the method to generate multiple reports
                        break; // Add break to prevent fall-through
                    case 3:
                        interviewControl.generateMultipleComprehensiveReports(interviewControl, matchControl); // Call the method to generate multiple reports
                        break; // Add break to prevent fall-through
                    case 4:
                        interviewControl.generateMultipleComprehensiveReports(interviewControl, matchControl); // Call the method to generate multiple reports
                        break; // Add break to prevent fall-through
                    case 5:
                        return; // Exit the loop and return to the previous menu
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } while (choice != 5);
    }

}
