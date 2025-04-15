package control;

import adt.ListInterface;
import adt.SortedList;
import entity.Application;
import entity.Student;
import entity.Job;
import java.util.Iterator;
import java.util.Scanner;

public class ApplicationControl {

    private final SortedList<Application> applicationList;

    public ApplicationControl(ListInterface<Application> initialApplications) {
        this.applicationList = initialApplications != null ? new SortedList<>(initialApplications) : new SortedList<>();
    }

    public void addApplication(Application application) {
        if (containsApplication(application.getApplicationID())) {
            System.out.println("Application already exists!");
            return;
        }
        
        
        applicationList.add(application);
        System.out.println("Application successfully added!");
    }

    public void viewApplications(ListInterface<Application> applicationList, ListInterface<Student> studentList, ListInterface<Job> jobList) {
        if (applicationList.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        // Iterate over the application list and filter based on desired job type
        Iterator<Application> iterator = applicationList.iterator();
        while (iterator.hasNext()) {
            Application application = iterator.next();

            // Get the student and job associated with the application
            Student student = application.getStudent();
            Job job = application.getJob();

          

            // Check if the student's desired job type matches the job title
            if (student.getDesiredJobType().equalsIgnoreCase(job.getTitle())) {
                // Check if the applicationID is between A1001 and A1100
                String applicationID = application.getApplicationID();
                if (applicationID.compareTo("A1001") >= 0 && applicationID.compareTo("A1100") <= 0) {
                    System.out.println(application);  // Print the application
                }
            }
        }
    }

    public void updateApplication(String applicationID, Scanner scanner) {
        Application application = getApplication(applicationID);
        if (application != null) {
            Job job = application.getJob();
            if (job != null) {
                System.out.print("Enter new salary range: ");
                job.setSalaryRange(scanner.nextLine());
                System.out.print("Enter new job status: ");
                job.setStatus(scanner.nextLine());
                System.out.println("Application updated successfully!");
            } else {
                System.out.println("No job found in this application to update.");
            }
        } else {
            System.out.println("Application ID not found.");
        }
    }

    public void removeApplication(String applicationID) {
        Iterator<Application> iterator = applicationList.iterator();
        while (iterator.hasNext()) {
            Application application = iterator.next();
            if (application.getApplicationID().equals(applicationID)) {
                applicationList.remove(application);
                System.out.println("Application removed successfully!");
                return;
            }
        }
        System.out.println("Application ID not found.");
    }

    public boolean containsApplication(String applicationID) {
        Iterator<Application> iterator = applicationList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getApplicationID().equals(applicationID)) {
                return true;
            }
        }
        return false;
    }

    public Application getApplication(String applicationID) {
        Iterator<Application> iterator = applicationList.iterator();
        while (iterator.hasNext()) {
            Application application = iterator.next();
            if (application.getApplicationID().equals(applicationID)) {
                return application;
            }
        }
        return null; // Application not found
    }

    public SortedList<Application> getAllApplications() {
        return applicationList;
    }

    // Check if student already applied for a specific job
    public boolean hasApplied(String studentID, String jobID) {
        Iterator<Application> iterator = applicationList.iterator();
        while (iterator.hasNext()) {
            Application application = iterator.next();
            if (application.getStudent().getStudentID().equals(studentID)
                    && application.getJob().getJobID().equals(jobID)) {
                return true;
            }
        }
        return false;
    }

    // Filter applications by location, job type, or student skills
    public SortedList<Application> filterApplications(String filterChoice, String filterValue, StudentManagementControl studentControl) {
        SortedList<Application> filteredApplications = new SortedList<>();

        Iterator<Application> iterator = applicationList.iterator();
        while (iterator.hasNext()) {
            Application application = iterator.next();
            Student student = application.getStudent(); // or use studentControl if needed

            boolean matches = false;
            switch (filterChoice) {
                case "1": // Location
                    matches = application.getJob().getLocation() != null
                            && application.getJob().getLocation().equalsIgnoreCase(filterValue);
                    break;
                case "2": // Job Category
                    matches = application.getJob().getCategory() != null
                            && application.getJob().getCategory().equalsIgnoreCase(filterValue);
                    break;
                case "3": // Student Skills
                    matches = student.getSkills() != null
                            && student.getSkills().toLowerCase().contains(filterValue.toLowerCase());
                    break;
                default:
                    System.out.println("Invalid filter choice.");
                    return filteredApplications;
            }

            if (matches) {
                filteredApplications.add(application);
            }
        }

        return filteredApplications;
    }
}
