package control;
// Author Lim Yin Ze

import adt.ListInterface;
import entity.Student;
import entity.Job;
import entity.Match;
import entity.Weighting;
import adt.SortedList;
import dao.JobInitializer;
import dao.StudentInitializer;

public class MatchingEngineControl {

    private ListInterface<Match> matches;
    private ListInterface<Weighting> weightings;
    private ListInterface<Student> students;
    private ListInterface<Job> jobs;
    private ListInterface <Match> reportMatchList = new SortedList<>();
    private JobInitializer jobInitializer;
    private StudentInitializer studentInitializer;
    private int matchCounter = 1000;

    public MatchingEngineControl() {
        this.matches = new SortedList<>();
        this.weightings = new SortedList<>();
        this.students = new SortedList<>();
        this.jobs = new SortedList<>();
        this.jobInitializer = new JobInitializer();
        this.studentInitializer = new StudentInitializer();
        initializeDefaultWeightings();
    }

    public void calculateMatches(ListInterface<Student> studentList, ListInterface<Job> jobList) {
        this.matches.clear();
        this.students.clear();
        this.jobs.clear();
        this.matchCounter = 1000;

        // Load data using ADT operations
        for (Student student : studentList) {
            this.students.add(student);
        }

        for (Job job : jobList) {
            this.jobs.add(job);
        }

        for (Student student : this.students) {
            Match bestMatch = findBestMatchForStudent(student);
            if (bestMatch != null) {
                this.matches.add(bestMatch);
            }
        }
    }

    private Match findBestMatchForStudent(Student student) {
        Match bestMatch = null;
        double highestScore = 0.0;

        for (Job job : this.jobs) {
            if (isBasicMatch(student, job)) {
                double score = calculateMatchScore(student, job);

                // Track best match
                if (score >= 0.1 && score > highestScore) {
                    highestScore = score;
                    ListInterface<String> matchedSkills = getMatchedSkills(student, job);

                    String matchID = "M" + (++matchCounter);
                    bestMatch = new Match(matchID, student, job);
                    bestMatch.setMatchScore(score);
                    bestMatch.setMatchedSkills(String.join(", ", matchedSkills));
                }
            }
        }
        return bestMatch;
    }

    // skill matching with partial matches
    private ListInterface<String> getMatchedSkills(Student student, Job job) {
        ListInterface<String> matchedSkills = new SortedList<>();
        String[] studentSkills = student.getSkills().toLowerCase().split("\\s*,\\s*");

        // Compare each student skill with each job requirement
        for (String sSkill : studentSkills) {
            for (String jSkill : job.getRequiredSkills()) {
                String cleanJSkill = jSkill.toLowerCase().trim();
                if (sSkill.trim().equals(cleanJSkill)
                        || sSkill.contains(cleanJSkill)
                        || cleanJSkill.contains(sSkill)) {
                    matchedSkills.add(jSkill); // Store original job skill name
                    break;
                }
            }
        }
        return matchedSkills;
    }

    private boolean isBasicMatch(Student student, Job job) {
        String studentJobType = student.getDesiredJobType().toLowerCase().trim();
        String jobCategory = job.getCategory().toLowerCase().trim();

        return studentJobType.contains(jobCategory)
                || jobCategory.contains(studentJobType)
                || (studentJobType.contains("developer") && jobCategory.contains("developer"))
                || (studentJobType.contains("engineer") && jobCategory.contains("engineer"));
    }

    private double calculateMatchScore(Student student, Job job) {
        double totalScore = 0.0;

        for (Weighting weighting : weightings) {
            String criteria = weighting.getCriteria().toLowerCase();

            switch (criteria) {
                case "skills":
                    totalScore += matchsSkills(student, job) * weighting.getWeight();
                    break;
                case "cgpa":
                    totalScore += matchsCGPA(student, job) * weighting.getWeight();
                    break;
                case "location":
                    totalScore += matchsLocation(student, job) * weighting.getWeight();
                    break;
                case "experience":
                    totalScore += matchsExperience(student, job) * weighting.getWeight();
                    break;
            }
        }
        return Math.min(1.0, totalScore);
    }

    // matching methods
    private double matchsSkills(Student student, Job job) {
        if (job.getRequiredSkills() == null || job.getRequiredSkills().length == 0) {
            return 0.5;
        }

        String[] studentSkills = student.getSkills().toLowerCase().split("\\s*,\\s*");
        double score = 0;

        // Count matching skills
        for (String jSkill : job.getRequiredSkills()) {
            String cleanJSkill = jSkill.toLowerCase().trim();
            boolean found = false;

            for (String sSkill : studentSkills) {
                if (sSkill.trim().equals(cleanJSkill)) {
                    score += 1.0; // Exact match
                    found = true;
                    break;
                } else if (sSkill.contains(cleanJSkill) || cleanJSkill.contains(sSkill)) {
                    score += 0.7; // Partial match
                    found = true;
                    break;
                }
            }

            if (!found) {
                // Check for related skills
                if (isRelatedSkill(studentSkills, cleanJSkill)) {
                    score += 0.4;
                }
            }
        }

        return score / job.getRequiredSkills().length;
    }

    private boolean isRelatedSkill(String[] studentSkills, String jobSkill) {
        // Define skill relationships
        String[][] relatedGroups = {
            {"java", "spring", "j2ee"},
            {"python", "django", "flask"},
            {"ai", "machine learning", "deep learning"}
        };

        for (String[] group : relatedGroups) {
            boolean hasJobSkill = false;
            boolean hasStudentSkill = false;

            for (String skill : group) {
                if (skill.equals(jobSkill)) {
                    hasJobSkill = true;
                }
                for (String sSkill : studentSkills) {
                    if (sSkill.trim().equals(skill)) {
                        hasStudentSkill = true;
                    }
                }
            }

            if (hasJobSkill && hasStudentSkill) {
                return true;
            }
        }
        return false;
    }

    private double matchsCGPA(Student student, Job job) {
        try {
            double minCGPA = Double.parseDouble(job.getStudyLevelRequired());
            double diff = student.getCgpa() - minCGPA;
            if (diff >= 0.5) {
                return 1.0;
            }
            if (diff >= 0) {
                return 0.8;
            }
            if (diff >= -0.5) {
                return 0.5;
            }
            return 0.2;
        } catch (NumberFormatException e) {
            return 0.5;
        }
    }

    private double matchsLocation(Student student, Job job) {
        if (student.getLocation().equalsIgnoreCase(job.getLocation())) {
            return 1.0;
        }

        // Check nearby locations
        String[][] nearbyGroups = {
            {"Kuala Lumpur", "Selangor"},
            {"Penang", "Kedah"},
            {"Johor", "Malacca"}
        };

        for (String[] group : nearbyGroups) {
            boolean hasStudent = false;
            boolean hasJob = false;

            for (String loc : group) {
                if (loc.equalsIgnoreCase(student.getLocation())) {
                    hasStudent = true;
                }
                if (loc.equalsIgnoreCase(job.getLocation())) {
                    hasJob = true;
                }
            }

            if (hasStudent && hasJob) {
                return 0.7;
            }
        }

        return 0.0;
    }

    private double matchsExperience(Student student, Job job) {
        int studentExp = getExperienceValue(student.getStudyLevel());
        int jobExp = getStudyLevelRequirement(job.getDescription());

        if (studentExp >= jobExp) {
            return 1.0;
        }
        if (studentExp == jobExp - 1) {
            return 0.7;
        }
        if (studentExp > 0) {
            return 0.4;
        }
        return 0.0;
    }

    public Match getMatchByID(String matchID) {
        for (Match match : matches) {
            if (match.getMatchID().equals(matchID)) {
                return match;
            }
        }
        return null;
    }

    public Match getMatchByStudentID(String studentID) {
        for (Match match : matches) {
            if (match.getStudent().getStudentID().equals(studentID)) {
                return match;
            }
        }
        return null;
    }

    public ListInterface<Match> getMatchesByJobID(String jobID) {
        ListInterface<Match> jobMatches = new SortedList<>();
        for (Match match : matches) {
            if (match.getJob().getJobID().equals(jobID)) {
                jobMatches.add(match);
            }
        }
        return jobMatches;
    }

    private double matchSkills(Student student, Job job) {
        // Original implementation kept for reference
        if (job.getRequiredSkills() == null || job.getRequiredSkills().length == 0) {
            return 0.5;
        }

        String[] studentSkills = student.getSkills().split(",");
        int matches = 0;

        for (String sSkill : studentSkills) {
            for (String jSkill : job.getRequiredSkills()) {
                if (sSkill.trim().equalsIgnoreCase(jSkill.trim())) {
                    matches++;
                    break;
                }
            }
        }
        return (double) matches / job.getRequiredSkills().length;
    }

    private double matchCGPA(Student student, Job job) {
        try {
            double minCGPA = Double.parseDouble(job.getStudyLevelRequired());
            return student.getCgpa() >= minCGPA ? 1.0 : 0.3;
        } catch (NumberFormatException e) {
            return 0.5;
        }
    }

    private double matchLocation(Student student, Job job) {
        return student.getLocation().equalsIgnoreCase(job.getLocation()) ? 1.0 : 0.0;
    }

    private double matchExperience(Student student, Job job) {
        int studentExp = getExperienceValue(student.getStudyLevel());
        int jobExp = getStudyLevelRequirement(job.getDescription());
        return studentExp >= jobExp ? 1.0 : (studentExp > 0 ? 0.5 : 0.0);
    }

    /**
     * Generates a comprehensive report of all matches
     *
     * @return Formatted report string with match details and analysis
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();

        // Header with weighting information
        report.append("MATCHING REPORT\n");
        report.append("===============\n\n");
        report.append("Weighting Criteria:\n");
        report.append("-------------------\n");
        for (Weighting w : weightings) {
            report.append(String.format("- %s (Weight: %.2f, Importance: %s)\n",
                    w.getCriteria(), w.getWeight(), w.getImportance()));
        }

        // Match statistics
        report.append("\nMatch Statistics:\n");
        report.append("----------------\n");
        report.append(String.format("Total Students: %d\n", students.getNumberOfEntries()));
        report.append(String.format("Total Jobs: %d\n", jobs.getNumberOfEntries()));
        report.append(String.format("Total Matches: %d\n", matches.getNumberOfEntries()));

        // Match details
        report.append("\nDetailed Matches:\n");
        report.append("========================================================================================================\n");
        report.append(String.format("%-8s %-20s %-25s %-18s %-6s %-12s %-10s %-6s %s\n",
                "MatchID", "Student", "Job Position", "Matched Skills", "CGPA", "Location", "Exp.", "Score", "Strength"));
        report.append("========================================================================================================\n");

        for (Match match : matches) {
            Student student = match.getStudent();
            Job job = match.getJob();

            String strength = getMatchStrength(match.getMatchScore());

            report.append(String.format("%-8s %-20s %-25s %-18s %-6.2f %-12s %-10s %-6.2f %s\n",
                    match.getMatchID(),
                    student.getStudentName(),
                    job.getTitle(),
                    match.getMatchedSkills(),
                    student.getCgpa(),
                    student.getLocation(),
                    student.getStudyLevel(),
                    match.getMatchScore(),
                    strength));

            reportMatchList.add(match);
        }

        // Summary analysis
        report.append("\nSummary Analysis:\n");
        report.append("----------------\n");
        report.append(getMatchAnalysis());

        return report.toString();
    }

    /**
     * Determines match strength category
     *
     * @param score The match score (0.0-1.0)
     * @return Strength description
     */
    private String getMatchStrength(double score) {
        if (score >= 0.8) {
            return "Excellent";
        }
        if (score >= 0.6) {
            return "Good";
        }
        if (score >= 0.4) {
            return "Fair";
        }
        return "Weak";
    }

    /**
     * Generates analysis of matches
     *
     * @return Analysis string
     */
    private String getMatchAnalysis() {
        if (matches.isEmpty()) {
            return "No matches available for analysis";
        }

        int excellent = 0, good = 0, fair = 0, weak = 0;
        double totalScore = 0;

        for (Match m : matches) {
            double score = m.getMatchScore();
            totalScore += score;

            if (score >= 0.8) {
                excellent++;
            } else if (score >= 0.6) {
                good++;
            } else if (score >= 0.4) {
                fair++;
            } else {
                weak++;
            }
        }

        double avgScore = totalScore / matches.getNumberOfEntries();

        return String.format(
                "Match Quality Distribution:\n"
                + "- Excellent (≥0.8): %d (%.1f%%)\n"
                + "- Good (0.6-0.8): %d (%.1f%%)\n"
                + "- Fair (0.4-0.6): %d (%.1f%%)\n"
                + "- Weak (<0.4): %d (%.1f%%)\n\n"
                + "Average Match Score: %.2f",
                excellent, (excellent * 100.0 / matches.getNumberOfEntries()),
                good, (good * 100.0 / matches.getNumberOfEntries()),
                fair, (fair * 100.0 / matches.getNumberOfEntries()),
                weak, (weak * 100.0 / matches.getNumberOfEntries()),
                avgScore
        );
    }

    private int getExperienceValue(String level) {
        return switch (level.toLowerCase()) {
            case "Master" ->
                3;
            case "Degree" ->
                2;
            case "Diploma" ->
                1;
            default ->
                0;
        };
    }

    private int getStudyLevelRequirement(String description) {
        if (description.contains("PHD") || description.contains("Master")) {
            return 3;
        }
        if (description.contains("Bachelor") || description.contains("Degree")) {
            return 2;
        }
        return 1;
    }

    public ListInterface<Match> getAllMatches() {
        return new SortedList<>(matches);
    }
    
       public ListInterface<Match> getReportedMatches() {
        return reportMatchList;
    }

    public ListInterface<Weighting> getWeightings() {
        return new SortedList<>(weightings);
    }

    //Adds a new weighting criteria to the system.
    public void addWeighting(Weighting weighting) {
        weightings.add(weighting);
    }

    public boolean remove(Match match) {
        return matches.remove(match); //return true if match was found and removed
    }

    private void initializeDefaultWeightings() {
        weightings.add(new Weighting("Skills", 0.5, "High"));
        weightings.add(new Weighting("CGPA", 0.3, "Medium"));
        weightings.add(new Weighting("Location", 0.1, "Low"));
        weightings.add(new Weighting("Experience", 0.1, "Low"));
    }
}
