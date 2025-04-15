package control;
// author: CHONG PEI LEE
import adt.ListInterface;
import adt.SortedList;
import entity.Posting;
import entity.Job;
import entity.Company;

public class JobControl {
    private final ListInterface<Job> jobList = new SortedList<>(); //ADT object declared
    private final ListInterface<Company> companyList = new SortedList<>(); //ADT object declared
    private final PostingControl postingControl = new PostingControl();

    public JobControl() {
    }

    public JobControl(ListInterface<Job> initialJobs, ListInterface<Company> initialCompanies) {
        this();
        if (initialJobs != null) {
            for (Job job : initialJobs) {
                jobList.add(job); // add() called from ListInterface class
            }
        }
        if (initialCompanies != null) {
            for (Company company : initialCompanies) {
                companyList.add(company); // add() called from ListInterface class
            }
        }
    }

    // Selection Sort implementation for Jobs by Title
    public void sortJobsByTitle() {
        for (int i = 0; i < jobList.size() - 1; i++) {  // size() called from ListInterface class
            int minIndex = i;
            for (int j = i + 1; j < jobList.size(); j++) { // size() called from ListInterface class
                Job currentJob = getJobAtIndex(j);
                Job minJob = getJobAtIndex(minIndex);
                if (currentJob.getTitle().compareToIgnoreCase(minJob.getTitle()) < 0) {
                    minIndex = j;
                }
            }
            swapJobs(i, minIndex);
        }
    }

    // Insertion Sort implementation for Jobs by Salary
    public void sortJobsBySalary() {
        for (int i = 1; i < jobList.size(); i++) { // size() called from ListInterface class
            Job key = getJobAtIndex(i);
            int j = i - 1;
            
            while (j >= 0 && parseSalary(getJobAtIndex(j).getSalaryRange()) > parseSalary(key.getSalaryRange())) {
                setJobAtIndex(j + 1, getJobAtIndex(j));
                j--;
            }
            setJobAtIndex(j + 1, key);
        }
    }

    // QuickSort implementation for Jobs by Date Posted
    public void sortJobsByDatePosted() {
        Job[] jobsArray = listToArray(jobList);
        quickSort(jobsArray, 0, jobsArray.length - 1, "date");
        arrayToList(jobsArray, jobList);
    }

    private void quickSort(Job[] arr, int low, int high, String sortBy) {
        if (low < high) {
            int pi = partition(arr, low, high, sortBy);
            quickSort(arr, low, pi - 1, sortBy);
            quickSort(arr, pi + 1, high, sortBy);
        }
    }

    private int partition(Job[] arr, int low, int high, String sortBy) {
        Job pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (compareJobs(arr[j], pivot, sortBy) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(Job[] arr, int i, int j) {
        Job temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Binary search implementation
    public Job binarySearchJobByTitle(String title) {
        ListInterface<Job> sorted = new SortedList<>(); //ADT object declared
        int low = 0;
        int high = sorted.size() - 1; // size() called from ListInterface class
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            Job midJob = sorted.get(mid); // get() called from ListInterface class
            int comparison = midJob.getTitle().compareToIgnoreCase(title);
            
            if (comparison == 0) {
                return midJob;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Binary Search implementation for Jobs by ID
    public Job binarySearchJobById(String jobId) {
        sortJobsById();
        int low = 0;
        int high = jobList.size() - 1; // size() called from ListInterface class
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            Job midJob = getJobAtIndex(mid);
            int comparison = midJob.getJobID().compareTo(jobId);
            
            if (comparison == 0) {
                return midJob;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Helper methods
    private Job getJobAtIndex(int index) {
        int i = 0;
        for (Job job : jobList) {
            if (i == index) return job;
            i++;
        }
        return null;
    }

    private void setJobAtIndex(int index, Job job) {
        ListInterface<Job> temp = new SortedList<>(); //ADT object declared
        for (int i = 0; i < jobList.size(); i++) { // size() called from ListInterface class
            if (i == index) {
                temp.add(job); // add() called from ListInterface class
            } else {
                Job current = getJobAtIndex(i);
                if (current != null) temp.add(current); // add() called from ListInterface class
            }
        }
        jobList.clear();  // clear() called from ListInterface class
        for (Job j : temp) {
            jobList.add(j); // add() called from ListInterface class
        }
    }

    private void swapJobs(int i, int j) {
        Job temp = getJobAtIndex(i);
        setJobAtIndex(i, getJobAtIndex(j));
        setJobAtIndex(j, temp);
    }

    private Job[] listToArray(ListInterface<Job> list) {
        Job[] array = new Job[list.size()]; // size() called from ListInterface class
        int index = 0;
        for (Job job : list) {
            array[index++] = job;
        }
        return array;
    }

    private void arrayToList(Job[] array, ListInterface<Job> list) {
        list.clear(); // clear() called from ListInterface class
        for (Job job : array) {
            list.add(job); // add() called from ListInterface class
        }
    }

    private double parseSalary(String salaryRange) {
        try {
            String salary = salaryRange.replaceAll("[^0-9.-]", "");
            if (salary.contains("-")) { // contains() called from ListInterface class
                return Double.parseDouble(salary.split("-")[0]);
            }
            return Double.parseDouble(salary);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private int compareJobs(Job a, Job b, String sortBy) {
        switch(sortBy) {
            case "title":
                return a.getTitle().compareToIgnoreCase(b.getTitle());
            case "salary":
                return Double.compare(parseSalary(a.getSalaryRange()), parseSalary(b.getSalaryRange()));
            case "date":
                return a.getPostedDate().compareTo(b.getPostedDate());
            case "id":
                return a.getJobID().compareTo(b.getJobID());
            default:
                return 0;
        }
    }

    public boolean createJobPosting(String postingID, String jobID, String companyID, 
                                  String postingDate, String expirationDate) {
        if (postingID == null || jobID == null || companyID == null || 
            postingDate == null || expirationDate == null) {
            return false;
        }

        Job job = getJobById(jobID);
        Company company = getCompanyById(companyID);
        if (job == null || company == null) {
            return false;
        }
        return postingControl.createPosting(postingID, job, company, postingDate, expirationDate);
    }

    public boolean publishJobPosting(String postingID) {
        return postingID != null && postingControl.publishPosting(postingID);
    }

    public boolean closeJobPosting(String postingID) {
        return postingID != null && postingControl.closePosting(postingID);
    }

    public boolean updatePostingExpiration(String postingID, String newExpirationDate) {
        return postingID != null && newExpirationDate != null && 
               postingControl.updateExpirationDate(postingID, newExpirationDate);
    }

    public boolean recordPostingView(String postingID) {
        return postingID != null && postingControl.recordView(postingID);
    }

    public boolean addPostingApplication(String postingID) {
        return postingID != null && postingControl.addApplication(postingID);
    }

    public boolean addPostingNote(String postingID, String note) {
        return postingID != null && note != null && postingControl.addNote(postingID, note);
    }

    public Posting getPostingById(String postingID) {
        return postingID != null ? postingControl.getPostingById(postingID) : null;
    }

    public ListInterface<Posting> getAllPostings() {
        return postingControl.getAllPostings();
    }

    public ListInterface<Posting> getActivePostings() {
        return postingControl.getActivePostings();
    }

    public ListInterface<Posting> getPostingsByCompany(String companyID) {
        if (companyID == null) return new SortedList<>();
        Company company = getCompanyById(companyID);
        return company != null ? postingControl.getPostingsByCompany(company) : new SortedList<>();
    }

    public ListInterface<Posting> getPostingsByJob(String jobID) {
        if (jobID == null) return new SortedList<>();
        Job job = getJobById(jobID);
        return job != null ? postingControl.getPostingsByJob(job) : new SortedList<>();
    }

    public boolean addJob(Job job) {
        if (job == null) return false;
        jobList.add(job); // add() called from ListInterface class
        return getJobById(job.getJobID()) != null;
    }

    public boolean removeJob(String jobID) {
        if (jobID == null) return false;
        Job jobToRemove = getJobById(jobID);
        return jobToRemove != null && jobList.remove(jobToRemove);  // remove() called from ListInterface class
    }

    public Job getJobById(String jobID) {
        if (jobID == null) return null;
        for (Job job : jobList) {
            if (jobID.equals(job.getJobID())) {
                return job;
            }
        }
        return null;
    }

    public ListInterface<Job> getAllJobs() {
        ListInterface<Job> copy = new SortedList<>(); //ADT object declared
        for (Job job : jobList) {
            copy.add(job); // add() called from ListInterface class
        }
        return copy;
    }

    public Company getCompanyById(String companyID) {
        if (companyID == null) return null;
        for (Company company : companyList) {
            if (companyID.equals(company.getCompanyID())) {
                return company;
            }
        }
        return null;
    }

    
    public boolean updateJobByIndex(int index, Job newJob) {
        return jobList.update(index, newJob); // update() called from ListInterface class
    }
    public ListInterface<Company> getAllCompanies() {
        ListInterface<Company> copy = new SortedList<>(); //ADT object declared
        for (Company company : companyList) {
            copy.add(company); // add() called from ListInterface class
        }
        return copy;
    }

    public ListInterface<Job> filterJobsByLocation(String location) {
        ListInterface<Job> filtered = new SortedList<>(); //ADT object declared
        if (location != null) {
            for (Job job : jobList) {
                if (job.getLocation() != null && 
                    job.getLocation().toLowerCase().contains(location.toLowerCase())) { // contains() called from ListInterface class
                    filtered.add(job); // add() called from ListInterface class
                }
            }
        }
        return filtered;
    }

    public ListInterface<Job> filterJobsByCategory(String category) {
        ListInterface<Job> filtered = new SortedList<>(); //ADT object declared
        if (category != null) {
            for (Job job : jobList) {
                if (job.getCategory() != null && 
                    job.getCategory().equalsIgnoreCase(category)) {
                    filtered.add(job); // add() called from ListInterface class
                }
            }
        }
        return filtered;
    }

    public ListInterface<Job> filterJobsByStudyLevel(String studyLevel) {
        ListInterface<Job> filtered = new SortedList<>(); //ADT object declared
        if (studyLevel != null) {
            for (Job job : jobList) {
                if (job.getStudyLevelRequired() != null && 
                    job.getStudyLevelRequired().equalsIgnoreCase(studyLevel)) {
                    filtered.add(job); // add() called from ListInterface class
                }
            }
        }
        return filtered;
    }

    public ListInterface<Job> filterJobsBySalaryRange(String min, String max) {
        ListInterface<Job> filtered = new SortedList<>(); //ADT object declared
        if (min == null || max == null) return filtered;

        try {
            double minVal = Double.parseDouble(min.replaceAll("[^0-9.]", ""));
            double maxVal = Double.parseDouble(max.replaceAll("[^0-9.]", ""));

            for (Job job : jobList) {
                if (job.getSalaryRange() != null) {
                    String salaryStr = job.getSalaryRange().replaceAll("[^0-9.-]", "");
                    if (salaryStr.contains("-")) { // contains() called from ListInterface class
                        String[] range = salaryStr.split("-");
                        double jobMin = Double.parseDouble(range[0]);
                        double jobMax = Double.parseDouble(range[1]);
                        if (jobMin >= minVal && jobMax <= maxVal) {
                            filtered.add(job); // add() called from ListInterface class
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            // Invalid number format
        }
        return filtered;
    }
        
    public ListInterface<Job> getJobsByCompany(String companyID) {
        ListInterface<Job> companyJobs = new SortedList<>(); //ADT object declared
        if (companyID != null) {
            Company company = getCompanyById(companyID);
            if (company != null) {
                for (Job job : jobList) {
                    if (company.equals(job.getCompany())) {
                        companyJobs.add(job); // add() called from ListInterface class
                    }
                }
            }
        }
        return companyJobs;
    }

    public boolean jobExists(String jobID) {
        return getJobById(jobID) != null;
    }

    public int getTotalJobCount() {
        return jobList.getNumberOfEntries(); // getNumberOfEntries() called from ListInterface class
    }

    public int getTotalCompanyCount() {
        return companyList.getNumberOfEntries(); // getNumberOfEntries() called from ListInterface class
    }

    public void sortJobsById() {
        Job[] jobsArray = listToArray(jobList);
        quickSort(jobsArray, 0, jobsArray.length - 1, "id");
        arrayToList(jobsArray, jobList);
    }
    
    /**
     * Gets the union of two job lists (all unique jobs from both lists)
     */
    public ListInterface<Job> getUnionOfJobs(ListInterface<Job> list1, ListInterface<Job> list2) {
        if (list1 == null) list1 = new SortedList<>();
        if (list2 == null) list2 = new SortedList<>();
        return list1.union(list2); // union() called from ListInterface class
    }
    
    /**
     * Gets the intersection of two job lists (only jobs present in both lists)
     */
    public ListInterface<Job> getIntersectionOfJobs(ListInterface<Job> list1, ListInterface<Job> list2) {
        if (list1 == null || list2 == null) return new SortedList<>();
        return list1.intersection(list2); // intersection() called from ListInterface class
    }
    
    /**
     * Gets the difference between two job lists (jobs in list1 but not in list2)
     */
    public ListInterface<Job> getDifferenceOfJobs(ListInterface<Job> list1, ListInterface<Job> list2) {
        if (list1 == null) return new SortedList<>();
        if (list2 == null) return list1;
        return list1.difference(list2); // difference() called from ListInterface class
    }
    
    /**
     * Gets jobs matching all specified filters (intersection of multiple filters)
     */
    public ListInterface<Job> getJobsMatchingAllFilters(String location, String category, String studyLevel) {
        ListInterface<Job> filtered = getAllJobs();
        
        if (location != null && !location.isEmpty()) { // isEmpty() called from ListInterface class
            filtered = filtered.intersection(filterJobsByLocation(location)); // intersection() called from ListInterface class
        }
        
        if (category != null && !category.isEmpty()) { // isEmpty() called from ListInterface class
            filtered = filtered.intersection(filterJobsByCategory(category)); // intersection() called from ListInterface class
        }
        
        if (studyLevel != null && !studyLevel.isEmpty()) { // isEmpty() called from ListInterface class
            filtered = filtered.intersection(filterJobsByStudyLevel(studyLevel)); // intersection() called from ListInterface class
        }
        
        return filtered;
    }
    
    public boolean updateJobPosting(String postingID, String jobID, String companyID,
                                 String postingDate, String expirationDate) {
        // Get the existing posting first
        Posting existingPosting = postingControl.getPostingById(postingID);
        if (existingPosting == null) return false;
        
        // Only get new job/company if IDs were provided
        Job job = (jobID != null && !jobID.isEmpty()) ? getJobById(jobID) : existingPosting.getJob(); // isEmpty() called from ListInterface class
        Company company = (companyID != null && !companyID.isEmpty()) ? getCompanyById(companyID) : existingPosting.getCompany(); // isEmpty() called from ListInterface class
        
        if (job == null || company == null) return false;
        
        // Use existing dates if new ones not provided
        String newPostingDate = (postingDate != null && !postingDate.isEmpty()) ? postingDate : existingPosting.getPostingDate(); // isEmpty() called from ListInterface class
        String newExpirationDate = (expirationDate != null && !expirationDate.isEmpty()) ? expirationDate : existingPosting.getExpirationDate(); // isEmpty() called from ListInterface class
        
        return postingControl.updatePosting(postingID, job, company, newPostingDate, newExpirationDate);
    }
    
}
