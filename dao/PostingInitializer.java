package dao;
// author: CHONG PEI LEE
import adt.ListInterface;
import adt.SortedList;
import entity.Posting;
import entity.Job;
import entity.Company;

public class PostingInitializer {
    public ListInterface<Posting> initializePostings(ListInterface<Job> jobs, ListInterface<Company> companies) {
        ListInterface<Posting> postings = new SortedList<>();
        
        if (jobs == null || jobs.isEmpty() || companies == null || companies.isEmpty()) {
            return postings;
        }

        int postingCounter = 1;
        int jobIndex = 0;
        int companyIndex = 0;
        
        for (int i = 0; i < 55; i++) {
            String postingID = "P" + (1000 + postingCounter++);
            Job job = jobs.get(jobIndex);
            Company company = companies.get(companyIndex);
            
            jobIndex = (jobIndex + 1) % jobs.getNumberOfEntries();
            companyIndex = (companyIndex + 1) % companies.getNumberOfEntries();
            
            String postingDate = String.format("2025-%02d-%02d", (i % 12) + 1, (i % 28) + 1);
            String expirationDate = String.format("2025-%02d-%02d", ((i + 3) % 12) + 1, ((i + 7) % 28) + 1);
            
            // Create posting with default DRAFT status
            Posting posting = new Posting(postingID, job, company, postingDate, expirationDate);
            
            // Set different statuses and simulate activity
            if (i < 15) {
                // Published postings
                posting.publish();
                for (int v = 0; v < (i % 20) + 5; v++) posting.recordView();
                for (int a = 0; a < (i % 10) + 2; a++) posting.addApplication();
            } 
            else if (i < 30) {
                // Draft postings (no action needed)
            } 
            else if (i < 45) {
                // Closed postings
                posting.publish();
                posting.close();
                for (int v = 0; v < (i % 30) + 10; v++) posting.recordView();
                for (int a = 0; a < (i % 15) + 5; a++) posting.addApplication();
            }
            else {
                // Archived postings (treated as closed in current implementation)
                posting.publish();
                posting.close();
                for (int v = 0; v < (i % 40) + 15; v++) posting.recordView();
                for (int a = 0; a < (i % 20) + 8; a++) posting.addApplication();
            }
            
            // Add sample notes for some postings
            if (i % 5 == 0) {
                posting.addNote("Special note for posting " + postingID);
            }
            
            postings.add(posting);
        }

        return postings;
    }
}