package dao;

import adt.ListInterface;
import adt.SortedList;
import entity.Student;

// Method to return a collection of Studentobjects with hard-coded values
public class StudentInitializer {

    public ListInterface<Student> initializeStudents() {
        ListInterface<Student> studentList = new SortedList<>();

        // Hard-coded Student objects for TARUMT Diploma and Degree IT courses
        studentList.add(new Student("23WMR15253", "Fang Wei Xiang", "RIS", "fangwx-wp22@student.tarc.edu.my", "019-7465411", "Kuala Lumpur", "Java, Networking", "Diploma", 3.6, "Backend Developer Intern"));
        studentList.add(new Student("23WMR15252", "Fang Wei Sheng", "RIS", "fangws-wp22@student.tarc.edu.my", "019-7465412", "Kuala Lumpur", "Java, HTML+CSS, Database Management", "Diploma", 3.5, "Frontend Developer Intern"));
        studentList.add(new Student("25WMR39821", "John Doe", "RDS", "johndoe-wp22@student.tarc.edu.my", "016-5324785", "Selangor", "Java, Troubleshooting, Software Testing", "Degree", 3.7, "Software Tester Intern"));
        studentList.add(new Student("23WMR16732", "Mary Tan", "REI", "marytan-wp22@student.tarc.edu.my", "013-8762345", "Kuala Lumpur", "Penetration Testing, Networking, Cybersecurity", "Degree", 3.9, "Software Tester Intern"));
        studentList.add(new Student("22WMR14258", "Siti Aisyah", "RSD", "sitiaisyah-wp22@student.tarc.edu.my", "014-7362819", "Kelantan", "HTML, Java, UI/UX Design", "Degree", 3.4, "IT Trainer Intern"));
        studentList.add(new Student("24WMR19287", "Arun Kumar", "RSW", "arunkumar-wp22@student.tarc.edu.my", "011-2374628", "Perak", "AI, Machine Learning, Robotics", "Degree", 3.8, "Robotics Intern"));
        studentList.add(new Student("23WMR14666", "Chong Pei Lee", "RIS", "chongpl-wp22@student.tarc.edu.my", "019-7465413", "Klang", "Penetration Testing, Java, HTML+CSS", "Degree", 3.7, "Software Tester Intern"));
        studentList.add(new Student("23WMR18476", "Mohamed Faris", "RMM", "farismohd-wp22@student.tarc.edu.my", "017-9473628", "Kuala Lumpur", "Java, HTML, Cloud Computing", "Degree", 3.6, "Web Designer Intern"));
        studentList.add(new Student("25WMR16543", "Lee Zhi Peng", "RST", "leezp-wp22@student.tarc.edu.my", "012-6389475", "Selangor", "Networking, Penetration Testing, Cybersecurity", "Degree", 3.5, "Network Security Intern"));
        studentList.add(new Student("23WMR12587", "Rajesh Kumar", "RDS", "rajeshkumar-wp22@student.tarc.edu.my", "018-5746932", "Penang", "Data Analysis, AI, Machine Learning", "Diploma", 3.7, "Data Scientist Intern"));

        studentList.add(new Student("23WMR15281", "Lim Yin Ze", "RIS", "limyz-wp22@student.tarc.edu.my", "019-7465414", "Kuala Lumpur", "Networking, Database Management", "Degree", 3.5, "Network Security Intern"));
        studentList.add(new Student("23WMR15254", "Ngo Tung Fu", "RIS", "ngotf-wp22@student.tarc.edu.my", "019-7465415", "Sabah", "Networking, Game Development", "Diploma", 3.5, "Game Developer Intern"));
        studentList.add(new Student("23WMR15255", "Wong Guang Hao", "RIS", "wonggh-wp22@student.tarc.edu.my", "019-7465416", "Terengganu", "HTML+CSS, AI", "Degree", 3.4, "Full Stack Developer Intern"));
        studentList.add(new Student("24WMR16329", "Priya Sharma", "RMM", "priyasharma-wp22@student.tarc.edu.my", "011-7382945", "Selangor", "HTML, Database Management, UI/UX Design", "Degree", 3.8, "Web Designer Intern"));
        studentList.add(new Student("23WMR19234", "Suresh Nair", "REI", "sureshnair-wp22@student.tarc.edu.my", "019-5267893", "Kelantan", "Penetration Testing, Networking, DevOps", "Diploma", 3.9, "DevSecOps Intern"));
        studentList.add(new Student("24WMR19865", "Chen Mei Ling", "RSD", "chenml-wp22@student.tarc.edu.my", "012-8642973", "Perak", "Data Analysis, Troubleshooting, Cloud Computing", "Degree", 3.4, "Data Privacy Intern"));
        studentList.add(new Student("22WMR17482", "Aravind Raj", "RSW", "aravindraj-wp22@student.tarc.edu.my", "013-9846732", "Sarawak", "AI, Machine Learning, Robotics", "Diploma", 3.8, "AI Developer Intern"));
        studentList.add(new Student("23WMR15267", "Eric Hing Choon Jing", "RIS", "erichcj-wp22@student.tarc.edu.my", "019-7465428", "Melaka", "Frontend Development, UI/UX Design", "Diploma", 3.6, "Frontend Developer Intern"));
        studentList.add(new Student("23WMR15268", "Tan Hong Yu", "RIS", "tanhy-wp22@student.tarc.edu.my", "019-7465429", "Perak", "Cloud Computing, DevOps", "Diploma", 3.9, "Cloud Engineer Intern"));
        studentList.add(new Student("23WMR15269", "Yap Jia Cheng", "RIS", "yapjc-wp22@student.tarc.edu.my", "019-7465430", "Sabah", "Database Management, AI", "Degree", 3.7, "Full Stack Developer Intern"));

        studentList.add(new Student("23WMR15270", "Tan Lip Xin", "RIS", "tanlx-wp22@student.tarc.edu.my", "019-7465431", "Negeri Sembilan", "Cybersecurity, Penetration Testing", "Diploma", 3.8, "QA Engineer Intern"));
        studentList.add(new Student("22WMR16489", "Lim Jun Hao", "RST", "limjh-wp22@student.tarc.edu.my", "011-9375284", "Penang", "Machine Learning, Data Analysis, Cloud Computing", "Degree", 3.6, "Cloud Architect Intern"));
        studentList.add(new Student("24WMR13875", "Vikram Singh", "RDS", "vikramsingh-wp22@student.tarc.edu.my", "014-8932645", "Kuala Lumpur", "Networking, Troubleshooting, Cybersecurity", "Degree", 3.9, "Network Security Intern"));
        studentList.add(new Student("23WMR19823", "Lee Xin Yi", "REI", "leexy-wp22@student.tarc.edu.my", "018-6347289", "Sarawak", "AI, Java, UI/UX Design", "Degree", 3.8, "Mobile Game Developer Intern"));
        studentList.add(new Student("25WMR13248", "Manoj Pillai", "RSD", "manojpillai-wp22@student.tarc.edu.my", "017-4236578", "Johor", "Data Analysis, Penetration Testing", "Diploma", 3.5, "Data Analyst Intern"));
        studentList.add(new Student("22WMR14398", "Chan Hui Ying", "RSW", "chanhy-wp22@student.tarc.edu.my", "019-6728439", "Kelantan", "Java, HTML", "Diploma", 3.7, "Backend Developer Intern"));
        studentList.add(new Student("23WMR15271", "Ivan Yeoh Zi Jie", "RIS", "ivanyzj-wp22@student.tarc.edu.my", "019-7465432", "Kuala Lumpur", "Machine Learning, AI", "Diploma", 3.9, "Machine Learning Intern"));
        studentList.add(new Student("23WMR15272", "Loo Khai Ching", "RIS", "lookc-wp22@student.tarc.edu.my", "019-7465433", "Penang", "Database Management, Data Analysis", "Diploma", 3.6, "Database Administrator Intern"));
        studentList.add(new Student("23WMR15273", "Daniel Tham Zhi Yang", "RIS", "danieltzy-wp22@student.tarc.edu.my", "019-7465434", "Selangor", "UI/UX Design, Digital Marketing", "Diploma", 3.8, "Mobile App Developer Intern"));
        studentList.add(new Student("23WMR15274", "Koh Wei Sheng", "RIS", "kohws-wp22@student.tarc.edu.my", "019-7465435", "Johor", "Software Testing, Troubleshooting", "Degree", 3.7, "Software Tester Intern"));

        studentList.add(new Student("23WMR18542", "Foo Wei Kang", "RMM", "foowk-wp22@student.tarc.edu.my", "014-6578234", "Perak", "Database Management, Troubleshooting", "Diploma", 3.6, "Network Engineer Intern"));
        studentList.add(new Student("25WMR19532", "Goh Zi Xuan", "RDS", "gohzx-wp22@student.tarc.edu.my", "018-9876543", "Penang", "Networking, Java", "Degree", 3.5, "IT Consultant Intern"));
        studentList.add(new Student("22WMR17645", "Amina Yusof", "RST", "aminay-wp22@student.tarc.edu.my", "013-7654321", "Kuala Lumpur", "AI, Machine Learning", "Degree", 3.7, "Robotics Intern"));
        studentList.add(new Student("23WMR19874", "James Low", "REI", "jameslow-wp22@student.tarc.edu.my", "014-8765432", "Selangor", "Data Analysis, HTML", "Degree", 3.6, "IT Project Manager Intern"));
        studentList.add(new Student("25WMR18976", "Khalish Haikal", "RMM", "khalishh-wp22@student.tarc.edu.my", "019-6748392", "Kelantan", "Database Management, AI", "Diploma", 3.9, "AI Developer Intern"));
        studentList.add(new Student("22WMR14325", "Nina Wong", "RIS", "ninawong-wp22@student.tarc.edu.my", "012-8765493", "Sarawak", "Java, Machine Learning", "Degree", 3.5, "IT Auditor Intern"));
        studentList.add(new Student("23WMR17684", "Kumaravel Raj", "RSD", "kumaravel-wp22@student.tarc.edu.my", "018-7654219", "Perak", "Networking, AI", "Diploma", 3.7, "AI Research Intern"));
        studentList.add(new Student("24WMR19432", "Chong Wei Ming", "RST", "chongwm-wp22@student.tarc.edu.my", "017-9876234", "Selangor", "Java, Data Analysis", "Diploma", 3.6, "Data Engineer Intern"));
        studentList.add(new Student("22WMR18943", "Rahul Dev", "RDS", "rahuld-wp22@student.tarc.edu.my", "013-8374926", "Kuala Lumpur", "HTML, Troubleshooting", "Degree", 3.5, "Web Security Intern"));
        studentList.add(new Student("23WMR19576", "Samantha Lau", "RMM", "samanthal-wp22@student.tarc.edu.my", "019-6748293", "Penang", "Machine Learning, Networking", "Diploma", 3.8, "Machine Learning Intern"));
        studentList.add(new Student("24WMR18954", "Naveen Kumar", "RSW", "naveenk-wp22@student.tarc.edu.my", "012-8974635", "Kelantan", "AI, Data Analysis", "Degree", 3.7, "AI Ethics Intern"));
        studentList.add(new Student("25WMR17294", "Melissa Chan", "RIS", "melissac-wp22@student.tarc.edu.my", "018-9274638", "Perak", "Database Management, Java", "Diploma", 3.6, "Database Administrator Intern"));
        studentList.add(new Student("22WMR16348", "Firdaus Ahmad", "RST", "firdausa-wp22@student.tarc.edu.my", "017-7342896", "Sarawak", "Troubleshooting, HTML", "Degree", 3.5, "Systems Engineer Intern"));
        studentList.add(new Student("23WMR19836", "Yvonne Teh", "RDS", "yvonneth-wp22@student.tarc.edu.my", "013-7645289", "Selangor", "Machine Learning, AI", "Diploma", 3.9, "AI Research Intern"));
        studentList.add(new Student("24WMR18976", "Zahid Ismail", "REI", "zahidi-wp22@student.tarc.edu.my", "016-7659234", "Kuala Lumpur", "Java, Data Analysis", "Degree", 3.5, "Embedded Systems Intern"));
        studentList.add(new Student("25WMR14253", "Vincent Chua", "RMM", "vincentc-wp22@student.tarc.edu.my", "019-7348926", "Penang", "Networking, Troubleshooting", "Degree", 3.8, "Network Administrator Intern"));
        studentList.add(new Student("22WMR17682", "Lina Noor", "RSW", "linan-wp22@student.tarc.edu.my", "012-7893456", "Kelantan", "HTML, Java", "Diploma", 3.7, "Web Developer Intern"));
        studentList.add(new Student("23WMR19342", "Wilson Tay", "RIS", "wilsont-wp22@student.tarc.edu.my", "014-7896234", "Johor", "AI, Penetration Testing", "Degree", 3.6, "Software Tester Intern"));
        studentList.add(new Student("24WMR18593", "Zara Syed", "RST", "zaras-wp22@student.tarc.edu.my", "017-6734892", "Perak", "Database Management, Machine Learning", "Diploma", 3.8, "Machine Learning Intern"));
        studentList.add(new Student("25WMR14832", "Nicolas Yap", "RDS", "nicolasy-wp22@student.tarc.edu.my", "013-9832647", "Selangor", "Java, Troubleshooting", "Degree", 3.7, "Embedded Systems Intern"));

        studentList.add(new Student("22WMR17286", "Elena Ho", "RMM", "elenah-wp22@student.tarc.edu.my", "018-7342986", "Kuala Lumpur", "Data Analysis, AI", "Diploma", 3.6, "Data Engineer Intern"));
        studentList.add(new Student("23WMR19872", "Hassan Ali", "REI", "hassana-wp22@student.tarc.edu.my", "016-8742936", "Penang", "Networking, Java", "Diploma", 3.5, "QA Engineer Intern"));
        studentList.add(new Student("23WMR19881", "Harith Iqbal", "RIS", "harithi-wp22@student.tarc.edu.my", "019-8372649", "Kuala Lumpur", "Cybersecurity, Networking", "Diploma", 3.6, "IT Support Intern"));
        studentList.add(new Student("23WMR19882", "Alia Rahman", "RMM", "aliar-wp22@student.tarc.edu.my", "018-7384926", "Selangor", "UI/UX Design, Technical Writing", "Degree", 3.8, "Technical Writer Intern"));
        studentList.add(new Student("23WMR19883", "Jason Low", "RST", "jasonl-wp22@student.tarc.edu.my", "012-9483627", "Penang", "Cloud Computing, DevOps", "Diploma", 3.7, "Cloud Engineer Intern"));
        studentList.add(new Student("23WMR19884", "Nisha Menon", "RDS", "nisham-wp22@student.tarc.edu.my", "017-2938465", "Johor", "AI, Data Analysis", "Diploma", 3.5, "Data Analyst Intern"));
        studentList.add(new Student("23WMR19885", "Samuel Tan", "REI", "samuelt-wp22@student.tarc.edu.my", "016-9273648", "Perak", "Game Development, Software Testing", "Degree", 3.6, "Game Designer Intern"));
        studentList.add(new Student("23WMR15256", "Darren Chung Wei Han", "RIS", "darrenc-wp22@student.tarc.edu.my", "019-7465417", "Kedah", "Java, Networking", "Degree", 3.6, "Systems Administrator Intern"));
        studentList.add(new Student("23WMR19887", "Kevin Chia", "RSW", "kevinc-wp22@student.tarc.edu.my", "014-8263947", "Kelantan", "Networking, Cloud Computing", "Diploma", 3.7, "Systems Analyst Intern"));
        studentList.add(new Student("23WMR19888", "Elena Wong", "RMM", "elenaw-wp22@student.tarc.edu.my", "019-2738465", "Kuala Lumpur", "Penetration Testing, Troubleshooting", "Degree", 3.8, "Software Tester Intern"));

        studentList.add(new Student("23WMR19889", "Arif Hassan", "RST", "arifh-wp22@student.tarc.edu.my", "012-7384926", "Selangor", "Database Management, AI", "Diploma", 3.6, "QA Engineer Intern"));
        studentList.add(new Student("23WMR19890", "Justin Wong", "RDS", "justinw-wp22@student.tarc.edu.my", "018-9473628", "Penang", "UI/UX Design, Digital Marketing", "Degree", 3.4, "Digital Marketing Intern"));
        studentList.add(new Student("23WMR19891", "Mikhail Raj", "REI", "mikhailr-wp22@student.tarc.edu.my", "017-2938465", "Johor", "Java, Machine Learning", "Degree", 3.7, "Software Architect Intern"));
        studentList.add(new Student("23WMR19894", "Rachel Ng", "RMM", "racheln-wp22@student.tarc.edu.my", "014-8263947", "Kelantan", "Penetration Testing, Troubleshooting", "Degree", 3.7, "Systems Engineer Intern"));
        studentList.add(new Student("23WMR19897", "Victor Goh", "REI", "victorg-wp22@student.tarc.edu.my", "018-9473628", "Penang", "UI/UX Design, Digital Marketing", "Diploma", 3.4, "UI/UX Designer Intern"));
        studentList.add(new Student("23WMR15257", "Tan Yi Feng", "RIS", "tanyf-wp22@student.tarc.edu.my", "019-7465418", "Johor", "Java, Software Testing", "Diploma", 3.7, "Software Engineer Intern"));
        studentList.add(new Student("23WMR15258", "Angela Yam Bao Hui", "RIS", "angelaybh-wp22@student.tarc.edu.my", "019-7465419", "Sarawak", "Database Management, Java, AI", "Degree", 3.9, "AI Ethics Intern"));
        studentList.add(new Student("23WMR15259", "Gan Xin Yi", "RIS", "ganxy-wp22@student.tarc.edu.my", "019-7465420", "Sabah", "Blockchain, Cloud Computing", "Diploma", 3.6, "Blockchain Developer Intern"));
        studentList.add(new Student("23WMR15260", "Foo Xin Mun", "RIS", "fooxm-wp22@student.tarc.edu.my", "019-7465421", "Negeri Sembilan", "Game Development, UI/UX Design", "Diploma", 3.6, "Game Developer Intern"));
        studentList.add(new Student("23WMR19899", "Yvonne Lee", "RSW", "yvonnel-wp22@student.tarc.edu.my", "016-9273648", "Perak", "Technical Writing, DevOps", "Diploma", 3.6, "DevOps Engineer Intern"));

        studentList.add(new Student("25WMR15254", "Zhang Wei", "RIS", "zhangwei-wp22@student.tarc.edu.my", "019-7465422", "Kuala Lumpur", "Cybersecurity, Cloud Computing", "Degree", 3.6, "Cloud Security Intern"));
        studentList.add(new Student("24WMR15256", "Alicia Tan", "RIS", "aliciatan-wp22@student.tarc.edu.my", "019-7465433", "Selangor", "AI, Data Analysis, Machine Learning", "Diploma", 3.8, "Machine Learning Intern"));
        studentList.add(new Student("25WMR15257", "Sarah Lim", "RDS", "sarahlim-wp22@student.tarc.edu.my", "019-7465455", "Perak", "Java, Software Testing, UI/UX Design", "Degree", 3.9, "UX Researcher Intern"));
        studentList.add(new Student("25WMR15258", "David Lee", "RST", "davidlee-wp22@student.tarc.edu.my", "019-7465466", "Johor", "Penetration Testing, Cybersecurity", "Degree", 3.7, "Network Security Intern"));
        studentList.add(new Student("25WMR15259", "Jessica Chong", "REI", "jessicachong-wp22@student.tarc.edu.my", "019-7465477", "Kuala Lumpur", "Blockchain, DevOps", "Diploma", 3.8, "Blockchain Developer Intern"));
        studentList.add(new Student("23WMR15280", "Arjun Kumar", "RSW", "arjunkumar-wp22@student.tarc.edu.my", "019-7465488", "Selangor", "Database Management, Cloud Computing", "Diploma", 3.6, "Database Administrator Intern"));
        studentList.add(new Student("23WMR15262", "Raymond Tan", "RMM", "raymondtan-wp22@student.tarc.edu.my", "019-7465500", "Johor", "Game Development, UI/UX Design", "Diploma", 3.7, "UI/UX Designer Intern"));
        studentList.add(new Student("23WMR15263", "Nadia Ismail", "RSD", "nadiaismail-wp22@student.tarc.edu.my", "019-7465511", "Kelantan", "Machine Learning, AI, Data Analysis", "Diploma", 3.9, "Data Analyst Intern"));
        studentList.add(new Student("25WMR15264", "Hafiz Rahman", "RST", "hafizrahman-wp22@student.tarc.edu.my", "019-7465522", "Sarawak", "Java, Networking", "Degree", 3.6, "Full Stack Developer Intern"));
        studentList.add(new Student("25WMR15265", "Mugilan", "RIS", "mugilan-wp22@student.tarc.edu.my", "019-7465499", "Nilai", "AI, Penetration Testing, Cybersecurity", "Degree", 3.85, "Software Tester Intern"));

        studentList.add(new Student("25WMR15266", "Samuel Goh", "RSW", "samuelgoh-wp22@student.tarc.edu.my", "019-7465544", "Selangor", "Data Analysis, Blockchain", "Diploma", 3.7, "Data Analyst Intern"));
        studentList.add(new Student("23WMR15261", "Tan Li Wei", "RIS", "tanlw-wp22@student.tarc.edu.my", "019-7465422", "Kuala Lumpur", "Data Analysis, Database Management", "Diploma", 3.8, "Data Analyst Intern"));
        studentList.add(new Student("23WMR15220", "Ng Ru Miau", "RIS", "ngrm-wp22@student.tarc.edu.my", "019-7465423", "Penang", "HTML, Digital Marketing", "Degree", 3.7, "Digital Marketing Intern"));
        studentList.add(new Student("23WMR15221", "Ho Yee Xuan", "RIS", "hoyx-wp22@student.tarc.edu.my", "019-7465424", "Selangor", "AI, Penetration Testing", "Degree", 3.9, "Full Stack Developer Intern"));
        studentList.add(new Student("23WMR15264", "Chia Xin Yi", "RIS", "chiaxy-wp22@student.tarc.edu.my", "019-7465425", "Johor", "Networking, Troubleshooting", "Diploma", 3.7, "Network Engineer Intern"));
        studentList.add(new Student("25WMR15269", "Karen Teh", "RDS", "karenteh-wp22@student.tarc.edu.my", "019-7465577", "Sarawak", "Game Development, Digital Marketing", "Diploma", 3.5, "Game Developer Intern"));
        studentList.add(new Student("25WMR15271", "Sophia Tan", "REI", "sophiatan-wp22@student.tarc.edu.my", "019-7465599", "Selangor", "Networking, Cybersecurity", "Diploma", 3.6, "Cybersecurity Intern"));
        studentList.add(new Student("25WMR15272", "Amir Hadi", "RSW", "amirhadi-wp22@student.tarc.edu.my", "019-7465600", "Kelantan", "Machine Learning, AR/VR Development, Troubleshooting", "Degree", 3.9, "AR/VR Developer Intern"));
        studentList.add(new Student("23WMR15265", "Thavee Chaiphakdi", "RIS", "thaveec-wp22@student.tarc.edu.my", "019-7465426", "Melaka", "DevOps, Software Testing", "Diploma", 3.6, "DevSecOps Intern"));
        studentList.add(new Student("23WMR15266", "Wong Han Zhen", "RIS", "wonghz-wp22@student.tarc.edu.my", "019-7465427", "Kedah", "Cybersecurity, Blockchain", "Diploma", 3.8, "Cybersecurity Intern"));

        studentList.add(new Student("24WMR16587", "Angeline Tan", "RMM", "angelinet-wp22@student.tarc.edu.my", "017-7283946", "Selangor", "Database Management, Cloud Computing", "Degree", 3.8, "Cloud Security Intern"));
        studentList.add(new Student("23WMR18932", "Harith Hakim", "RST", "harithh-wp22@student.tarc.edu.my", "018-7364952", "Penang", "Penetration Testing, Cybersecurity", "Degree", 3.7, "Network Security Intern"));
        studentList.add(new Student("25WMR17289", "Stephanie Wong", "RDS", "stephaniew-wp22@student.tarc.edu.my", "012-8392745", "Johor", "UI/UX Design, Technical Writing", "Degree", 3.5, "Technical Writer Intern"));
        studentList.add(new Student("24WMR19345", "Azhar Farid", "REI", "azharf-wp22@student.tarc.edu.my", "019-7346582", "Kelantan", "DevOps, Software Testing", "Degree", 3.9, "Software Tester Intern"));
        studentList.add(new Student("23WMR16879", "Joseph Chia", "RIS", "josephc-wp22@student.tarc.edu.my", "013-9876542", "Sarawak", "Data Analysis, Blockchain", "Degree", 3.6, "Big Data Intern"));
        studentList.add(new Student("24WMR18376", "Vanessa Chong", "RST", "vanessac-wp22@student.tarc.edu.my", "014-7892346", "Johor", "Troubleshooting, Digital Marketing", "Degree", 3.5, "Digital Marketing Intern"));
        studentList.add(new Student("25WMR13298", "Arvind Kumar", "RDS", "arvindk-wp22@student.tarc.edu.my", "018-7932465", "Penang", "Cybersecurity, DevOps", "Diploma", 3.9, "DevOps Engineer Intern"));
        studentList.add(new Student("23WMR18765", "Natalie Ho", "REI", "natalieh-wp22@student.tarc.edu.my", "012-8754392", "Kelantan", "HTML, UI/UX Design", "Diploma", 3.4, "UI Developer Intern"));
        studentList.add(new Student("22WMR15679", "Darren Lau", "RSW", "darrenl-wp22@student.tarc.edu.my", "019-7349268", "Perak", "AR/VR Development, Cybersecurity", "Degree", 3.8, "AR/VR Developer Intern"));
        studentList.add(new Student("25WMR15678", "Zhen Wei", "RSW", "zhenwei-wp22@student.tarc.edu.my", "012-7639842", "Perak", "Data Analysis, Digital Marketing", "Degree", 3.5, "Digital Marketing Intern"));

        return studentList;
    }
}