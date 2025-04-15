package boundary;

import adt.SortedList;
import control.StudentManagementControl;
import control.ApplicationControl;
import control.JobControl;
import entity.Student;
import entity.Job;
import java.util.Scanner;
import java.util.Iterator;

public class StudentManagementUI {

    private final StudentManagementControl studentControl;
    private final ApplicationUI applicationUI;
    private final Scanner scanner;
    private final JobControl jobControl;  // Declare jobControl

    public StudentManagementUI(StudentManagementControl studentControl,
            ApplicationControl applicationControl, JobControl jobControl,
            Scanner scanner) {
        this.studentControl = studentControl;
        this.jobControl = jobControl; // Initialize jobControl
        this.applicationUI = new ApplicationUI(applicationControl, studentControl, jobControl); // Pass jobControl here
        this.scanner = scanner;
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n===== Manage Students Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Apply For Jobs");
            System.out.println("6. Matched Applicant with Jobs");
            System.out.println("7. Schedule Interview");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    applyForJob();
                    break;
                case 6:
                    // Matched applicants functionality
                    break;
                case 7:
                    // Schedule interview functionality
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();

        if (studentControl.containsStudent(studentID)) {
            System.out.println("Student already exists. Successfully added!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Skills: ");
        String skills = scanner.nextLine();
        System.out.print("Enter Study Level: ");
        String studyLevel = scanner.nextLine();
        System.out.print("Enter CGPA: ");
        double cgpa = scanner.nextDouble();
        System.out.print("Enter JobType: ");
        String desiredJobType = scanner.nextLine();
        scanner.nextLine(); // Consume newline

        Student newStudent = new Student(studentID, name, course, email, phone,
                location, skills, studyLevel, cgpa, desiredJobType);
        studentControl.addStudent(newStudent);
    }

    public void displayAllStudents() {
        System.out.println(centerText("=== ALL AVAILABLE STUDENTS ==="));
        SortedList<Student> students = studentControl.getAllStudents();
        if (students.isEmpty()) {
            System.out.println(centerText("No students available."));
        } else {
            printStudentTable(students);
        }
    }

    private String centerText(String text) {
        int terminalWidth = 80;
        int padding = (terminalWidth - text.length()) / 2;
        if (padding > 0) {
            return String.format("%" + padding + "s%s", "", text);
        }
        return text;
    }

    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String studentID = scanner.nextLine();
        studentControl.updateStudent(studentID, scanner);
    }

    private void removeStudent() {
        System.out.print("Enter Student ID to remove: ");
        String studentID = scanner.nextLine();
        studentControl.removeStudent(studentID);
    }

    private void applyForJob() {
        System.out.println("\n===== Applying for Jobs =====");

        // Fetch available jobs using jobControl
        SortedList<Job> jobs = (SortedList<Job>) jobControl.getAllJobs();

        if (jobs.isEmpty()) {
            System.out.println("No jobs available.");
        } else {
            System.out.println("Available Jobs: ");

            // Using Iterator to loop through the list of jobs
            Iterator<Job> iterator = jobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                System.out.println(job.getJobID() + ": " + job.getTitle() + " - " + job.getLocation());
            }
        }

        // Proceed with application UI for students
        applicationUI.displayMenu();
    }

    private void printStudentTable(SortedList<Student> students) {
        System.out.println("+------------+----------------------+----------+------------------------------+-------------+-----------------+----------------------+------------+------+-----------------------+");
        System.out.printf("| %-10s | %-20s | %-8s | %-28s | %-11s | %-15s | %-20s | %-10s | %-4s | %-21s |%n",
                "Student ID", "Name", "Course", "Email", "Phone", "Location", "Skills", "Study Level", "CGPA", "Job Type");
        System.out.println("+------------+----------------------+----------+------------------------------+-------------+-----------------+----------------------+------------+------+-----------------------+");

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.printf("| %-10s | %-20s | %-8s | %-38s | %-11s | %-15s | %-20s | %-10s | %-4.2f | %-21s |%n",
                    student.getStudentID(),
                    truncate(student.getStudentName(), 20),
                    truncate(student.getCourse(), 8),
                    truncate(student.getEmail(), 38),
                    student.getPhone(),
                    truncate(student.getLocation(), 15),
                    truncate(student.getSkills(), 20),
                    truncate(student.getStudyLevel(), 10),
                    student.getCgpa(),
                    truncate(student.getDesiredJobType(), 21));
            System.out.println("+------------+----------------------+----------+------------------------------+-------------+-----------------+----------------------+------------+------+-----------------------+");
        }
    }

    private String truncate(String str, int length) {
        return (str != null && str.length() > length) ? str.substring(0, length - 3) + "..." : str;
    }
}
