/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.ListInterface;
import adt.SortedList;
import entity.Interview;
import entity.InterviewReport;
import entity.InterviewSchedule;
import entity.Match;
import entity.Recruitment;
import entity.Student;

/**
 *
 * @author rscma
 */
public final class InterviewInitializer {

    public static ListInterface<Interview> interviewList = new SortedList<>();
    public static ListInterface<InterviewSchedule> scheduleList = new SortedList<>();
    public static ListInterface<Recruitment> recruitmentList = new SortedList<>();
    public static ListInterface<Match> matchList = new SortedList<>();
    public static ListInterface<Student> studentList = new SortedList<>(); // List to hold InterviewReports

    public InterviewInitializer() {
        initialize();
    }

    public void initialize() {

        scheduleList.add(new InterviewSchedule("S1001", "Kuala Lumpur", "2025-05-01", "13:29", "M1015"));
        scheduleList.add(new InterviewSchedule("S1002", "a", "2025-05-02", "13:30", "M1010"));
        scheduleList.add(new InterviewSchedule("S1003", "b", "2025-05-03", "13:31", "M1014"));
        scheduleList.add(new InterviewSchedule("S1004", "c", "2025-05-04", "13:32", "M1004"));
        scheduleList.add(new InterviewSchedule("S1005", "d", "2025-05-05", "13:33", "M1009"));
        scheduleList.add(new InterviewSchedule("S1006", "e", "2025-05-06", "13:34", "M1002"));
        scheduleList.add(new InterviewSchedule("S1007", "f", "2025-05-07", "13:35", "M1012"));
        scheduleList.add(new InterviewSchedule("S1008", "g", "2025-05-08", "13:36", "M1017"));
        scheduleList.add(new InterviewSchedule("S1009", "h", "2025-05-09", "13:37", "M1006"));
        scheduleList.add(new InterviewSchedule("S1010", "i", "2025-05-10", "13:38", "M1007"));
        scheduleList.add(new InterviewSchedule("S1011", "k", "2025-05-11", "13:39", "M1001"));

        String matchID1 = scheduleList.get(0).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID2 = scheduleList.get(1).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID3 = scheduleList.get(2).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID4 = scheduleList.get(3).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID5 = scheduleList.get(4).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID6 = scheduleList.get(5).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID7 = scheduleList.get(6).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID8 = scheduleList.get(7).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID9 = scheduleList.get(8).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID10 = scheduleList.get(9).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        String matchID11 = scheduleList.get(10).getMatchID(); // Assuming getMatchID() is a method in InterviewSchedule
        // Create Interviews and associate them with schedules
        interviewList.add(new Interview("I1001", scheduleList.get(0), matchID1, "Ali", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1002", scheduleList.get(1), matchID2, "A", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1003", scheduleList.get(2), matchID3, "B", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1004", scheduleList.get(3), matchID4, "C", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1005", scheduleList.get(4), matchID5, "D", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1006", scheduleList.get(5), matchID6, "e", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1007", scheduleList.get(6), matchID7, "f", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1008", scheduleList.get(7), matchID8, "g", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1009", scheduleList.get(8), matchID9, "h", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1010", scheduleList.get(9), matchID10, "i", "Pending", "Great communication skills and confidence"));
        interviewList.add(new Interview("I1011", scheduleList.get(10),matchID11, "j", "Pending", "Great communication skills and confidence"));

        // Add a Recruitment object to the list
        recruitmentList.add(new Recruitment("R1001", "Fang Wei Xiang", 85.5, "AI", "IT department", "2025-03-01"));
    }

}
