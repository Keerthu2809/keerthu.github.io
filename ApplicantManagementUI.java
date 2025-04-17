/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.ApplicantManagement;
import entity.Applicant;
import adt.LinkedList;

import java.util.Scanner;
/**
 *
 * @author ROG G14
 */
public class ApplicantManagementUI {
    public static void manageApplicants(Scanner scanner, ApplicantManagement applicantManagement) {
        int choice;
        do {
            System.out.println("\n--- Applicant Management ---");
            System.out.println("1. Display All Applicants");
            System.out.println("2. Create Applicant");
            System.out.println("3. Update Applicant");
            System.out.println("4. Delete Applicant");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    applicantManagement.displayAll();
                    break;
                case 2:
                    System.out.print("Enter Applicant ID: ");
                    String applicantId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter number of skills: ");
                    int numSkills = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Applicant newApplicant = new Applicant(applicantId, name);
                    for (int i = 0; i < numSkills; i++) {
                        System.out.print("Enter Skill " + (i + 1) + ": ");
                        String skill = scanner.nextLine();
                        newApplicant.addSkill(skill);
                    }

                    boolean created = applicantManagement.createApplicant(newApplicant);
                    if (created) {
                        System.out.println("Applicant created successfully.");
                    } else {
                        System.out.println("Applicant with the same ID already exists.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Applicant ID to Update: ");
                    String updateApplicantId = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String updatedName = scanner.nextLine();

                    System.out.print("Enter number of skills: ");
                    int updateNumSkills = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Applicant updatedApplicant = new Applicant(updateApplicantId, updatedName);
                    for (int i = 0; i < updateNumSkills; i++) {
                        System.out.print("Enter Skill " + (i + 1) + ": ");
                        String updatedSkill = scanner.nextLine();
                        updatedApplicant.addSkill(updatedSkill);
                    }

                    boolean updated = applicantManagement.updateApplicant(updateApplicantId, updatedApplicant);
                    if (updated) {
                        System.out.println("Applicant updated successfully.");
                    } else {
                        System.out.println("Applicant not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Applicant ID to Delete: ");
                    String deleteApplicantId = scanner.nextLine();
                    boolean deleted = applicantManagement.removeApplicant(deleteApplicantId);
                    if (deleted) {
                        System.out.println("Applicant deleted successfully.");
                    } else {
                        System.out.println("Applicant not found.");
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