package control;

import adt.ListInterface;
import adt.SortedList;
import entity.Student;
import java.util.Iterator;
import java.util.Scanner;

public class StudentManagementControl {

    private final SortedList<Student> studentList;

    public StudentManagementControl(ListInterface<Student> initialStudents) {
        this.studentList = initialStudents != null ? new SortedList<>(initialStudents) : new SortedList<>();
    }

    public void addStudent(Student student) {
        if (containsStudent(student.getStudentID())) {
            System.out.println("Student ID already exists!"); // Correct message
            return;
        }
        studentList.add(student);
        System.out.println("Student successfully added!");
    }

    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void updateStudent(String studentID, Scanner scanner) {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentID().equals(studentID)) {
                System.out.print("Enter new name: ");
                student.setStudentName(scanner.nextLine());
                System.out.print("Enter new course: ");
                student.setCourse(scanner.nextLine());
                System.out.print("Enter new email: ");
                student.setEmail(scanner.nextLine());
                System.out.print("Enter new phone: ");
                student.setPhone(scanner.nextLine());
                System.out.print("Enter new location: ");
                student.setLocation(scanner.nextLine());
                System.out.print("Enter new skills: ");
                student.setSkills(scanner.nextLine());
                System.out.print("Enter new study level: ");
                student.setStudyLevel(scanner.nextLine());
                System.out.print("Enter new CGPA: ");
                student.setCgpa(scanner.nextDouble());
                scanner.nextLine(); // Consume newline
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    public void removeStudent(String studentID) {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentID().equals(studentID)) {
                studentList.remove(student);
                System.out.println("Student removed successfully!");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    public boolean containsStudent(String studentID) {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getStudentID().equals(studentID)) {
                return true;
            }
        }
        return false;
    }

   public Student getStudent(String studentID) {
    // Check if the studentID is empty
    if (studentID == null || studentID.trim().isEmpty()) {
        System.out.println("Student ID is required.");
        return null; // Student ID is empty, return null
    }

    Iterator<Student> iterator = studentList.iterator();
    while (iterator.hasNext()) {
        Student student = iterator.next();
        if (student.getStudentID().equals(studentID)) {
            return student;
        }
    }
    return null; // Student not found
}

    public SortedList<Student> getAllStudents() {
        return studentList;
    }
}