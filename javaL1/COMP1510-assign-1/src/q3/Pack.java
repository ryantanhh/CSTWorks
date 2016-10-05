package q3;

/**
 * <p>A class converting 56-based numbers from and to decimal values. 
 * The symbols of the 56-based number are denoted by ASCII code from 0 (space) 
 * to 55 (').</p>
 *
 * @author Hai Hua,Tan
 * @version 1.0
 */
public class Pack {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // your code will go here!!!
        //Define five char variable to store original characters.        
        final char c5 = 'I';
        final char c4 = 'F';
        final char c3 = 'E';
        final char c2 = 'B';
        final char c1 = 'A';
        
        //Define a final variable to store the base and assign it the value 56
        final int base = 56;
        
        //Define a variable to store the encoded value
        int code;
        
        //Define 5 char variable to store decoded value
        char d5;
        char d4;
        char d3;
        char d2;
        char d1;
        
        //Display the original characters
        System.out.println("Original: " + c5 + c4 + c3 + c2 + c1);
        
        //Encode
        code = (c5 - 'A' + 1) * base * base * base * base 
             + (c4 - 'A' + 1) * base * base * base
             + (c3 - 'A' + 1) * base * base
             + (c2 - 'A' + 1) * base
             + (c1 - 'A' + 1);
        
        //Display the encoded result
        System.out.println("Encoded: " + code);
        
        
        //Decode
        d1 = (char) (code % base - 1 + 'A');
        code /= base;
        
        d2 = (char) (code % base - 1 + 'A');
        code /= base;
        
        d3 = (char) (code % base - 1 + 'A');
        code /= base;
        
        d4 = (char) (code % base - 1 + 'A');
        code /= base;
        
        d5 = (char) (code % base - 1 + 'A');
        
        //Display the decoded result
        System.out.println("Decoded: " + d5 + d4 + d3 + d2 + d1);
        
        System.out.println("Question three was called and ran sucessfully.");
    }
};
