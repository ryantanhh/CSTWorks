package q1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * <p>A class that gets the length of three sides of a triangle from user input,
 * Compute the perimeter and area of the triangle and print the values to three 
 * decimal places.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class TriangleArea {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        final double half = 0.5;
        
        //Length of three sides;
        double a;
        double b;
        double c;
        
        //Perimeter
        double perimeter;
        
        //Area
        double area;
        
        Scanner scan = new Scanner(System.in);
        
        //Prompt the user to enter the length of three sides of the triangle
        System.out.println("Please enter the size of a triangle.");
        
        //Get the length of sides from user input.
        System.out.println("Please input the length of side a: ");
        a = scan.nextDouble();
        System.out.println("Please input the length of side b: ");
        b = scan.nextDouble();
        System.out.println("Please input the length of side b: ");
        c = scan.nextDouble();
                 
        //Compute the perimeter
        perimeter = a + b + c;
        
        //Compute the area
        double s = half * perimeter;
        area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        
        //Print the result
        DecimalFormat decimal = new DecimalFormat("#.###");
        System.out.println();
        System.out.println("The sides of the triangle: a=" 
                            + a
                            + ", b="
                            + b
                            + ", c="
                            + c);
        System.out.println("The perimeter: " + decimal.format(perimeter));
        System.out.println("The area: " + decimal.format(area));
        
        scan.close();
        System.out.println("Question one was called and ran sucessfully.");
    }

};
