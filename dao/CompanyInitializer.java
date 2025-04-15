package dao;
// author: CHONG PEI LEE
import adt.ListInterface;
import adt.SortedList;
import entity.Company;

public class CompanyInitializer {
    public ListInterface<Company> initializeCompanies() {
        ListInterface<Company> companyList = new SortedList<>();
        
        // 1-10: Major IT Companies
        companyList.add(new Company("C1001", "Tech Solutions Malaysia", "Software Development", "Kuala Lumpur"));
        companyList.add(new Company("C1002", "Data Dynamics", "Big Data Analytics", "Penang"));
        companyList.add(new Company("C1003", "Cloud Innovations Asia", "Cloud Computing", "Selangor"));
        companyList.add(new Company("C1004", "CyberSecure Malaysia", "Cybersecurity", "Johor Bahru"));
        companyList.add(new Company("C1005", "AI Frontier", "Artificial Intelligence", "Kuala Lumpur"));
        companyList.add(new Company("C1006", "Network Solutions", "Networking", "Penang"));
        companyList.add(new Company("C1007", "CodeCraft", "Software Development", "Selangor"));
        companyList.add(new Company("C1008", "MobileFirst", "Mobile Development", "Johor Bahru"));
        companyList.add(new Company("C1009", "BlockChain Asia", "Blockchain", "Kuala Lumpur"));
        companyList.add(new Company("C1010", "IoT Connect", "Internet of Things", "Penang"));

        // 11-20: Software Companies
        companyList.add(new Company("C1011", "Binary Logic", "Enterprise Software", "Selangor"));
        companyList.add(new Company("C1012", "Pixel Perfect", "UI/UX Design", "Johor Bahru"));
        companyList.add(new Company("C1013", "DevOps Malaysia", "DevOps Services", "Kuala Lumpur"));
        companyList.add(new Company("C1014", "FullStack Solutions", "Web Development", "Penang"));
        companyList.add(new Company("C1015", "Database Pros", "Database Management", "Selangor"));
        companyList.add(new Company("C1016", "TestAutomate", "QA Testing", "Johor Bahru"));
        companyList.add(new Company("C1017", "GameSphere", "Game Development", "Kuala Lumpur"));
        companyList.add(new Company("C1018", "ARVR Labs", "Augmented Reality", "Penang"));
        companyList.add(new Company("C1019", "FinTech Solutions", "Financial Software", "Selangor"));
        companyList.add(new Company("C1020", "EduTech Asia", "Education Software", "Johor Bahru"));

        // 21-30: Specialized IT Services
        companyList.add(new Company("C1021", "HealthTech", "Medical Software", "Kuala Lumpur"));
        companyList.add(new Company("C1022", "RetailTech", "Retail Systems", "Penang"));
        companyList.add(new Company("C1023", "LogiTech", "Logistics Software", "Selangor"));
        companyList.add(new Company("C1024", "PropTech", "Real Estate Tech", "Johor Bahru"));
        companyList.add(new Company("C1025", "MediaSphere", "Media Technology", "Kuala Lumpur"));
        companyList.add(new Company("C1026", "TravelTech", "Travel Software", "Penang"));
        companyList.add(new Company("C1027", "AdTech", "Advertising Technology", "Selangor"));
        companyList.add(new Company("C1028", "LegalTech", "Legal Software", "Johor Bahru"));
        companyList.add(new Company("C1029", "HR Tech", "HR Systems", "Kuala Lumpur"));
        companyList.add(new Company("C1030", "MarTech", "Marketing Technology", "Penang"));

        // 31-40: Infrastructure Providers
        companyList.add(new Company("C1031", "DataCenter Malaysia", "Hosting Services", "Selangor"));
        companyList.add(new Company("C1032", "5G Networks", "Telecommunications", "Johor Bahru"));
        companyList.add(new Company("C1033", "SmartCity Tech", "Urban Technology", "Kuala Lumpur"));
        companyList.add(new Company("C1034", "GreenTech IT", "Sustainable Tech", "Penang"));
        companyList.add(new Company("C1035", "Quantum Computing MY", "Quantum Tech", "Selangor"));
        companyList.add(new Company("C1036", "RoboTech", "Robotics", "Johor Bahru"));
        companyList.add(new Company("C1037", "NanoTech Solutions", "Nanotechnology", "Kuala Lumpur"));
        companyList.add(new Company("C1038", "BioTech IT", "Biotech Software", "Penang"));
        companyList.add(new Company("C1039", "SpaceTech Asia", "Space Technology", "Selangor"));
        companyList.add(new Company("C1040", "AutoTech", "Automotive Software", "Johor Bahru"));

        // 41-50: Consulting and Services
        companyList.add(new Company("C1041", "IT Consult Malaysia", "IT Consulting", "Kuala Lumpur"));
        companyList.add(new Company("C1042", "Digital Transform", "Digital Strategy", "Penang"));
        companyList.add(new Company("C1043", "SysAdmin Pro", "Managed IT", "Selangor"));
        companyList.add(new Company("C1044", "TechTrain Malaysia", "IT Training", "Johor Bahru"));
        companyList.add(new Company("C1045", "CodeAcademy MY", "Coding Bootcamp", "Kuala Lumpur"));
        companyList.add(new Company("C1046", "IT Recruiters", "Tech Recruitment", "Penang"));
        companyList.add(new Company("C1047", "TechSupport MY", "Helpdesk Services", "Selangor"));
        companyList.add(new Company("C1048", "CyberAudit", "Security Audits", "Johor Bahru"));
        companyList.add(new Company("C1049", "DataPrivacy MY", "Compliance", "Kuala Lumpur"));
        companyList.add(new Company("C1050", "IT Legal", "Tech Law", "Penang"));

        return companyList;
    }
}