/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectperformance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Michiel
 */
public class ProjectPerformance {
    /**
     * @param args the command line arguments
     */
    private Connection conn;
    private static long endTime;
    private static long startTime;
    
    // Create random File Name
    private String filename = Long.toHexString(Double.doubleToLongBits(Math.random())) + ".txt";
    
    public static void main(String[] args) {
        // Timing Start
        startTime = System.nanoTime();
        
        ProjectPerformance proj = new ProjectPerformance();
        proj.start();
        
        // Timing End
        endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000);  //divided by 1000000 to get milliseconds.
        System.out.println(duration + " millis");
        
    } //End Main method

    public void start() {
        conn = new Connection();
        
        // Get data of all types of players
        // Pass data to create info papers for players
        createInfopaper(conn.getData("kid"));
        
        createInfopaper(conn.getData("adolescent"));
        
        createInfopaper(conn.getData("adult"));
        
        createInfopaper(conn.getData("senior"));
        
    } //End start method
    
    public void createInfopaper(ArrayList<Person> people) {
        // Write info papers to database and to file
        for(Person person: people) {
            conn.writeInfopaper(person);       
        }
        
        // Write to file which info papers are created yet
        // Names need to be grouped by type of player (kid, adolescent, adult, senior)
        writeToFile(people);
        
    } // End createInfoPaper method
    
    public void writeToFile(ArrayList<Person> people) {
        try(FileWriter fw = new FileWriter(filename, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            for(Person person: people) {
                out.println(person.getName());
            }
        } catch (IOException e) {
            //exception handling 
            e.printStackTrace();
        }
    }
} //End ProjectPerformance Class
