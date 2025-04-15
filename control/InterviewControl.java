/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Interview;
import entity.InterviewReport;
import entity.InterviewSchedule;
import entity.Recruitment;
import adt.ListInterface;
import adt.SortedList;
import boundary.InterviewUI;
import dao.InterviewInitializer;
import java.util.Iterator;
import java.util.Timer;

/**
 *
 * @author rscma
 */
public class InterviewControl {

    private ListInterface<Interview> interviewList = new SortedList<>(); // List of interviews
    private ListInterface<InterviewSchedule> scheduleList = new SortedList<>(); // List of schedules
    private ListInterface<Recruitment> recruitmentList = new SortedList<>(); // List of recruitments

    private InterviewUI interviewUI; // User interface for interviews

    private double passingScore; // Variable to store the passing score
    private boolean isPassingScoreSet; // Flag to check if the passing score has been set

    public InterviewControl() {
        // Initialize the user interface
        MatchingEngineControl matchControl = new MatchingEngineControl();
        this.interviewUI = new InterviewUI(this, matchControl); // Pass this instance to InterviewUI

        // Call the initialize method to populate the lists
        InterviewInitializer interviewInitializer = new InterviewInitializer(); // Create an instance to call initialize        
        scheduleList = InterviewInitializer.scheduleList;
        interviewList = InterviewInitializer.interviewList;
        recruitmentList = InterviewInitializer.recruitmentList;

        isPassingScoreSet = false; // Initialize the flag to false
    }

    // Additional methods for managing interviews, schedules, and recruitments can be added here
    public static void main(String[] args) {
        InterviewControl interviewControl = new InterviewControl(); // Create an instance
        interviewControl.run(); // Start the user interface
    }

    public void run() {
        interviewUI.run(); // Start the user interface
    }

    // Method to add a new interview
    public boolean addInterview(Interview interview) {
        if (interview == null || interviewList.contains(interview)) {
            return false; // Interview is null or already exists
        }
        interviewList.add(interview);
        return true; // Interview added successfully
    }

    public void removeSchedule(InterviewSchedule schedule) {
        boolean removed = scheduleList.remove(schedule); // Ensure this method returns true if the schedule was removed
        if (removed) {
            System.out.println("Schedule removed from control: " + schedule.getScheduleID());
        } else {
            System.out.println("Failed to remove schedule: " + schedule.getScheduleID());
        }
    }
// Method to remove an interview

    public boolean removeInterview(Interview interview) {
        return interviewList.remove(interview);
    }

    // Method to get all interviews
    public ListInterface<Interview> getAllInterviews() {
        return interviewList;
    }

    // Method to add a new schedule
    public boolean addSchedule(InterviewSchedule schedule) {
        if (schedule == null || scheduleList.contains(schedule)) {
            return false; // Schedule is null or already exists
        }
        scheduleList.add(schedule);
        return true; // Schedule added successfully
    }

    public boolean containsInterviewSchedule(String scheduleID) {
        Iterator<InterviewSchedule> iterator = scheduleList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getScheduleID().equals(scheduleID)) {
                return true;
            }
        }
        return false;
    }

    public void addnewSchedule(InterviewSchedule interviewSchedule) {
        if (containsInterviewSchedule(interviewSchedule.getScheduleID())) {
            System.out.println("Schedule ID already exists!"); // Correct message
            return;
        }
        scheduleList.add(interviewSchedule);
        System.out.println("New Schedule successfully added!");
    }

    // Method to get all schedules
    public ListInterface<InterviewSchedule> getAllSchedules() {
        return scheduleList;
    }

    // Method to update a schedule
    public boolean updateSchedule(InterviewSchedule schedule) {
        if (schedule == null) {
            return false; // Schedule is null
        }
        for (int i = 0; i < scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule existingSchedule = scheduleList.get(i);
            if (existingSchedule.getScheduleID().equals(schedule.getScheduleID())) {
                scheduleList.update(i, schedule); //update method from adt
                return true; // Schedule updated successfully
            }
        }
        return false; // Schedule not found
    }

    // Method to update an interview
    public boolean updateInterview(Interview interview) {
        if (interview == null) {
            return false; // Interview is null
        }
        for (int i = 0; i < interviewList.getNumberOfEntries(); i++) {
            Interview existingInterview = interviewList.get(i);
            if (existingInterview.getInterviewID().equals(interview.getInterviewID())) {
                interviewList.update(i, interview);
                return true; // Interview updated successfully
            }
        }
        return false; // Interview not found
    }

    private boolean isScheduleIDExists(String scheduleID) {
        for (int i = 0; i < scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule existingSchedule = scheduleList.get(i);
            if (existingSchedule.getScheduleID().equals(scheduleID)) {
                return true; // Schedule ID already exists
            }
        }
        return false; // Schedule ID does not exist
    }

    private boolean isTimeExists(String date, String time) {
        for (int i = 0; i < scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule existingSchedule = scheduleList.get(i);
            if (existingSchedule.getScheduleDate().equals(date) && existingSchedule.getScheduleTime().equals(time)) {
                return true; // Same date and time already exists
            }
        }
        return false; // No conflict found
    }

    private boolean isInterviewFound(String interviewID) {
        for (int i = 0; i < interviewList.getNumberOfEntries(); i++) {
            Interview existingInterview = interviewList.get(i);
            if (existingInterview.getInterviewID().equals(interviewID)) {
                return true; // Schedule ID already exists
            }
        }
        return false; // Schedule ID does not exist
    }

    private boolean isInterviewScheduleIDExists(String interviewScheduleID) {
        for (Interview existingInterview : interviewList) {
            // Assuming getSchedule() returns the associated InterviewSchedule object
            InterviewSchedule schedule = existingInterview.getSchedule();
            if (schedule != null && schedule.getScheduleID().equals(interviewScheduleID)) {
                return true; // Schedule ID already exists
            }
        }
        return false; // Schedule ID does not exist
    }

    private boolean isValidDate(String date) {
        // Check if the date is in the correct format (length and separators)
        if (date.length() != 10 || date.charAt(4) != '-' || date.charAt(7) != '-') {
            return false; // Invalid format
        }

        // Extract year, month, and day as strings
        String yearStr = date.substring(0, 4);
        String monthStr = date.substring(5, 7);
        String dayStr = date.substring(8, 10);

        // Check if year, month, and day are numeric
        if (!isNumeric(yearStr) || !isNumeric(monthStr) || !isNumeric(dayStr)) {
            return false; // Non-numeric values
        }

        // Parse year, month, and day to integers
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        int day = Integer.parseInt(dayStr);

        // Check if the year is before 2025
        if (year < 2025) {
            return false; // Year cannot be before 2025
        }
        // Check for valid month
        if (month < 1 || month > 12) {
            return false; // Invalid month
        }

        // Check for valid day based on month
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return day >= 1 && day <= 31; // 31 days
            case 4:
            case 6:
            case 9:
            case 11:
                return day >= 1 && day <= 30; // 30 days
            case 2:
                // Check for leap year
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return day >= 1 && day <= 29; // Leap year
                } else {
                    return day >= 1 && day <= 28; // Non-leap year
                }
            default:
                return false; // Invalid month
        }
    }

    private boolean isValidTime(String time) {
        // Check if the time is in the correct format (HH.MM)
        if (time.length() != 5 || time.charAt(2) != '.') {
            return false; // Invalid format
        }

        // Extract hours and minutes
        String hourStr = time.substring(0, 2);
        String minuteStr = time.substring(3, 5);

        // Check if hours and minutes are numeric
        if (!isNumeric(hourStr) || !isNumeric(minuteStr)) {
            return false; // Non-numeric values
        }

        // Parse hours and minutes to integers
        int hours = Integer.parseInt(hourStr);
        int minutes = Integer.parseInt(minuteStr);

        // Check for valid hours and minutes
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            return false; // Invalid hour or minute
        }

        return true; // Valid time
    }

    // Helper method to check if a string is numeric
    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // Non-numeric character found
            }
        }
        return true; // All characters are numeric
    }

    // Public method to check if a schedule ID exists
    public boolean checkScheduleIDExists(String scheduleID) {
        return isScheduleIDExists(scheduleID); // Call the private method
    }

    // Public method to check if a time exists for a given date
    public boolean checkTimeExists(String date, String time) {
        return isTimeExists(date, time); // Call the private method
    }

    // Public method to check if the date is valid
    public boolean checkValidDate(String date) {
        return isValidDate(date); // Call the private method
    }

    public boolean checkValidTime(String time) {
        return isValidTime(time); // Call the private method
    }

    public boolean checkInterviewFound(String interviewID) {
        return isInterviewFound(interviewID);
    }

    // Public method to check if the interview schedule ID exists
    public boolean checkInterviewScheduleIDExists(String interviewScheduleID) {
        return isInterviewScheduleIDExists(interviewScheduleID);
    }

    // Method to filter successful candidates
    public void filterSuccessfulCandidates(double passingScore) {
        this.passingScore = passingScore; // Store the passing score
        this.isPassingScoreSet = true; // Set the flag to true
        System.out.println("\nRanked Successful Candidates (Score >= " + passingScore + "):");
        boolean found = false; // Flag to check if any candidates are found

        Iterator<Recruitment> iterator = recruitmentList.iterator();
        while (iterator.hasNext()) {
            Recruitment recruitment = iterator.next();
            if (recruitment.getTestScore() >= passingScore) {
                System.out.println(recruitment.getSuccessfulCandidate());
                found = true; // Set flag to true if a candidate is found
            }
        }

        if (!found) {
            System.out.println("No candidates found with a score of " + passingScore + " or higher.");
        }
    }

    // Method to display all successful candidates based on the stored passing score
    public void displayAllSuccessfulRecruitments() {
        if (!isPassingScoreSet) {
            return; // Exit if the passing score is not set
        }

        System.out.println("\nAll Successful Recruitment Ranked by Test Score (Score >= " + passingScore + "):");
        Iterator<Recruitment> iterator = recruitmentList.iterator();
        int rank = 1; // Start ranking from 1

        // Variables to track the highest and lowest candidates
        Recruitment highestCandidate = null; // To store the candidate with the highest score
        Recruitment lowestCandidate = null; // To store the candidate with the lowest score

        // Check if the list is empty
        if (!iterator.hasNext()) {
            System.out.println("No candidates available.");
            return;
        }

        // Initialize a flag to check if any successful candidates are displayed
        boolean hasDisplayedCandidates = false;

        // Iterate through the candidates
        while (iterator.hasNext()) {
            Recruitment recruitment = iterator.next();

            // Only consider candidates who meet the passing score requirement
            if (recruitment.getTestScore() >= passingScore) {
                // Display the candidate's rank, name, score, joined date, and student details
                System.out.println(rank + ". " + recruitment.getSuccessfulCandidate() + "\n"
                        + "Test Score: " + recruitment.getTestScore() + "\n"
                        + "Joined Date: " + recruitment.getJoinedDate());
                hasDisplayedCandidates = true; // Set the flag to true

                // Update highest and lowest candidates based on their scores
                if (highestCandidate == null || recruitment.getTestScore() > highestCandidate.getTestScore()) {
                    highestCandidate = recruitment; // Update the highest candidate
                }
                if (lowestCandidate == null || recruitment.getTestScore() < lowestCandidate.getTestScore()) {
                    lowestCandidate = recruitment; // Update the lowest candidate
                }

                rank++;
            }
        }

        // If no successful candidates were displayed, inform the user
        if (!hasDisplayedCandidates) {
            System.out.println("No candidates meet the minimum score requirement of " + passingScore + ".");
            return;
        }

        // Display the lowest and highest candidates
        if (lowestCandidate != null) {
            System.out.println("Lowest Score: " + lowestCandidate.getSuccessfulCandidate()
                    + " - Score: " + lowestCandidate.getTestScore());
        }

        if (highestCandidate != null) {
            System.out.println("Highest Score: " + highestCandidate.getSuccessfulCandidate()
                    + " - Score: " + highestCandidate.getTestScore());
        }
    }

    // Method to get all recruitments
    public ListInterface<Recruitment> getAllRecruitments() {
        return recruitmentList;
    }

    public ListInterface<Recruitment> getSuccessfulRecruitments() {
        ListInterface<Recruitment> successfulRecruitments = new SortedList<>(); // Assuming you have a sorted list implementation

        if (!isPassingScoreSet) {
            return successfulRecruitments; // Return empty list if passing score is not set
        }

        // Iterate through the recruitment list to find successful candidates
        Iterator<Recruitment> iterator = recruitmentList.iterator();
        while (iterator.hasNext()) {
            Recruitment recruitment = iterator.next();
            // Only consider candidates who meet the passing score requirement
            if (recruitment.getTestScore() >= passingScore) {
                successfulRecruitments.add(recruitment); // Add to the list of successful candidates
            }
        }

        return successfulRecruitments; // Return the list of successful candidates
    }

    public void generateMultipleComprehensiveReports(InterviewControl interviewControl, MatchingEngineControl matchControl) {
        // Define the report details
        String[] reportIDs = {"R001", "R002", "R003"};
        String[] reportTypes = {"Schedule Report", "Interview Report", "Successful Recruitment Report"};

        // Create a Timer to get the current date
        Timer timer = new Timer();
        final String[] generateDate = new String[1]; // Array to hold the date

        // Schedule a task to get the current date
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                // Get the current time in milliseconds
                long currentTimeMillis = System.currentTimeMillis();
                // Convert to a date string in the format "yyyy-MM-dd"
                generateDate[0] = convertMillisToDateString(currentTimeMillis);
                timer.cancel(); // Cancel the timer after getting the date
            }
        }, 0); // Schedule immediately

        // Wait for the timer task to complete (this is a simple way to block until the date is set)
        try {
            Thread.sleep(100); // Sleep for a short time to allow the timer task to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Loop through the predefined report details
        for (int i = 0; i < reportIDs.length; i++) {
            // Create and generate the report based on the report type
            InterviewReport report = new InterviewReport(reportIDs[i], reportTypes[i], generateDate[0], interviewControl, matchControl);

            // Generate the report based on the type
            switch (reportTypes[i]) {
                case "Schedule Report":
                    report.generateScheduleReport(); // Generate only the schedule report
                    break;
                case "Interview Report":
                    report.generateInterviewReport(); // Generate only the interview report
                    break;
                case "Successful Recruitment Report":
                    report.generateRecruitmentReport(); // Generate only the recruitment report
                    break;
                default:
                    System.out.println("Unknown report type: " + reportTypes[i]);
            }
        }
    }

    private String convertMillisToDateString(long millis) {
        // Constants for time calculations
        final long SECONDS_IN_A_DAY = 86400;
        final long MILLISECONDS_IN_A_DAY = 86400000;

        // Calculate total days since epoch
        long totalDays = millis / MILLISECONDS_IN_A_DAY;

        // Calculate year
        int year = 1970;
        while (totalDays >= (isLeapYear(year) ? 366 : 365)) {
            totalDays -= (isLeapYear(year) ? 366 : 365);
            year++;
        }

        // Calculate month and day
        int month = 0;
        int[] daysInMonth = isLeapYear(year) ? new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
                : new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        while (totalDays >= daysInMonth[month]) {
            totalDays -= daysInMonth[month];
            month++;
        }

        int day = (int) totalDays + 1; // Day of the month (1-based)

        // Format the date string
        return String.format("%04d-%02d-%02d", year, month + 1, day); // Adjust month to be 1-based
    }

// Helper method to check if a year is a leap year
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public InterviewSchedule getScheduleByID(String scheduleID) {
        // Assuming you have a ListInterface<InterviewSchedule> called scheduleList
        ListInterface<InterviewSchedule> scheduleList = this.getAllSchedules(); // Method to get all schedules

        // Loop through the schedule list to find the matching Schedule ID
        for (int i = 0; i < scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule schedule = scheduleList.get(i);
            if (schedule.getScheduleID().equals(scheduleID)) {
                return schedule; // Return the matching schedule
            }
        }

        // If no matching schedule is found, return null
        return null;
    }

    public Interview getInterviewByID(String interviewID) {
        // Assuming you have a ListInterface<InterviewSchedule> called scheduleList
        ListInterface<Interview> interviewList = this.getAllInterviews(); // Method to get all schedules

        // Loop through the schedule list to find the matching Schedule ID
        for (int i = 0; i < interviewList.getNumberOfEntries(); i++) {
            Interview interview = interviewList.get(i);
            if (interview.getInterviewID().equals(interviewID)) {
                return interview; // Return the matching schedule
            }
        }

        // If no matching schedule is found, return null
        return null;
    }

  
}
