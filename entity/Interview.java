/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author rscma
 */
public class Interview implements Comparable<Interview> {

    private String interviewID;
    private String interviewer;
    private String interviewStatus;
    private String feedback;
    private InterviewSchedule schedule;
    private String matchID;

    private InterviewReport report; // New property
    private Recruitment recruitment; // Reference to the recruitment object

    public Interview(String interviewID, InterviewSchedule schedule, String matchID, String interviewer, String interviewStatus, String feedback) {
        this.interviewID = interviewID;
        this.schedule = schedule; // This should be selectedSchedule
        this.matchID = matchID;
        this.interviewStatus = interviewStatus;
        this.feedback = feedback;
        this.interviewer = interviewer;
    }

    public String getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(String interviewID) {
        this.interviewID = interviewID;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void assignStudentFeedback(String interviewID, String feedback) {
        if (this.schedule.equals(interviewID)) {
        }
    }

    public String getInterviewDetails() {
        String details = "Interview ID: " + interviewID + "\n"
                + "Interviewer: " + interviewer + "\n"
                + "Interview Status: " + interviewStatus + "\n"
                + "Feedback: " + feedback + "\n";
        return details;
    }

    @Override
    public int compareTo(Interview other) {
        return this.interviewID.compareTo(other.interviewID);
    }

    public InterviewSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(InterviewSchedule schedule) {
        this.schedule = schedule;
    }

    public String getMatchID() {
        return matchID; // Getter for matchID
    }

    public Recruitment getRecruitment() {
        return recruitment; // Return the associated Recruitment object
    }
}
