package q4;
import java.util.Scanner;

/**
 * <p>A class that prompts users to enter radius and height of a cylinder,
 * Calculates and displays the volume of the cylinder.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class Cylinder {
    /**
     * <p>Main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //Define r as the radius of the cylinder
        double r;
        
        //Define h as the height of the cylinder
        double h;
        
        //Define v as the volume of the cylinder
        double v;
        
        //Create a Scanner object to get user input
        Scanner scan = new Scanner(System.in);
        
        //Prompt user to enter radius
        System.out.print("Please enter the radius of the cylinder: ");
        r = scan.nextDouble();
        
        //Prompt user to enter height
        System.out.print("Please enter the height of the cylinder: ");
        h = scan.nextDouble();
        
        //Calculate the volume
        v = Math.PI * r * r * h;
        
        //Display the result
        System.out.println("The volume of a cylinder with radius="
                            + r
                            + " and height="
                            + h
                            + " is: "
                            + v
                            + ".");
        System.out.println("Question four was called and ran sucessfully.");
   
        scan.close();        
    }

};
