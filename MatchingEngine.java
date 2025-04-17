/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedList;
import entity.Applicant;
import entity.JobPosting;
import entity.MatchResult;
/**
 *
 * @author ROG G14
 */
public class MatchingEngine {
    private LinkedList<Applicant> applicants;
    private LinkedList<JobPosting> jobPostings;

    public MatchingEngine(LinkedList<Applicant> applicants, LinkedList<JobPosting> jobPostings) {
        this.applicants = applicants;
        this.jobPostings = jobPostings;
    }

    /**
     * Matches applicants to job postings based on skills.
     *
     * @return A list of match results.
     */
    public LinkedList<MatchResult> findMatches() {
        LinkedList<MatchResult> matches = new LinkedList<>();

        for (int i = 1; i <= applicants.getNumberOfEntries(); i++) {
            Applicant applicant = applicants.getEntry(i);

            for (int j = 1; j <= jobPostings.getNumberOfEntries(); j++) {
                JobPosting job = jobPostings.getEntry(j);

                // Calculate match score based on skills
                double matchScore = calculateMatchScore(applicant, job);
                if (matchScore > 0) {
                    matches.add(new MatchResult(applicant.getApplicantId(), job.getJobId(), matchScore));
                }
            }
        }

        return matches;
    }

    /**
     * Calculates the match score between an applicant and a job posting.
     *
     * @param applicant The applicant to be matched.
     * @param job       The job posting to be matched.
     * @return The match score (0 if no match).
     */
    private double calculateMatchScore(Applicant applicant, JobPosting job) {
        LinkedList<String> applicantSkills = applicant.getSkills();
        String jobTitle = job.getJobTitle().toLowerCase();

        int matchingSkills = 0;

        for (int i = 1; i <= applicantSkills.getNumberOfEntries(); i++) {
            String skill = applicantSkills.getEntry(i).toLowerCase();
            if (jobTitle.contains(skill)) {
                matchingSkills++;
            }
        }

        // Return a score based on matching skills
        return matchingSkills > 0 ? (double) matchingSkills / applicantSkills.getNumberOfEntries() * 100 : 0;
    }

    /**
     * Displays all matches.
     *
     * @param matches The list of match results to display.
     */
    public void displayMatches(LinkedList<MatchResult> matches) {
        System.out.println("Match Results:");
        for (int i = 1; i <= matches.getNumberOfEntries(); i++) {
            System.out.println(matches.getEntry(i));
        }
    }
}
