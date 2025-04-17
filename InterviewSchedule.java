/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ROG G14
 */
public class InterviewSchedule {
    private String scheduleId;
    private String applicantId;
    private String jobId;
    private String interviewDate; // Format: YYYY-MM-DD
    private String interviewTime; // Format: HH:MM

    public InterviewSchedule(String scheduleId, String applicantId, String jobId, String interviewDate, String interviewTime) {
        this.scheduleId = scheduleId;
        this.applicantId = applicantId;
        this.jobId = jobId;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getJobId() {
    return jobId;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    @Override
    public String toString() {
        return String.format("Schedule ID: %s, Applicant ID: %s, Job ID: %s, Date: %s, Time: %s",
                scheduleId, applicantId, jobId, interviewDate, interviewTime);
    }
}