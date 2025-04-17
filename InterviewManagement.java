/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedList;
import entity.InterviewSchedule;
/**
 *
 * @author ROG G14
 */
public class InterviewManagement {
    private LinkedList<InterviewSchedule> scheduleList = new LinkedList<>() ;


    // Create a new interview schedule
    public boolean createSchedule(InterviewSchedule schedule) {
        if (!containsSchedule(schedule.getScheduleId())) {
            scheduleList.add(schedule); //ADT Method
            return true;
        }
        return false; // Schedule with the same ID already exists
    }

    // Update an existing interview schedule
    public boolean updateSchedule(String scheduleId, InterviewSchedule updatedSchedule) {
        for (int i = 1; i <= scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule currentSchedule = scheduleList.getEntry(i);
            if (currentSchedule.getScheduleId().equals(scheduleId)) {
                scheduleList.replace(i, updatedSchedule);
                return true;
            }
        }
        return false; // Schedule not found
    }

    // Delete an interview schedule
    public boolean deleteSchedule(String scheduleId) {
        for (int i = 1; i <= scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule currentSchedule = scheduleList.getEntry(i);
            if (currentSchedule.getScheduleId().equals(scheduleId)) {
                scheduleList.remove(i);
                return true;
            }
        }
        return false; // Schedule not found
    }

    // Filter schedules by applicant ID
    public LinkedList<InterviewSchedule> filterSchedulesByApplicant(String applicantId) {
        LinkedList<InterviewSchedule> filteredSchedules = new LinkedList<>();
        for (int i = 1; i <= scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule currentSchedule = scheduleList.getEntry(i);
            if (currentSchedule.getApplicantId().equalsIgnoreCase(applicantId)) {
                filteredSchedules.add(currentSchedule);
            }
        }
        return filteredSchedules;
    }

    // Filter schedules by job ID
    public LinkedList<InterviewSchedule> filterSchedulesByJob(String jobId) {
        LinkedList<InterviewSchedule> filteredSchedules = new LinkedList<>();
        for (int i = 1; i <= scheduleList.getNumberOfEntries(); i++) {
            InterviewSchedule currentSchedule = scheduleList.getEntry(i);
            if (currentSchedule.getJobId().equalsIgnoreCase(jobId)) {
                filteredSchedules.add(currentSchedule);
            }
        }
        return filteredSchedules;
    }

    // Display all interview schedules
    public void displayAll() {
        System.out.println("Interview Schedules:");
        for (int i = 1; i <= scheduleList.getNumberOfEntries(); i++) {
            System.out.println(scheduleList.getEntry(i));
        }
    }

    // Helper method to check if a schedule exists
    private boolean containsSchedule(String scheduleId) {
        for (int i = 1; i <= scheduleList.getNumberOfEntries(); i++) {
            if (scheduleList.getEntry(i).getScheduleId().equals(scheduleId)) {
                return true;
            }
        }
        return false;
    }
}