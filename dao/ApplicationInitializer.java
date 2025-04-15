package dao;

import adt.ListInterface;
import adt.SortedList;
import entity.Application;
import entity.Student;
import entity.Job;
import entity.Company;

public class ApplicationInitializer {

    private int applicationCounter = 1001; // Starts from A1001
    private long fakeTimeSeed = 1; // Seed for simple "random" simulation

    public ListInterface<Application> initializeApplications(
            ListInterface<Application> applicationList,
            ListInterface<Student> studentList,
            ListInterface<Job> jobList,
            ListInterface<Company> companyList) {

        if (applicationList == null) {
            applicationList = new SortedList<>();
        }

        for (int i = 0; i < studentList.size() && applicationCounter <= 1100; i++) {
            Student student = studentList.get(i);
            Job job = findMatchingJobForStudent(student, jobList);
            if (job == null) {
                continue; // Skip if no matching job found
            }

            // Replace the random company assignment with a condition-based one
            Company company = findMatchingCompanyForJob(job, companyList);
            if (company == null) {
                continue; // Skip if no matching company found
            }

            String applicationID = "A" + applicationCounter++;

            String postingDate = job.getPostedDate(); // e.g., "2025-08-01"
            String deadline = job.getDeadline();       // e.g., "2025-10-10"

            String applicationDate = generateSimulatedDateBetween(postingDate, deadline);

            Application application = new Application(applicationID, student, job, company, applicationDate);
            applicationList.add(application);
        }

        return applicationList;
    }

    // Simple simulated date between two dates using increasing seed
    private String generateSimulatedDateBetween(String start, String end) {
        int startDay = extractDay(start);
        int endDay = extractDay(end);

        int middleDay = startDay + (int) ((fakeTimeSeed * 7) % Math.max(1, (endDay - startDay + 1)));
        fakeTimeSeed++;

        return start.substring(0, 8) + String.format("%02d", middleDay);
    }

    private int extractDay(String date) {
        return Integer.parseInt(date.substring(8, 10));
    }

    private Job findMatchingJobForStudent(Student student, ListInterface<Job> jobList) {
        for (int j = 0; j < jobList.size(); j++) {
            Job job = jobList.get(j);
            if (student.getDesiredJobType().equalsIgnoreCase(job.getTitle())) {
                return job;
            }
        }
        return null; // No match found
    }

    // New method to find the company based on a condition such as job category
    private Company findMatchingCompanyForJob(Job job, ListInterface<Company> companyList) {
        // Assume companies have a preferred job category and we try to match it to the job's category
        for (int i = 0; i < companyList.size(); i++) {
            Company company = companyList.get(i);
            // Assuming there's a way to check if a company is linked to a job, either via jobID or index
            if (job.getCompanyID().equals(company.getCompanyID())) { // Assuming Job has a companyID field
                return company;
            }
        }
        return null; // No matching company found
    }
}
