/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ListInterface;
import adt.SortedList;
import control.InterviewControl;
import control.MatchingEngineControl;
import dao.InterviewInitializer;
import java.util.Iterator;

/**
 *
 * @author rscma
 */
public class InterviewReport {

    private String reportID;
    private String reportType;
    private String generateDate;
    private InterviewControl interviewControl;
    private MatchingEngineControl matchControl; // Add MatchControl instance

    public InterviewReport(String reportID, String reportType, String generateDate, InterviewControl interviewControl, MatchingEngineControl matchControl) {
        this.reportID = reportID;
        this.reportType = reportType;
        this.generateDate = generateDate;
        this.interviewControl = interviewControl;
        this.matchControl = matchControl; // Initialize MatchControl
    }

    // Implement compareTo method for sorting
    public int compareTo(InterviewReport other) {
        return this.generateDate.compareTo(other.generateDate); // Sort by generateDate
    }

    public void generateScheduleReport() {
        System.out.println("\nReport ID: " + reportID);
        System.out.println("Report Type: " + reportType);
        System.out.println("Generated Date: " + generateDate);
        System.out.println(getFormattedScheduleDetails());
    }

    public void generateInterviewReport() {
        System.out.println("Report ID: " + reportID);
        System.out.println("Report Type: " + reportType);
        System.out.println("Generated Date: " + generateDate);
        System.out.println(getFormattedInterviewDetails());
    }

    public void generateRecruitmentReport() {
        System.out.println("Report ID: " + reportID);
        System.out.println("Report Type: " + reportType);
        System.out.println("Generated Date: " + generateDate);
        System.out.println(getFormattedRecruitmentDetails());
    }

    public void generateReport() {
        System.out.println("Generating Comprehensive Report...");
        System.out.println("Report ID: " + reportID);
        System.out.println("Report Type: " + reportType);
        System.out.println("Generated Date: " + generateDate);

        // Display all details using InterviewControl methods
        System.out.println(getFormattedScheduleDetails());
        System.out.println(getFormattedInterviewDetails());
        System.out.println(getFormattedRecruitmentDetails());

    }

    public String getFormattedScheduleDetails() {
        StringBuilder scheduleDetails = new StringBuilder();
        scheduleDetails.append("\nSchedule Report:\n");

        // Define column widths
        int scheduleIdWidth = 16;
        int locationWidth = 18;
        int dateWidth = 15;
        int timeWidth = 16;
        int matchIdWidth = 16;
        int studentNameWidth = 18;
        int jobPositionWidth = 16;
        int matchedSkillsWidth = 16;
        int cgpaWidth = 10; // Adjusted width for CGPA

        // Header
        scheduleDetails.append("+------------------+--------------------+-----------------+------------------+------------------+--------------------+-----------------+------------------+----------+\n");
        scheduleDetails.append(String.format("| %-16s | %-18s | %-15s | %-16s | %-16s | %-18s | %-15s | %-16s | %-8s |\n",
                "Schedule ID", "Schedule Location", "Schedule Date", "Schedule Time", "Match ID", "Match Student", "Job Position", "Matched Skills", "CGPA"));
        scheduleDetails.append("+------------------+--------------------+-----------------+------------------+------------------+--------------------+-----------------+------------------+----------+\n");

        // Use an iterator to get all schedules
        Iterator<InterviewSchedule> scheduleIterator = interviewControl.getAllSchedules().iterator();
        while (scheduleIterator.hasNext()) {
            InterviewSchedule schedule = scheduleIterator.next();
            String matchID = schedule.getMatchID(); // Get the associated Match ID

            // Get the associated Match details
            Match associatedMatch = matchControl.getMatchByID(matchID); // Assuming you have this method

            if (associatedMatch != null) {
                // Format CGPA to 2 decimal places
                double cgpa = associatedMatch.getStudent().getCgpa(); // Assuming getCgpa() returns a double
                String formattedCgpa = String.format("%.2f", cgpa); // Format CGPA to 2 decimal places

                // Append schedule and match details to the report
                scheduleDetails.append(String.format("| %-16s | %-18s | %-15s | %-16s | %-16s | %-18s | %-15s | %-16s | %-8s |\n",
                        schedule.getScheduleID(),
                        schedule.getScheduleLocation(),
                        schedule.getScheduleDate(),
                        schedule.getScheduleTime(),
                        associatedMatch.getMatchID(),
                        associatedMatch.getStudent().getStudentName(),
                        associatedMatch.getStudent().getDesiredJobType(),
                        associatedMatch.getMatchedSkills(),
                        formattedCgpa)); // Use formatted CGPA
            }
        }
        scheduleDetails.append("+------------------+--------------------+-----------------+------------------+------------------+--------------------+-----------------+------------------+----------+\n"); // Bottom border
        return scheduleDetails.toString();
    }

    public String getFormattedInterviewDetails() {
        StringBuilder interviewDetails = new StringBuilder();
        interviewDetails.append("\nInterview Report:\n");

        // Define column widths
        int interviewIdWidth = 16;
        int interviewerWidth = 16;
        int statusWidth = 16;
        int feedbackWidth = 40; // Adjusted width for feedback
        int scheduleIdWidth = 16;
        int locationWidth = 18;
        int dateWidth = 15;
        int timeWidth = 16;
        int matchIdWidth = 16;
        int matchStudentWidth = 18; // Width for Match Student
        int jobPositionWidth = 16; // Width for Job Position

        // Header
        interviewDetails.append("+------------------+------------------+------------------+----------------------------------------+------------------+------------------+------------------+------------------+------------------+------------------+\n");
        interviewDetails.append(String.format("| %-16s | %-16s | %-16s | %-40s | %-16s | %-18s | %-15s | %-16s | %-16s | %-16s |\n",
                "Interview ID", "Interviewer", "Status", "Feedback", "Schedule ID", "Schedule Location", "Schedule Date", "Schedule Time", "Match ID", "Match Student"));
        interviewDetails.append("+------------------+------------------+------------------+----------------------------------------+------------------+------------------+------------------+------------------+------------------+------------------+\n");

        // Use an iterator to get all interviews directly
        Iterator<Interview> interviewIterator = InterviewInitializer.interviewList.iterator(); // Accessing the static list
        while (interviewIterator.hasNext()) {
            Interview interview = interviewIterator.next();

            // Get the associated InterviewSchedule
            InterviewSchedule schedule = interview.getSchedule(); // Assuming Interview has a method to get its schedule

            // Get the associated Match details
            Match associatedMatch = matchControl.getMatchByID(interview.getMatchID()); // Assuming you have this method

            // Handle the case where the interview, schedule, or match might not be found
            String interviewID = (interview != null) ? interview.getInterviewID() : "N/A"; // Accessing InterviewID directly
            String interviewer = (interview != null) ? interview.getInterviewer() : "N/A"; // Assuming getInterviewer() exists
            String interviewStatus = (interview != null) ? interview.getInterviewStatus() : "N/A"; // Assuming getInterviewStatus() exists
            String feedback = (interview != null) ? interview.getFeedback() : "N/A"; // Assuming getFeedback() exists
            String scheduleID = (schedule != null) ? schedule.getScheduleID() : "N/A"; // Accessing ScheduleID
            String scheduleLocation = (schedule != null) ? schedule.getScheduleLocation() : "N/A"; // Assuming getScheduleLocation() exists
            String scheduleDate = (schedule != null) ? schedule.getScheduleDate() : "N/A"; // Assuming getScheduleDate() exists
            String scheduleTime = (schedule != null) ? schedule.getScheduleTime() : "N/A"; // Assuming getScheduleTime() exists
            String matchID = (associatedMatch != null) ? associatedMatch.getMatchID() : "N/A"; // Accessing MatchID
            String matchStudent = (associatedMatch != null && associatedMatch.getStudent() != null)
                    ? associatedMatch.getStudent().getStudentName() : "N/A"; // Accessing Match Student Name
            String jobPosition = (associatedMatch != null) ? associatedMatch.getStudent().getDesiredJobType() : "N/A"; // Accessing Job Position

            // Append interview details to the report in a tabular format
            interviewDetails.append(String.format("| %-16s | %-16s | %-16s | %-40s | %-16s | %-18s | %-15s | %-16s | %-16s | %-16s |\n",
                    interviewID,
                    interviewer,
                    interviewStatus,
                    feedback,
                    scheduleID,
                    scheduleLocation,
                    scheduleDate,
                    scheduleTime,
                    matchID,
                    matchStudent));
        }

        // Append the bottom border of the table
        interviewDetails.append("+------------------+------------------+------------------+----------------------------------------+------------------+------------------+------------------+------------------+------------------+------------------+\n");

        return interviewDetails.toString();
    }

    public String getFormattedRecruitmentDetails() {
        StringBuilder recruitmentDetails = new StringBuilder();
        recruitmentDetails.append("\nSuccessful Recruitments Report:\n");

        // Header
        recruitmentDetails.append("+------------------+--------------------+------------------+------------------+\n");
        recruitmentDetails.append(String.format("| %-16s | %-18s | %-16s | %-16s |\n",
                "Candidate", "Test Score", "Joined Date", "Student Details"));
        recruitmentDetails.append("+------------------+--------------------+------------------+------------------+\n");

        // Use the correct method to get all successful recruitments
        Iterator<Recruitment> recruitmentIterator = interviewControl.getSuccessfulRecruitments().iterator();
        // Variables to track the highest and lowest candidates
        Recruitment lowestCandidate = null; // To store the candidate with the lowest score
        Recruitment highestCandidate = null; // To store the candidate with the highest score

        while (recruitmentIterator.hasNext()) {
            Recruitment recruitment = recruitmentIterator.next();
            recruitmentDetails.append(String.format("| %-16s | %-17s  | %-16s | %-16s |\n",
                    recruitment.getSuccessfulCandidate(),
                    recruitment.getTestScore(),
                    recruitment.getJoinedDate(),
                    recruitment.getStudentDetails()));

            // Update lowest and highest candidates based on their scores
            if (highestCandidate == null || recruitment.getTestScore() > highestCandidate.getTestScore()) {
                highestCandidate = recruitment; // Update the highest candidate
            }
            if (lowestCandidate == null || recruitment.getTestScore() < lowestCandidate.getTestScore()) {
                lowestCandidate = recruitment; // Update the lowest candidate
            }

        }
        recruitmentDetails.append(
                "+------------------+--------------------+------------------+------------------+\n"); // Bottom border

        // Display the lowest and highest scores if they exist
        if (lowestCandidate != null) {
            recruitmentDetails.append(String.format("Lowest Score: " + lowestCandidate.getSuccessfulCandidate() + " - " + lowestCandidate.getTestScore() + "\n"));
        }
        if (highestCandidate != null) {
            recruitmentDetails.append(String.format("Highest Score: " + highestCandidate.getSuccessfulCandidate() + " - " + highestCandidate.getTestScore()));
        }

        return recruitmentDetails.toString();
    }
}
