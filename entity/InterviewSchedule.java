/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedList;

/**
 *
 * @author rscma
 */
public class InterviewSchedule implements Comparable<InterviewSchedule> {

    private String scheduleID;
    private String scheduleLocation;
    private String scheduleDate; // Changed to String
    private String scheduleTime;
    private String matchID;
    private Match scheduleMatch; // This is the Match object

    public InterviewSchedule(String scheduleID, String scheduleLocation, String scheduleDate, String scheduleTime, String matchID) {
        this.scheduleID = scheduleID;
        this.scheduleLocation = scheduleLocation;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.scheduleMatch = null; // Initialize as null, we will set it later
        this.matchID = matchID; // Set the matchID
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleLocation(String scheduleLocation) {
        this.scheduleLocation = scheduleLocation;
    }

    public String getScheduleLocation() {
        return scheduleLocation;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    // Additional methods for functionality
    private static InterviewSchedule[] schedules = new InterviewSchedule[100];
    private static int scheduleCount = 0;

    public String getScheduleDetails() {

        String details = "Schedule ID: " + scheduleID + "\n"
                + "Schedule Location: " + scheduleLocation + "\n"
                + "Schedule Date: " + scheduleDate + "\n"
                + "Schedule Time: " + scheduleTime + "\n";
        return details;
    }

    public Match getScheduleMatch() {
        return scheduleMatch;
    }

    public void setScheduleMatch(Match scheduleMatch) {
        this.scheduleMatch = scheduleMatch;
    }

    @Override
    public int compareTo(InterviewSchedule other) {
        return this.scheduleID.compareTo(other.scheduleID);
    }

    public String getMatchID() {
        return matchID; // Getter for matchID
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Check if both references point to the same object
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Check for null and class type
        }
        InterviewSchedule that = (InterviewSchedule) obj; // Cast to InterviewSchedule
        return this.scheduleID.equals(that.scheduleID); // Compare only Schedule ID
    }

}
