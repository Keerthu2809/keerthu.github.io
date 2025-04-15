package dao;

import adt.ListInterface;
import adt.SortedList;
import entity.Job;

public class JobInitializer {
    public static final String[] VALID_LOCATIONS = {
        "Kuala Lumpur", "Penang", "Selangor", "Johor Bahru", "Remote"
    };

    public ListInterface<Job> initializeJobs() {
        ListInterface<Job> jobList = new SortedList<>();

        // ==================== AI/ML Jobs ====================
        jobList.add(new Job("J1001", "AI Developer Intern", "Assist in developing AI models and algorithms.", "C1005", 
            getCompanyLocation("C1005"), "AI", "RM 800 - RM 1200", 
            new String[]{"Python", "TensorFlow", "Machine Learning"}, "Diploma", 
            "2023-10-01", "2023-11-01", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1013", "Machine Learning Intern", "Assist in developing and training machine learning models.", "C1005", 
            "Remote", "AI", "RM 800 - RM 1200", 
            new String[]{"Python", "TensorFlow", "Scikit-learn"}, "Diploma", 
            "2023-11-25", "2023-12-25", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1021", "AI Research Intern", "Assist in researching and developing AI algorithms.", "C1005", 
            getCompanyLocation("C1005"), "AI", "RM 800 - RM 1200", 
            new String[]{"Python", "TensorFlow", "Research"}, "Diploma", 
            "2024-01-01", "2024-02-01", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1036", "AI Ethics Intern", "Assist in researching ethical implications of AI.", "C1005", 
            getCompanyLocation("C1005"), "AI", "RM 800 - RM 1200", 
            new String[]{"Ethics", "AI", "Research"}, "Degree", 
            "2024-03-10", "2024-04-10", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1047", "Robotics Intern", "Assist in developing robotic systems.", "C1036", 
            getCompanyLocation("C1036"), "AI", "RM 800 - RM 1200", 
            new String[]{"ROS", "C++", "Robotics"}, "Degree", 
            "2024-04-30", "2024-05-30", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));

        // ==================== Software Development Jobs ====================
        jobList.add(new Job("J1002", "Backend Developer Intern", "Assist in developing server-side logic and APIs.", "C1001", 
            getCompanyLocation("C1001"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Java", "Spring Boot", "REST APIs"}, "Diploma", 
            "2023-10-05", "2023-11-05", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1003", "Cloud Engineer Intern", "Assist in managing and deploying cloud infrastructure.", "C1003", 
            "Remote", "Software Development", "RM 800 - RM 1200", 
            new String[]{"AWS", "Azure", "Docker"}, "Diploma", 
            "2023-10-10", "2023-11-10", "Open", 
            new String[]{"Allowance", "Training", "Certification Support"}));
            
        jobList.add(new Job("J1007", "Database Administrator Intern", "Assist in managing and optimizing databases.", "C1015", 
            getCompanyLocation("C1015"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"MySQL", "PostgreSQL", "MongoDB"}, "Diploma", 
            "2023-10-30", "2023-11-30", "Open", 
            new String[]{"Allowance", "Training", "Certification Support"}));
            
        jobList.add(new Job("J1008", "DevOps Engineer Intern", "Assist in automating deployment and infrastructure management.", "C1013", 
            getCompanyLocation("C1013"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Docker", "Kubernetes", "CI/CD"}, "Diploma", 
            "2023-11-01", "2023-12-01", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1009", "Frontend Developer Intern", "Assist in developing user interfaces for web applications.", "C1014", 
            getCompanyLocation("C1014"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"HTML", "CSS", "JavaScript"}, "Diploma", 
            "2023-11-05", "2023-12-05", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1010", "Full Stack Developer Intern", "Assist in developing both frontend and backend components.", "C1014", 
            "Remote", "Software Development", "RM 800 - RM 1200", 
            new String[]{"React", "Node.js", "MongoDB"}, "Degree", 
            "2023-11-10", "2023-12-10", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1011", "Game Developer Intern", "Assist in developing and testing video games.", "C1017", 
            getCompanyLocation("C1017"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Unity", "C#", "3D Modeling"}, "Diploma", 
            "2023-11-15", "2023-12-15", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1014", "Mobile App Developer Intern", "Assist in developing mobile applications for iOS and Android.", "C1008", 
            getCompanyLocation("C1008"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Flutter", "Kotlin", "Swift"}, "Diploma", 
            "2023-11-30", "2023-12-30", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1016", "QA Engineer Intern", "Assist in testing and ensuring software quality.", "C1016", 
            getCompanyLocation("C1016"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Selenium", "JUnit", "Manual Testing"}, "Diploma", 
            "2023-12-05", "2024-01-05", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1017", "Software Engineer Intern", "Assist in developing and maintaining software applications.", "C1001", 
            getCompanyLocation("C1001"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Java", "Python", "SQL"}, "Diploma", 
            "2023-12-10", "2024-01-10", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1018", "Systems Analyst Intern", "Assist in analyzing and improving IT systems.", "C1011", 
            getCompanyLocation("C1011"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"UML", "Requirement Analysis", "Process Modeling"}, "Diploma", 
            "2023-12-15", "2024-01-15", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1019", "UI/UX Designer Intern", "Assist in designing user interfaces and experiences.", "C1012", 
            "Remote", "Software Development", "RM 800 - RM 1200", 
            new String[]{"Figma", "Adobe XD", "Wireframing"}, "Diploma", 
            "2023-12-20", "2024-01-20", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1020", "Web Developer Intern", "Assist in developing and maintaining websites.", "C1014", 
            getCompanyLocation("C1014"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"HTML", "CSS", "JavaScript"}, "Diploma", 
            "2023-12-25", "2024-01-25", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1022", "Blockchain Developer Intern", "Assist in developing blockchain-based applications.", "C1009", 
            getCompanyLocation("C1009"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Solidity", "Ethereum", "Smart Contracts"}, "Diploma", 
            "2024-01-05", "2024-02-05", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1026", "Embedded Systems Intern", "Assist in developing embedded software.", "C1036", 
            getCompanyLocation("C1036"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"C", "C++", "Microcontrollers"}, "Degree", 
            "2024-01-25", "2024-02-25", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1028", "IT Project Manager Intern", "Assist in managing IT projects.", "C1041", 
            getCompanyLocation("C1041"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Agile", "Scrum", "Project Management"}, "Degree", 
            "2024-02-01", "2024-03-01", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1030", "Software Tester Intern", "Assist in testing software applications.", "C1016", 
            getCompanyLocation("C1016"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Manual Testing", "Automation Testing", "JUnit"}, "Degree", 
            "2024-02-10", "2024-03-10", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1032", "Technical Writer Intern", "Assist in writing technical documentation.", "C1044", 
            "Remote", "Software Development", "RM 800 - RM 1200", 
            new String[]{"Technical Writing", "Markdown", "Git"}, "Degree", 
            "2024-02-20", "2024-03-20", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1033", "UI Developer Intern", "Assist in developing user interfaces.", "C1012", 
            getCompanyLocation("C1012"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"HTML", "CSS", "JavaScript"}, "Diploma", 
            "2024-02-25", "2024-03-25", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1034", "UX Researcher Intern", "Assist in conducting user experience research.", "C1012", 
            getCompanyLocation("C1012"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"User Research", "Surveys", "Usability Testing"}, "Degree", 
            "2024-03-01", "2024-04-01", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1035", "Web Designer Intern", "Assist in designing websites.", "C1014", 
            getCompanyLocation("C1014"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"HTML", "CSS", "JavaScript"}, "Degree", 
            "2024-03-05", "2024-04-05", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1037", "AR/VR Developer Intern", "Assist in developing augmented and virtual reality applications.", "C1018", 
            getCompanyLocation("C1018"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Unity", "C#", "3D Modeling"}, "Degree", 
            "2024-03-15", "2024-04-15", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1039", "Cloud Architect Intern", "Assist in designing cloud infrastructure.", "C1003", 
            "Remote", "Software Development", "RM 800 - RM 1200", 
            new String[]{"AWS", "Azure", "GCP"}, "Degree", 
            "2024-03-25", "2024-04-25", "Open", 
            new String[]{"Allowance", "Training", "Certification Support"}));
            
        jobList.add(new Job("J1041", "Digital Marketing Intern", "Assist in managing digital marketing campaigns.", "C1030", 
            getCompanyLocation("C1030"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"SEO", "Social Media", "Analytics"}, "Degree", 
            "2024-04-01", "2024-05-01", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1042", "Game Designer Intern", "Assist in designing video games.", "C1017", 
            getCompanyLocation("C1017"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Unity", "C#", "Game Design"}, "Degree", 
            "2024-04-05", "2024-05-05", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1043", "IT Consultant Intern", "Assist in providing IT consulting services.", "C1041", 
            "Remote", "Software Development", "RM 800 - RM 1200", 
            new String[]{"Consulting", "IT Strategy", "Business Analysis"}, "Degree", 
            "2024-04-10", "2024-05-10", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1044", "IT Trainer Intern", "Assist in training users on IT systems.", "C1044", 
            getCompanyLocation("C1044"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Training", "IT Systems", "Communication"}, "Degree", 
            "2024-04-15", "2024-05-15", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1045", "Mobile Game Developer Intern", "Assist in developing mobile games.", "C1017", 
            getCompanyLocation("C1017"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"Unity", "C#", "Game Development"}, "Degree", 
            "2024-04-20", "2024-05-20", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1048", "Software Architect Intern", "Assist in designing software systems.", "C1001", 
            getCompanyLocation("C1001"), "Software Development", "RM 800 - RM 1200", 
            new String[]{"UML", "Design Patterns", "System Architecture"}, "Degree", 
            "2024-05-01", "2024-06-01", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));

        // ==================== Data Science Jobs ====================
        jobList.add(new Job("J1005", "Data Analyst Intern", "Analyze and interpret data to support decision-making.", "C1002", 
            getCompanyLocation("C1002"), "Data Science", "RM 800 - RM 1200", 
            new String[]{"Python", "R", "Excel"}, "Diploma", 
            "2023-10-20", "2023-11-20", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1006", "Data Engineer Intern", "Assist in building and maintaining data pipelines.", "C1002", 
            "Remote", "Data Science", "RM 800 - RM 1200", 
            new String[]{"SQL", "Python", "ETL"}, "Diploma", 
            "2023-10-25", "2023-11-25", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1024", "Data Scientist Intern", "Assist in analyzing and interpreting complex data.", "C1002", 
            getCompanyLocation("C1002"), "Data Science", "RM 800 - RM 1200", 
            new String[]{"Python", "R", "Machine Learning"}, "Diploma", 
            "2024-01-15", "2024-02-15", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1038", "Big Data Intern", "Assist in processing and analyzing large datasets.", "C1002", 
            getCompanyLocation("C1002"), "Data Science", "RM 800 - RM 1200", 
            new String[]{"Hadoop", "Spark", "Python"}, "Degree", 
            "2024-03-20", "2024-04-20", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));

        // ==================== Cybersecurity Jobs ====================
        jobList.add(new Job("J1004", "Cybersecurity Intern", "Assist in monitoring and securing IT systems.", "C1004", 
            getCompanyLocation("C1004"), "Cybersecurity", "RM 800 - RM 1200", 
            new String[]{"Networking", "Security", "Linux"}, "Diploma", 
            "2023-10-15", "2023-11-15", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1023", "Cloud Security Intern", "Assist in securing cloud infrastructure.", "C1004", 
            "Remote", "Cybersecurity", "RM 800 - RM 1200", 
            new String[]{"AWS", "Azure", "Security"}, "Degree", 
            "2024-01-10", "2024-02-10", "Open", 
            new String[]{"Allowance", "Training", "Certification Support"}));
            
        jobList.add(new Job("J1025", "DevSecOps Intern", "Assist in integrating security into DevOps processes.", "C1004", 
            getCompanyLocation("C1004"), "Cybersecurity", "RM 800 - RM 1200", 
            new String[]{"Docker", "Kubernetes", "Security"}, "Diploma", 
            "2024-01-20", "2024-02-20", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1027", "IT Auditor Intern", "Assist in auditing IT systems and processes.", "C1048", 
            getCompanyLocation("C1048"), "Cybersecurity", "RM 800 - RM 1200", 
            new String[]{"Auditing", "Compliance", "Risk Management"}, "Degree", 
            "2024-01-30", "2024-02-30", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1029", "Network Security Intern", "Assist in securing network infrastructure.", "C1029", 
            getCompanyLocation("C1029"), "Cybersecurity", "RM 800 - RM 1200", 
            new String[]{"Firewalls", "VPN", "Intrusion Detection"}, "Degree", 
            "2024-02-05", "2024-03-05", "Open", 
            new String[]{"Allowance", "Training", "Certification Support"}));
            
        jobList.add(new Job("J1040", "Data Privacy Intern", "Assist in ensuring compliance with data privacy regulations.", "C1049", 
            getCompanyLocation("C1049"), "Cybersecurity", "RM 800 - RM 1200", 
            new String[]{"GDPR", "Compliance", "Data Protection"}, "Degree", 
            "2024-03-30", "2024-04-30", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1050", "Web Security Intern", "Assist in securing web applications.", "C1004", 
            getCompanyLocation("C1004"), "Cybersecurity", "RM 800 - RM 1200", 
            new String[]{"OWASP", "Firewalls", "Encryption"}, "Degree", 
            "2024-05-10", "2024-06-10", "Open", 
            new String[]{"Allowance", "Training", "Certification Support"}));

        // ==================== Networking Jobs ====================
        jobList.add(new Job("J1012", "IT Support Intern", "Provide technical support to end-users.", "C1047", 
            getCompanyLocation("C1047"), "Networking", "RM 800 - RM 1200", 
            new String[]{"Windows", "Linux", "Troubleshooting"}, "Diploma", 
            "2023-11-20", "2023-12-20", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));
            
        jobList.add(new Job("J1015", "Network Engineer Intern", "Assist in managing and securing network infrastructure.", "C1006", 
            getCompanyLocation("C1006"), "Networking", "RM 800 - RM 1200", 
            new String[]{"Cisco", "Firewalls", "Routing"}, "Diploma", 
            "2023-12-01", "2024-01-01", "Open", 
            new String[]{"Allowance", "Training", "Certification Support"}));
            
        jobList.add(new Job("J1031", "Systems Administrator Intern", "Assist in managing and maintaining IT systems.", "C1031", 
            getCompanyLocation("C1031"), "Networking", "RM 800 - RM 1200", 
            new String[]{"Windows", "Linux", "Networking"}, "Degree", 
            "2024-02-15", "2024-03-15", "Open", 
            new String[]{"Allowance", "Training", "Remote Work"}));
            
        jobList.add(new Job("J1046", "Network Administrator Intern", "Assist in managing network infrastructure.", "C1006", 
            getCompanyLocation("C1006"), "Networking", "RM 800 - RM 1200", 
            new String[]{"Cisco", "Routing", "Switching"}, "Degree", 
            "2024-04-25", "2024-05-25", "Open", 
            new String[]{"Allowance", "Training", "Certification Support"}));
            
        jobList.add(new Job("J1049", "Systems Engineer Intern", "Assist in designing and maintaining IT systems.", "C1031", 
            getCompanyLocation("C1031"), "Networking", "RM 800 - RM 1200", 
            new String[]{"Windows", "Linux", "Networking"}, "Degree", 
            "2024-05-05", "2024-06-05", "Open", 
            new String[]{"Allowance", "Training", "Flexible Hours"}));

        return jobList;
    }

    private String getCompanyLocation(String companyId) {
        switch(companyId) {
            // Major IT Companies (1-10)
            case "C1001": return "Kuala Lumpur";
            case "C1002": return "Penang";
            case "C1003": return "Selangor";
            case "C1004": return "Johor Bahru";
            case "C1005": return "Kuala Lumpur";
            case "C1006": return "Penang";
            case "C1007": return "Selangor";
            case "C1008": return "Johor Bahru";
            case "C1009": return "Kuala Lumpur";
            case "C1010": return "Penang";
            
            // Software Companies (11-20)
            case "C1011": return "Selangor";
            case "C1012": return "Johor Bahru";
            case "C1013": return "Kuala Lumpur";
            case "C1014": return "Penang";
            case "C1015": return "Johor Bahru";
            case "C1016": return "Johor Bahru";
            case "C1017": return "Kuala Lumpur";
            case "C1018": return "Penang";
            case "C1019": return "Selangor";
            case "C1020": return "Johor Bahru";
            
            // Specialized IT Services (21-30)
            case "C1021": return "Kuala Lumpur";
            case "C1022": return "Penang";
            case "C1023": return "Selangor";
            case "C1024": return "Johor Bahru";
            case "C1025": return "Kuala Lumpur";
            case "C1026": return "Penang";
            case "C1027": return "Selangor";
            case "C1028": return "Johor Bahru";
            case "C1029": return "Kuala Lumpur";
            case "C1030": return "Penang";
            
            // Infrastructure Providers (31-40)
            case "C1031": return "Johor Bahru";
            case "C1032": return "Selangor";
            case "C1033": return "Kuala Lumpur";
            case "C1034": return "Penang";
            case "C1035": return "Selangor";
            case "C1036": return "Johor Bahru";
            case "C1037": return "Kuala Lumpur";
            case "C1038": return "Penang";
            case "C1039": return "Selangor";
            case "C1040": return "Johor Bahru";
            
            // Consulting and Services (41-50)
            case "C1041": return "Kuala Lumpur";
            case "C1042": return "Penang";
            case "C1043": return "Selangor";
            case "C1044": return "Johor Bahru";
            case "C1045": return "Kuala Lumpur";
            case "C1046": return "Penang";
            case "C1047": return "Selangor";
            case "C1048": return "Johor Bahru";
            case "C1049": return "Kuala Lumpur";
            case "C1050": return "Penang";
            
            default: return "Kuala Lumpur"; // Default fallback
        }
    }

    public static boolean isValidLocation(String location) {
        if (location.equalsIgnoreCase("Remote")) return true;
        for (String validLoc : VALID_LOCATIONS) {
            if (validLoc.equalsIgnoreCase(location)) {
                return true;
            }
        }
        return false;
    }
}