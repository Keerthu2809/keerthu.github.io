/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author rscma
 */
public class Recruitment implements Comparable<Recruitment>{

    private String successfulCandidate;
    private double testScore;
    private String joinedDate; // Date as String
    private String studentDetails; // Assuming student details are stored as a String

    // Constructor
    public Recruitment(String successCandidate, String joinedDate, double testScore, String studentDetails, String it_department, String string) {
        this.successfulCandidate = successCandidate;
        this.testScore = testScore;
        this.joinedDate = joinedDate; // Now a String
        this.studentDetails = studentDetails;
    }

    // Getters
    public String getSuccessfulCandidate() {
        return successfulCandidate;
    }

    public double getTestScore() {
        return testScore;
    }

    public String getJoinedDate() {
        return joinedDate; // Now returns a String
    }

    public String getStudentDetails() {
        return studentDetails;
    }

    public String getRecruitmentDetails() {
        String details = "Successful Candidate: " + successfulCandidate + "\n"
                + "Test Score: " + testScore + "\n"
                + "Joined Date: " + joinedDate + "\n"
                + "Student details: " + studentDetails +"\n";
        return details;
    }

    // Implementing the compareTo method for natural ordering
    @Override
    public int compareTo(Recruitment other) {
        return Double.compare(this.testScore, other.testScore); // Compare based on test scores
    }
}


