/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.MatchingEngine;
import adt.LinkedList;
import entity.MatchResult;
/**
 *
 * @author ROG G14
 * User interface for the Matching Engine.
 */
public class MatchingEngineUI {
    public static void runMatchingEngine(MatchingEngine matchingEngine) {
        System.out.println("\n--- Matching Engine ---");
        LinkedList<MatchResult> matches = matchingEngine.findMatches();

        if (matches.getNumberOfEntries() > 0) {
            System.out.println("Match Results:");
            for (int i = 1; i <= matches.getNumberOfEntries(); i++) {
                System.out.println(matches.getEntry(i));
            }
        } else {
            System.out.println("No suitable matches found.");
        }
    }
}
