/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ROG G14
 */
public class MatchResult {
    private String applicantId;
    private String jobId;
    private double matchScore;

    public MatchResult(String applicantId, String jobId, double matchScore) {
        this.applicantId = applicantId;
        this.jobId = jobId;
        this.matchScore = matchScore;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getJobId() {
        return jobId;
    }

    public double getMatchScore() {
        return matchScore;
    }

    @Override
    public String toString() {
        return String.format("Applicant ID: %s, Job ID: %s, Match Score: %.2f", applicantId, jobId, matchScore);
    }
}