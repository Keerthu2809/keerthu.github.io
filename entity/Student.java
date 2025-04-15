package entity;

public class Student implements Comparable <Student> {
    private String studentID;
    private String studentName;
    private String course;
    private String email;
    private String phone;
    private String location;
    private String skills;
    private String studyLevel;
    private double cgpa;
    private String desiredJobType;

    // Constructor
    public Student(String studentID, String studentName, String course,
                   String email, String phone, String location, String skills, String studyLevel, double cgpa, String desiredJobType) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.course = course;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.skills = skills;
        this.studyLevel = studyLevel;
        this.cgpa = cgpa;
        this.desiredJobType = desiredJobType;
    }

    // Getters
    public String getStudentID() { return studentID; }
    public String getStudentName() { return studentName; }
    public String getCourse() { return course; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getSkills() { return skills; }
    public String getStudyLevel() { return studyLevel; }
    public double getCgpa() { return cgpa; }
    public String getDesiredJobType() { return desiredJobType; }

    // Setters
    public void setStudentID(String studentID) { this.studentID = studentID; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setCourse(String course) { this.course = course; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
    public void setSkills(String skills) { this.skills = skills; }
    public void setStudyLevel(String studyLevel) { this.studyLevel = studyLevel; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    public void setDesiredJobType(String desiredJobType) { this.desiredJobType = desiredJobType; }
    
    // toString method
    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", name='" + studentName + '\'' +
                ", course='" + course + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", skills='" + skills + '\'' +
                ", studyLevel='" + studyLevel + '\'' +
                ", cgpa=" + cgpa +
                ", jobType=" + desiredJobType +
                '}';
}

    
     // Implement compareTo method
    @Override
    public int compareTo(Student other) {
        return this.studentID.compareTo(other.studentID); // Compare based on studentID
    }
}