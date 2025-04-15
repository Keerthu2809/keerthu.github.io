package control;
// author: CHONG PEI LEE
import adt.ListInterface;
import adt.SortedList;
import entity.Posting;
import entity.Job;
import entity.Company;

public class PostingControl {
    private final ListInterface<Posting> postingList = new SortedList<>(); //ADT object declared

    public PostingControl() {
    }

    public PostingControl(ListInterface<Posting> initialPostings) {
        this();
        if (initialPostings != null) {
            for (Posting posting : initialPostings) {
                this.postingList.add(posting);  // add() called from ListInterface class
            }
        }
    }

    // Selection Sort implementation for Postings by ID
    public void sortPostingsById() {
        for (int i = 0; i < postingList.size() - 1; i++) { // size() called from ListInterface class
            int minIndex = i;
            for (int j = i + 1; j < postingList.size(); j++) { // size() called from ListInterface class
                Posting current = getPostingAtIndex(j);
                Posting min = getPostingAtIndex(minIndex);
                if (current.getPostingID().compareTo(min.getPostingID()) < 0) {
                    minIndex = j;
                }
            }
            swapPostings(i, minIndex);
        }
    }

    // Insertion Sort implementation for Postings by Expiration Date
    public void sortPostingsByExpirationDate() {
        for (int i = 1; i < postingList.size(); i++) { // size() called from ListInterface class
            Posting key = getPostingAtIndex(i);
            int j = i - 1;
            
            while (j >= 0 && getPostingAtIndex(j).getExpirationDate().compareTo(key.getExpirationDate()) > 0) {
                setPostingAtIndex(j + 1, getPostingAtIndex(j));
                j--;
            }
            setPostingAtIndex(j + 1, key);
        }
    }

    // Binary Search implementation for Postings by ID
    public Posting binarySearchPostingById(String postingId) {
        sortPostingsById();
        int low = 0;
        int high = postingList.size() - 1; // size() called from ListInterface class
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            Posting midPosting = getPostingAtIndex(mid);
            int comparison = midPosting.getPostingID().compareTo(postingId);
            
            if (comparison == 0) {
                return midPosting;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Helper methods
    private Posting getPostingAtIndex(int index) {
        int i = 0;
        for (Posting posting : postingList) {
            if (i == index) return posting;
            i++;
        }
        return null;
    }

    private void setPostingAtIndex(int index, Posting posting) {
        ListInterface<Posting> temp = new SortedList<>(); //ADT object declared
        for (int i = 0; i < postingList.size(); i++) { // size() called from ListInterface class
            if (i == index) {
                temp.add(posting);  // add() called from ListInterface class
            } else {
                Posting current = getPostingAtIndex(i);
                if (current != null) temp.add(current);  // add() called from ListInterface class
            }
        }
        postingList.clear(); // clear() called from ListInterface class
        for (Posting p : temp) {
            postingList.add(p);  // add() called from ListInterface class
        }
    }

    private void swapPostings(int i, int j) {
        Posting temp = getPostingAtIndex(i);
        setPostingAtIndex(i, getPostingAtIndex(j));
        setPostingAtIndex(j, temp);
    }

    public boolean createPosting(String postingID, Job job, Company company, 
                               String postingDate, String expirationDate) {
        if (postingID == null || postingID.isEmpty() || // isEmpty() called from ListInterface class
            job == null || company == null || 
            postingDate == null || expirationDate == null) {
            return false;
        }

        if (getPostingById(postingID) != null) {
            return false;
        }

        Posting newPosting = new Posting(postingID, job, company, postingDate, expirationDate);
        postingList.add(newPosting);  // add() called from ListInterface class
        
        return getPostingById(postingID) != null;
    }

    public Posting getPostingById(String postingID) {
        if (postingID == null) return null;
        
        for (Posting posting : postingList) {
            if (postingID.equals(posting.getPostingID())) {
                return posting;
            }
        }
        return null;
    }

    public boolean publishPosting(String postingID) {
        Posting posting = getPostingById(postingID);
        if (posting != null && "DRAFT".equals(posting.getStatus())) {
            posting.publish();
            return true;
        }
        return false;
    }

    public boolean closePosting(String postingID) {
        Posting posting = getPostingById(postingID);
        if (posting != null && "PUBLISHED".equals(posting.getStatus())) {
            posting.close();
            return true;
        }
        return false;
    }

    public boolean recordView(String postingID) {
        Posting posting = getPostingById(postingID);
        if (posting != null) {
            posting.recordView();
            return true;
        }
        return false;
    }

    public boolean addApplication(String postingID) {
        Posting posting = getPostingById(postingID);
        if (posting != null) {
            posting.addApplication();
            return true;
        }
        return false;
    }

    public boolean addNote(String postingID, String note) {
        Posting posting = getPostingById(postingID);
        if (posting != null && note != null && !note.isEmpty()) { // isEmpty() called from ListInterface class
            posting.addNote(note);
            return true;
        }
        return false;
    }

    public boolean updateExpirationDate(String postingID, String newExpirationDate) {
        Posting posting = getPostingById(postingID);
        if (posting != null && newExpirationDate != null && !newExpirationDate.isEmpty()) { // isEmpty() called from ListInterface class
            posting.setExpirationDate(newExpirationDate);
            return true;
        }
        return false;
    }

    public ListInterface<Posting> getAllPostings() {
        ListInterface<Posting> copy = new SortedList<>(); //ADT object declared
        for (Posting posting : postingList) {
            copy.add(posting);  // add() called from ListInterface class
        }
        return copy;
    }

    public ListInterface<Posting> getActivePostings() {
        ListInterface<Posting> active = new SortedList<>(); //ADT object declared
        for (Posting posting : postingList) {
            if (posting.isActive()) {
                active.add(posting);  // add() called from ListInterface class
            }
        }
        return active;
    }

    public ListInterface<Posting> getPostingsByCompany(Company company) {
        ListInterface<Posting> companyPostings = new SortedList<>(); //ADT object declared
        if (company == null) return companyPostings;
        
        for (Posting posting : postingList) {
            if (company.equals(posting.getCompany())) {
                companyPostings.add(posting);  // add() called from ListInterface class
            }
        }
        return companyPostings;
    }

    public ListInterface<Posting> getPostingsByJob(Job job) {
        ListInterface<Posting> jobPostings = new SortedList<>(); //ADT object declared
        if (job == null) return jobPostings;
        
        for (Posting posting : postingList) {
            if (job.equals(posting.getJob())) {
                jobPostings.add(posting);  // add() called from ListInterface class
            }
        }
        return jobPostings;
    }

    public int getTotalPostingsCount() {
        return postingList.getNumberOfEntries(); // getNumberOfEntries() called from ListInterface class
    }

    public int getActivePostingsCount() {
        int count = 0;
        for (Posting posting : postingList) {
            if (posting.isActive()) {
                count++;
            }
        }
        return count;
    }
    
    public boolean updatePosting(String postingID, Job newJob, Company newCompany, 
                              String newPostingDate, String newExpirationDate) {
        Posting posting = getPostingById(postingID);
        if (posting == null) return false;
        
        if (newJob != null) posting.setJob(newJob);
        if (newCompany != null) posting.setCompany(newCompany);
        if (newPostingDate != null && !newPostingDate.isEmpty()) {  // isEmpty() called from ListInterface class
            posting.setPostingDate(newPostingDate);
        }
        if (newExpirationDate != null && !newExpirationDate.isEmpty()) { // isEmpty() called from ListInterface class
            posting.setExpirationDate(newExpirationDate);
        }
        
        return true;
    }
}