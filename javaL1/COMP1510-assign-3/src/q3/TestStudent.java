package q3;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

/**
 * <p>A class to test the new features of Student class (version 1.1).
 * The tests will run through:
 * <ol>
 * <li>Constructor with all arguments supplied</li>
 * <li>Constructor with zero argument</li>
 * <li>setTestScore method</li>
 * <li>getTestScore method</li>
 * <li>average method</li>
 * <li>toString method</li> 
 * </ol>
 * </p>
 *
 * @author Hai Hua, Tan
 * @version 1.1
 */
public class TestStudent {
    /**Count for all test cases.*/
    private int casesCount;
    /**Count for passed cases.*/
    private int passedCount;
    /**Count for failed cases.*/
    private int failedCount;
    /**TestData reference to get access to test data.*/
    private TestData testData;
    
    /**Constructor of the TestStudent class.
     */
    TestStudent() {
        casesCount = 0;
        passedCount = 0;
        failedCount = 0;
        testData = new TestData();
    }
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //Create an object of TestStudent to control work flow.
        TestStudent test = new TestStudent();
        
        //Create a scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        //Call the test case methods to do the test.
        test.printHeader();
        System.out.println("Auto test started...");
        test.testConstructorsWithFullArgs();
        test.testConstructorWithZeroArg();
        test.testGetSetScore();
        test.testAverage();
        test.printSeparator();
        System.out.println();
        System.out.println("Auto test finished.");
        test.printSeparator();
        System.out.println("Total\tPassed\tFailed");
        System.out.println(test.passedCount + test.failedCount + "\t"
                         + test.passedCount + "\t"
                         + test.failedCount);
        test.printFooter();
        
        System.out.println("\nManual test begin ...");
        test.manualTestConstructor(scan);
        test.manualTestSetGetScore(scan);
        System.out.println("Question three was called and ran sucessfully.");
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //Methods to create test cases by getting testing data for TestData class//
    //and call try* method to test the specific method of the Student class. //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * A method retrieve data from TestData to populate test cases and call the 
     * actual test methods (the try* method)to run the test cases.
     */
    public void testConstructorsWithFullArgs() {
        TestConstructorData[] data = testData.getTestConstructorData();
        for (int i = 0; i < data.length; i++) {
            casesCount++;
            printSeparator();
            System.out.println("Case " + casesCount + ": " 
                              + data[i].getCaseDescription());
            boolean testResult = tryConstructorWithArgs(
                    data[i].getFirstName(),
                    data[i].getLastName(),
                    data[i].getHomeAddress(),
                    data[i].getSchoolAddress(),
                    data[i].getTest1Score(),
                    data[i].getTest2Score(),
                    data[i].getTest3Score()
                    );
            if (testResult == data[i].getExpectedResult()) {
                System.out.println("Test Passed.");
                passedCount++;
            } else {
                System.out.println("Test Failed!");
                failedCount++;
            }
        }
    }
    
    
    /**
     * A method to test the Student constructor with zero argument. 
     */
    public void testConstructorWithZeroArg() {
        boolean expectedResult;
        boolean testResult;
        
        String result;
        
        printSeparator();
        System.out.println("Case " + casesCount 
                + ": Test constructo with zero argument.");
        casesCount++;
        testResult = tryConstructorWithZeroArg();
        
        expectedResult = true;
        if (expectedResult == testResult) {
            passedCount++;
            result = "Passed.";
        } else {
            failedCount++;
            result = "Failed!";
        }
        System.out.println("Test Result: " + result);   
    }
    
    /**
     * A method retrieve data from TestData to populate test cases and call the 
     * actual test methods (the try* method)to run the test cases. The testing 
     * targets are getting and setting test score methods.
     */
    public void testGetSetScore() {
        TestGetSetScoreData[] data = testData.getTestGetSetScoreData();
        for (int i = 0; i < data.length; i++) {
            casesCount++;
            printSeparator();
            System.out.println("Case " + casesCount + ": " 
                              + data[i].getCaseDescription());
            boolean testResult = tryGetSetTestScore(
                    data[i].getTestNum(),
                    data[i].getTestScore()
                    );
            if (testResult == data[i].getExpectedResult()) {
                System.out.println("Test Passed.");
                passedCount++;
            } else {
                System.out.println("Test Failed!");
                failedCount++;
            }
        }
    }

    /**
     * A test cast to test set and get the score of a test number less 
     * than lower boundary.
     */
    public void testAverage() {
        Random generator = new Random();
        boolean expectedResult;
        boolean testResult;
        double score1 = Math.round(Student.MAX_SCORE * generator.nextDouble());
        double score2 = Math.round(Student.MAX_SCORE * generator.nextDouble());
        double score3 = Math.round(Student.MAX_SCORE * generator.nextDouble());
        String result;
        
        casesCount++;
        
        printSeparator();
        System.out.println("Case " + casesCount + ": " 
                + "Test average method");

        testResult = tryAverage(score1, score2, score3);      
        expectedResult = true;
        if (expectedResult == testResult) {
            passedCount++;
            result = "Passed.";
        } else {
            failedCount++;
            result = "Failed!";
        }
        System.out.println("Test Result: " + result);           
    }


    
    ///////////////////////////////////////////////////////////////////////////
    //The actual testing methods called by test case methods to test the     //
    //target class methods with data transfered from test case methods.      //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * A method that accepts parameters from test case methods and test the 
     * constructor with the given data. Exception handling is included.
     * @param fisrtName The string of first name
     * @param lastName The string of last name
     * @param home An Address object storing home address
     * @param school An Address object storing school address
     * @param test1Score Score of test 1
     * @param test2Score Score of test 2
     * @param test3Score Score of test 3
     * @return True if the student object created successfully. 
     */
    private boolean tryConstructorWithArgs(String fisrtName, 
                                           String lastName, 
                                           Address home, 
                                           Address school, 
                                           double test1Score, 
                                           double test2Score, 
                                           double test3Score) {
        Student student = null;
        System.out.println("Result:");
        try {
            student = new Student(fisrtName, lastName, home, school, 
                                test1Score, test2Score, test3Score);
            System.out.println(student);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return student != null;
    }
    
    /**
     * A method to test whether the zero-argument constructor of class Student
     * work properly. Exception handling is also included.
     * @return True if the Student object is created successfully
     */
    public boolean tryConstructorWithZeroArg() {
        Student student = null;

            student = new Student();
            System.out.println(student);
        return student != null;
    } 
    
    
    /**
     * A method that accepts parameters from test case method and test the 
     * Student class with the given data.
     * @param num Test number
     * @param score Score of the test with given number
     * @return True if the value retrieved by getter equal to the set value.
     */
    public boolean tryGetSetTestScore(int num, double score) {
        final double tolerent = 0.01;
        Student student = new Student();
        System.out.println("Result:");
        try {
            student.setTestScore(num, score);
            System.out.println("Score " 
                                + num 
                                + ": " 
                                + student.getTestScore(num));
            return student.getTestScore(num) - score < tolerent;            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * A method that accepts parameters from the test case method and test
     * the Student's average method with the given data.
     * @param score1 Score of test 1
     * @param score2 Score of test 2
     * @param score3 Score of test 3
     * @return True if the value retrieved by getter equal to the set value.
     */
    private boolean tryAverage(
            double score1, double score2, double score3) {
        DecimalFormat fmt = new DecimalFormat("0.0");
        final int testNum1 = 1;
        final int testNum2 = 2;
        final int testNum3 = 3;
        final double tolerent = 0.1;
        Student student = new Student();
        student.setTestScore(testNum1, score1);
        student.setTestScore(testNum2, score2);
        student.setTestScore(testNum3, score3);

        double originAverage = (score1 + score2 + score3) / testNum3;
        System.out.println("Result:");
        System.out.println("Input scores average: " 
                + fmt.format(originAverage));
        System.out.println("Average from student: " 
                        + fmt.format(student.average()));
        return  (originAverage - student.average() < tolerent);
        
 
    }
    ///////////////////////////////////////////////////////////////////////////
    //Manual Testing                                                         //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * A class to run manually test constructor with all argument supplied.
     * @param scan A scanner object to get user input
     */
    public void manualTestConstructor(Scanner scan) {
       System.out.println("Test Student constructor with arguments.");
       String toContinue = "y";
       String firstName;
       String lastName;
       double test1Score = 0;
       double test2Score = 0;
       double test3Score = 0;
       do {
           System.out.println("Please input the information of the student:");
           System.out.println("First name:");
           firstName =  scan.next();
           System.out.println("Last name:");
           lastName =  scan.next();
           System.out.println("Test1 score:");
           if (scan.hasNextInt()) {
              test1Score = scan.nextInt(); 
           }
           System.out.println("Test2 score:");
           if (scan.hasNextInt()) {
              test2Score = scan.nextInt(); 
           }
           System.out.println("Test3 score:");
           if (scan.hasNextInt()) {
              test3Score = scan.nextInt(); 
           }
           Address homeAddress = new Address("21 Jump Street", "Langley", "BC",
                   "V3A 6K6");
           Address schoolAddress = new Address("3700 Willingdon Ave.", 
                   "Burnaby", "BC", "V5G 3H2");
           tryConstructorWithArgs(firstName, lastName, homeAddress, 
                   schoolAddress, test1Score, test2Score, test3Score);
           System.out.println("Try again? (y/n)");
           
           scan.nextLine();
           toContinue = scan.nextLine();
       } while (!toContinue.equalsIgnoreCase("n"));
    }
    
    /**A method provide manual test function.
     * @param scan The scanner to get user input.
     */
    public void manualTestSetGetScore(Scanner scan) {
        int testNumber;
        double testScore;
        String toContinue = "y";
        do {
        System.out.println("Test set and get sduent score.");
        System.out.println("Please input the test number (1-3):"); 
        if (scan.hasNextInt()) {
           testNumber = scan.nextInt();
           System.out.println("Please input the score:(0-100):");
           if (scan.hasNextDouble()) {
               testScore = scan.nextDouble();
               tryGetSetTestScore(testNumber, testScore);
           } else {
               System.out.println("Invalid input");
           }
        } else {
            System.out.println("Invalid input");
        }
        System.out.println("Try again? (y/n)");
        scan.nextLine();
        toContinue = scan.nextLine();
        } while (!toContinue.equalsIgnoreCase("n"));

        
        
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    //Helper methods to make the output looks more organized                 //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * A method that prints a header line of the result.
     */   
    private void printHeader() {
        System.out.println();
        System.out.println("**********************"
                + "*************************");
    }
    /**
     * A method that prints a footer line of the result.
     */  
    private void printFooter() {
        System.out.println("***********************"
                + "************************");        
    }
    
    /**
     * A method that prints a separating line between different test cases.
     */
    private void printSeparator() {
        System.out.println("------------------------"
                + "------------------------");
    }    
};
