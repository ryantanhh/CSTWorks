package q1;

import java.util.Scanner;

/**
 * <p>A class to check whether a string is a palindrome, ignoring spaces
 * punctuation marks and cases.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class PalindromeTester {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        String str;
        String toContinue = "y";
        int left;
        int right;
        Scanner scan = new Scanner(System.in);
        
        //Main loop of the application, ends when user enter "n".
        while (!toContinue.equalsIgnoreCase("n")) { 
            System.out.println("Enter a potential palindrome:");
            str = scan.nextLine();

            left = -1;
            right = str.length();
            do {
                left++;
                right--;
                //Skip unconsidered characters in the left cursor
                while (isPunctuation(str.charAt(left))) {
                    left++;
                }
              //Skip unconsidered characters in the right cursor
                while (isPunctuation(str.charAt(right))) {
                    right--;
                }
            } while (left < right 
                   && Character.toLowerCase(str.charAt(left))
                   == Character.toLowerCase(str.charAt(right))); 

            System.out.println();
            
            //Print the result.
            if (left < right) {
                System.out.println("That string is NOT a palindrome.");
            } else {
                System.out.println("That string IS a palindrome.");
            }

            System.out.println();
            System.out.print("Test another palindrome (y/n)? ");
            toContinue = scan.nextLine();
        }
        
        //Close the scanner
        scan.close();
        
        //Thank you and goodbye message.
        System.out.println("Thank you for using the PalindromeTester. Bye!");
        System.out.println();
        System.out.println("Question one was called and ran sucessfully.");
    }
        
    /**
     * A static method to check whether a character is a punctuation mark.
     * @param ch A character to be checked.
     * @return True if the character is not 'a'~'z', 'A'~'Z' or number 
     * '0' ~ '9'.
     */
    public static boolean isPunctuation(char ch) {
        return (ch < '0' 
                || ((ch > '9') && (ch < 'A')) 
                || ((ch > 'Z') && (ch < 'a')) 
                || ch > 'z');
    }
};
