package q2;

import java.util.Scanner;
import java.text.DecimalFormat;
/**
 * <p>A class gets the radius and height from user input and compute the surface
 * area and volume.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class CylinderStats {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        final double circleAreaConstant = 2;
        
        //Variable to store radius, height, surface area and volume
        double radius;
        double height;
        double surfaceArea;
        double volume;
        
        //Prompt user to enter radius and height and validate the input.
        Scanner scan = new Scanner(System.in);
        
        do {
            System.out.println("Please Enter the radius of the cylinder: ");
            radius = scan.nextDouble();
            if (radius <= 0) {
                System.out.println("Invalid input! " 
                                + "Radius must be greater than 0.");
            }
        } while(radius <= 0);
        
        do {
            System.out.println("Please Enter the height of the cylinder: ");
            height = scan.nextDouble();
            if (height <= 0) {
                System.out.println("Invalid input! " 
                                + "Radius must be greater than 0.");
            }
        } while(height <= 0);
        
        //Compute the surface area
        surfaceArea = circleAreaConstant * Math.PI * radius * (radius + height);
        
        //Compute the volume
        volume = Math.PI * radius * radius * height;
        
        //Print the result
        System.out.println();
        
        DecimalFormat decimal = new DecimalFormat("#.####");
        System.out.println("The size of the cylinder: radius="
                            + decimal.format(radius)
                            + ", height="
                            + decimal.format(height));
        
        System.out.println("Surface area: " + decimal.format(surfaceArea));
        System.out.println("Volume: " + decimal.format(volume));
        scan.close();
        System.out.println("Question two was called and ran sucessfully.");
    }

};
