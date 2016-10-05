package q1;

import java.io.File;
import static java.io.File.separator;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * <p>A class that read floating point numbers from a file and calculate the
 * the mean the mean and standard deviation .</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class Statistics {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        //Assume there will be no more than 50 input values
        final int maxLength = 50;
        
        //Initial array to store data read from the input file
        double [] data = new double[maxLength];
        
        //Track hom many numbers are read in.
        int length = 0;
        Scanner scan = new Scanner(System.in);      
        Scanner fileScan = getDataFile(scan);
        
        
        //Read data from file and store the data into the initial array.
        while (fileScan != null && fileScan.hasNext()) {
            String str = fileScan.next();
            data[length] = Double.parseDouble(str);
            length++;
        }
        
        //Shrink the size of the array
        double[] temp = new double[length];
        for (int i = 0; i < length; i++) {
            temp[i] = data[i];
        }
        data = temp;
        
        //Calculate the sum of the input numbers
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
            sum += data[i];
        }
        
        //Calculate the mean of the input numbers
        double mean = sum / data.length;
        
        //Calculate the standard deviation of the input numbers
        double sumDevSquare = 0;
        for (int i = 0; i < data.length; i++) {
            sumDevSquare += Math.pow((data[i] - mean), 2);
        }
        double standardDeviation = Math.sqrt(sumDevSquare / (data.length - 1));
        
        //Show the result
        DecimalFormat fmt = new DecimalFormat("0.00");
        System.out.println();
        System.out.println("The mean of the numbers: "
                          + fmt.format(mean));
        System.out.println("The deviation of the numbers: " 
                          + fmt.format(standardDeviation));
        
        if (fileScan != null) {
         fileScan.close();   
        }
        scan.close();
        System.out.println("Question one was called and ran sucessfully.");
    }
    
    /**A method that generate a scanner of a file.
     * @param scan A scanner that gets user input
     * @return A scanner on the data file.
     */
    public static Scanner getDataFile(Scanner scan) {
        File file = null;
        Scanner fileScan = null;
        //search data file in current class path
        try {
            file = new File(Statistics.class.getResource("data.txt").getPath());
            fileScan = new Scanner(file);
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println("No data file in current path, try source "
                    + "path...");
        }
        
        //search data file in source code directory
        if (fileScan == null) {
            try {
                file = new File("src" + separator + "q1" + separator 
                        + "data.txt");
                fileScan = new Scanner(file);
            } catch (NullPointerException | FileNotFoundException e) {
                System.out.println("No data found in source path.");
            }
        }
        
        //search data file in source code directory
        if (fileScan == null) {
            try {
                file = new File("q1" + separator + "data.txt");
                fileScan = new Scanner(file);
            } catch (NullPointerException | FileNotFoundException e) {
                System.out.println("No data found in package directory.");
            }
        }
        
        if (fileScan == null) {
            try {
                file = new File("q1" + separator 
                        + "data.txt");
                fileScan = new Scanner(file);
            } catch (NullPointerException | FileNotFoundException e) {
                
            }
        }
        
        while (fileScan == null) {
            System.out.println("Pleas input the full path of data.txt"
                    + "(\"q\" to exit):");
            String path = scan.nextLine();
            if (path.equalsIgnoreCase("q")) {
                break;
                }
            try {
            file = new File(path);
            fileScan = new Scanner(file);
            } catch (NullPointerException | FileNotFoundException e) {  
                System.out.println("Invalid data file!");
            }
        }
        
        
        return fileScan;
    }
};
