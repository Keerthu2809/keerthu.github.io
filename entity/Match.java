package entity;
// Author Lim Yin Ze

public class Match implements Comparable<Match> {

    String matchID;
    private Student student;
    private Job job;
    private double matchScore;
    private String matchResult;
    private String matchedSkills;
    private String matchedCourses;

    public Match(String matchID, Student student, Job job) {
        this.matchID = matchID;
        this.student = student;
        this.job = job;
    }

    // Getters and setters
    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public Student getStudent() {
        return student;
    }

    public Job getJob() {
        return job;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    public String getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(String matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public String getMatchedCourses() {
        return matchedCourses;
    }

    public void setMatchedCourses(String matchedCourses) {
        this.matchedCourses = matchedCourses;
    }

    @Override
    public int compareTo(Match other) {
        // Sort by match score in descending order
        return Double.compare(other.matchScore, this.matchScore);
    }
}
