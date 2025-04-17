/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.LinkedList;

/**
 *
 * @author ROG G14
 */
public class Applicant {

    private String applicantId;
    private String name;
    private String location;
    private LinkedList<String> skills; // Using LinkedList for skills
    private String desiredJobType;

    public Applicant(String applicantId, String name) {
        this.applicantId = applicantId;
        this.name = name;
        this.location = location;
        this.skills = new LinkedList<>(); // Initialize skills as a LinkedList
        this.desiredJobType = desiredJobType;
    }

    // Getters and setters
    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LinkedList<String> getSkills() {
        return skills;
    }

    public void addSkill(String skill) {
        skills.add(skill); // Add a skill to the LinkedList
    }

    public String getDesiredJobType() {
        return desiredJobType;
    }

    public void setDesiredJobType(String desiredJobType) {
        this.desiredJobType = desiredJobType;
    }

    @Override
    public String toString() {
        StringBuilder skillList = new StringBuilder();
        for (int i = 1; i <= skills.getNumberOfEntries(); i++) {
            skillList.append(skills.getEntry(i));
            if (i < skills.getNumberOfEntries()) {
                skillList.append(", "); // Separate skills with a comma
            }
        }
        return "Applicant ID: " + applicantId +
               ", Name: " + name +
               ", Location: " + location +
               ", Skills: [" + skillList.toString() + "]" +
               ", Desired Job Type: " + desiredJobType;
    }
}