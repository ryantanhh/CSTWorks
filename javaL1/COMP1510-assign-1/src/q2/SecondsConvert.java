package q2;

import java.util.Scanner;

/**
 * <p>A simple time unit converting program that gets user input 
 * of a time duration in hours, minutes and seconds, and converts 
 * it to seconds.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class SecondsConvert {

    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */

    public static void main(String[] args) {
        // your code will go here!!!
        //Define final variables for converting time units
        final int minutesPerHour = 60;
        final int secondsPerMinute = 60;
        
        //Define variables to keep input and converted time values
        int hours;
        int minutes;
        int seconds;
        int timeInSeconds;
        
        //Prompt user to input hours, minutes and seconds
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter an integer for hours:");
        hours = scan.nextInt();
        System.out.print("Please enter an integer for minutes (0-59):");
        minutes = scan.nextInt();
        System.out.print("Please enter an integer for seconds (0-59):");
        seconds = scan.nextInt();
        
        //Convert to seconds
        timeInSeconds = (hours * minutesPerHour + minutes) 
                        * secondsPerMinute 
                        + seconds;
        
        //Print the result
        System.out.println(hours + " hours "
                            + minutes + " minutes "
                            + seconds + " seconds = "
                            + timeInSeconds + "seconds.");
        
        System.out.println("Question two was called and ran sucessfully.");
        
        scan.close();

    }

};
