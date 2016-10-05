package q4;

import java.util.Scanner;

/**
 * <p>A class that prompt user and read in an integer number and calculate
 * all the prime numbers between the range of 0 and the number user input.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class Primes {
    /**The upper boundary to calculate prime numbers.*/
    private int upperBound;
    
    /**An array of boolean variables. The element value "true" indicating the 
     * corresponding index is a prime number.*/
    private boolean[] primes;
    
    /**Constructor with one integer parameter supplied. 
     * @param upperBound The upper boundary of primes to calculate
     */
    Primes(int upperBound) {
        //Throw exception if the upper bound is invalid (less than 2).
        if (upperBound < 2) {
            throw new IllegalArgumentException("Invalid upper bound. "
                    + "Upper bound should be greater than or equal to 2");
        }
        this.upperBound = upperBound;
        calculatePrimes();
    }
    
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        int upperBound = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("This program uses the sieve of Eratosthenese to "
                + "determine which numbers are prime.");
        //Main loop of the application, ends when user enter "n".
        String toContinue = "y";
        do {
            //Handle exception thrown by Primes constructor
            try {
                //Prompt user and read in an upper bound
                System.out.println("Please enter an upper bound:");
                upperBound = getInputInt(scan);
                
                //Instantiate the Primes object with the upper bound
                Primes primes = new Primes(upperBound);
                
                //Print the count of primes in the given range
                int count = primes.countPrimes();
                System.out.println("There " 
                                   + ((count == 1) ? "is " : "are ")
                                   + count
                                   + ((count == 1) ? " prime:" : " primes:"));
                System.out.println("The prime"
                                   + ((count == 1) ? " number" : " numbers")
                                   + " beteween 0 and "
                                   + upperBound
                                   + ((count == 1) ? " is:" : " are:"));
                //Print all the prime numbers in the range
                primes.printPrimes();
                
                //Check whether a number is a prime
                primes.queryPrime(scan);
            } catch (IllegalArgumentException e) {
                //Handling the exception
                System.out.println(e.getMessage());
            }
            System.out.println("Try other upper bound again?(y/n)");
            toContinue = scan.nextLine();
        } while(!toContinue.equalsIgnoreCase("n"));
        
        //Close the scanner
        scan.close();
        
        //Thank you and goodbye message.
        System.out.println("Thank you for using the prime calculator. Bye!");
        System.out.println();
        
        System.out.println("Question four was called and ran sucessfully.");
    }
    
    /**
     * A method to get an integer from input, handling non-integer input.
     * @param scan A Scanner retrieved from caller.
     * @return An integer
     */
    public static int getInputInt(Scanner scan) {
        int number = 0;
        //Only execute when input is integer
        if (scan.hasNextInt()) {
        number = scan.nextInt();    
        }
        //Set the scanner to a new line and ignore the rest.
        scan.nextLine();
        return number;
    }
    
    /**A method that uses the sieve of Eratosthenese to determine 
     * which numbers are prime.
     */
    private void calculatePrimes() {
        //Create a new array with size of upperBound + 1
        primes = new boolean[upperBound + 1];
        
        //Initial all the element to be true except for 0, 1, 2
        for (int i = 0; i < upperBound + 1; i++) {
            if (i < 2) {
                primes[i] = false;
            } else {
                primes[i] = true; 
            }
        }
        
        ///////////////////////////////////////////////////////////////////////
        //Answer the question in requirement:                                //
        //                                                                   //
        //Since 2 is a prime number, 2 * (upperBound/2) = upperBound, and    //
        //upperBound is the index of the last element to be calculated. Each // 
        //number in the second half must have at least one number in the     //
        //first half as its factor if it is not a prime number. This can     //
        //guarantee all the numbers in the array have been checked correctly.//
        ///////////////////////////////////////////////////////////////////////
        
        //Use the sieve of Eratosthenese to determine
        int halfWay = upperBound / 2;
        
        for (int i = 0; i <= halfWay; i++) {
            if (primes[i]) {
                int j = 2;
                do {
                    primes[i * j] = false;
                    j++;                   
                } while (i * j <= upperBound);
            }
        }        
    }
    
    /**A method that prints the prime numbers between 0 to the upper bound.*/
    public void printPrimes() {
        //Each line print 10 numbers
        int count = 0;
        final int numberPerLine = 10;
        
        //Traverse the array and print the prime numbers
        for (int i = 0; i <= upperBound; i++) {
            if (primes[i]) {
                System.out.print(i + " ");
                                
                //Begin a new line every 10 numbers
                count++;
                if ((count % numberPerLine) == 0) {
                    System.out.println();
                }                 
            }            
        }
        //An empty line make output looks clear
        System.out.println();
    }
    
    /**A method that counts the number of primes in the range of 
     * [0, upperBound].
     * @return The number of primes in the given range
     */
    public int countPrimes() {
        int primeCount = 0;
        for (int i = 0; i <= upperBound; i++) {
            if (primes[i]) {
                primeCount++;
            }
        }
        return primeCount;
    }
    
    /**A method that can be use to query whether an integer is a prime number.
     * @param x The number to be queried.
     * @return True if the number is a prime
     */
    public boolean isPrime(int x) {
        //Throw exception if the querying value out of range
        if (x < 0 || x > upperBound) {
            throw new IllegalArgumentException("Invalid number! Must be "
                    + "positive number and less than or equal to " 
                    + upperBound);
        }
        //The value of the element with index x indicates it is a prime or not.
        return primes[x];
    }
    
    /**
     * A method that let user keep on input numbers and check whether they are
     * prime numbers.
     * @param scan A scanner to get user input, passed in from the caller.
     */
    public void queryPrime(Scanner scan) {
        String toQuery = "y";
        //Loop until the user enter "n"
        do {
            System.out.println("Please enter a number to check if it is"
                    + " a prime number (0~"
                    + upperBound
                    + "):");
            int x = getInputInt(scan);
            
            //Handle the exception thrown by isPrime
            try {
                //Call the isPrime method to check whether the number is prime
                System.out.println(x 
                    + (isPrime(x) ? " is" : " is not")
                    + " a prime number.");                        
            } catch (IllegalArgumentException e) {
                //Print the errer message
                System.out.println(e.getMessage());
            }                   
            System.out.println();
            System.out.println("Check anothoer number?(y/n)");
            toQuery = scan.nextLine();
        } while(!toQuery.equalsIgnoreCase("n"));

    }
};
