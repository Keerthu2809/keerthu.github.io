/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.InterviewManagement;
import entity.InterviewSchedule;

import java.util.Scanner;

/**
 * User interface for interview management.
 * @author ROG G14
 */
public class InterviewManagementUI {
    public static void manageInterviews(Scanner scanner, InterviewManagement interviewManagement) {
        int choice;
        do {
            System.out.println("\n--- Interview Management ---");
            System.out.println("1. Display All Interview Schedules");
            System.out.println("2. Create Interview Schedule");
            System.out.println("3. Update Interview Schedule");
            System.out.println("4. Delete Interview Schedule");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    interviewManagement.displayAll();
                    break;
                case 2:
                    System.out.print("Enter Schedule ID: ");
                    String scheduleId = scanner.nextLine();
                    System.out.print("Enter Applicant ID: ");
                    String applicantId = scanner.nextLine();
                    System.out.print("Enter Job ID: ");
                    String jobId = scanner.nextLine();
                    System.out.print("Enter Interview Date (YYYY-MM-DD): ");
                    String interviewDate = scanner.nextLine();
                    System.out.print("Enter Interview Time (HH:MM): ");
                    String interviewTime = scanner.nextLine();

                    InterviewSchedule newSchedule = new InterviewSchedule(scheduleId, applicantId, jobId, interviewDate, interviewTime);
                    if (interviewManagement.createSchedule(newSchedule)) {
                        System.out.println("Interview Schedule created successfully.");
                    } else {
                        System.out.println("Schedule with the same ID already exists.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Schedule ID to Update: ");
                    String updateScheduleId = scanner.nextLine();
                    System.out.print("Enter New Applicant ID: ");
                    String updateApplicantId = scanner.nextLine();
                    System.out.print("Enter New Job ID: ");
                    String updateJobId = scanner.nextLine();
                    System.out.print("Enter New Interview Date (YYYY-MM-DD): ");
                    String updateInterviewDate = scanner.nextLine();
                    System.out.print("Enter New Interview Time (HH:MM): ");
                    String updateInterviewTime = scanner.nextLine();

                    InterviewSchedule updatedSchedule = new InterviewSchedule(updateScheduleId, updateApplicantId, updateJobId, updateInterviewDate, updateInterviewTime);
                    if (interviewManagement.updateSchedule(updateScheduleId, updatedSchedule)) {
                        System.out.println("Interview Schedule updated successfully.");
                    } else {
                        System.out.println("Schedule not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Schedule ID to Delete: ");
                    String deleteScheduleId = scanner.nextLine();
                    if (interviewManagement.deleteSchedule(deleteScheduleId)) {
                        System.out.println("Interview Schedule deleted successfully.");
                    } else {
                        System.out.println("Schedule not found.");
                    }
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}