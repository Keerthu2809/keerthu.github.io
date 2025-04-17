/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;


import adt.LinkedList;
import entity.Applicant;

/**
 * ApplicantManagement class to manage applicants and their data.
 * //Author: Keerthu_2809
 */
public class ApplicantManagement {
    private LinkedList<Applicant> applicantList = new LinkedList<>();

    // Getter method to access the applicant list
    public LinkedList<Applicant> getApplicants() {
        return applicantList;
    }

    // Create a new applicant
    public boolean createApplicant(Applicant applicant) {
        if (!containsApplicant(applicant.getApplicantId())) {
            applicantList.add(applicant);
            return true;
        }
        return false; // Applicant with the same ID already exists
    }

    // Update an existing applicant
    public boolean updateApplicant(String applicantId, Applicant updatedApplicant) {
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant currentApplicant = applicantList.getEntry(i);
            if (currentApplicant.getApplicantId().equals(applicantId)) {
                applicantList.replace(i, updatedApplicant); // Replace the applicant at the specified index
                return true;
            }
        }
        return false; // Applicant not found
    }

    // Remove an applicant
    public boolean removeApplicant(String applicantId) {
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant currentApplicant = applicantList.getEntry(i);
            if (currentApplicant.getApplicantId().equals(applicantId)) {
                applicantList.remove(i); // Remove the applicant at the specified index
                return true;
            }
        }
        return false; // Applicant not found
    }

    // Filter applicants based on skills
    public LinkedList<Applicant> filterApplicantsBySkill(String skill) {
        LinkedList<Applicant> filteredApplicants = new LinkedList<>();

        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant applicant = applicantList.getEntry(i);

            // Check if the skill matches
            boolean skillMatches = false;
            if (skill == null) {
                skillMatches = true; // If no skill is specified, consider it a match
            } else {
                LinkedList<String> skills = applicant.getSkills(); // Assuming getSkills() returns a LinkedList<String>
                for (int j = 1; j <= skills.getNumberOfEntries(); j++) {
                    String s = skills.getEntry(j);
                    if (s.trim().toLowerCase().contains(skill.toLowerCase())) {
                        skillMatches = true;
                        break; // Exit the loop as soon as a match is found
                    }
                }
            }

            // Add the applicant to the filtered list if the skill matches
            if (skillMatches) {
                filteredApplicants.add(applicant);
            }
        }

        return filteredApplicants;
    }

    // Display all applicants
    public void displayAll() {
        System.out.println("Applicant List:");
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            System.out.println(applicantList.getEntry(i));
        }
    }

    // Helper method to check if an applicant exists by ID
    private boolean containsApplicant(String applicantId) {
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            if (applicantList.getEntry(i).getApplicantId().equals(applicantId)) {
                return true;
            }
        }
        return false;
    }
}