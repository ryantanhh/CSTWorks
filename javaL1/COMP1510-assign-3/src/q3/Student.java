package q3;

import java.text.DecimalFormat;

/**
 * Represents a college student.
 * @author Lewis
 * @author Loftus
 * @author Hai Hua, Tan
 * @version 1.1
 * <p>New features in version 1.1:
 * <ol>
 * <li>Provide a constructor that sets all instance values based on parameter
 *  values.</li>
 *  <li>Provide a zero-parameter constructor that sets each test score to 
 *  zero.</li>
 *  <li>Provide a setTestScore method that accepts two parameters the test 
 *  number (1 through 3) and the score.</li>
 *  <li>Provide a getTestScore method that accepts the test number and returns 
 *  the appropriate score.</li>
 * <li>Provide a method called average that computes and returns the average
 *  test score for this student</li>
 * </ol>
 * Feature changed in version 1.1:
 * toString method such that the test scores and average are included in the 
 * description of the student.
 * </p>
 * 
 */
public class Student {
    /**The maximum score value.*/
    static final double MAX_SCORE = 100;
    /**The minimum score value.*/
    static final double MIN_SCORE = 0;
    /**The maximum test number.*/
    static final int MAX_TEST_NUMBER = 3;
    /**The minimum test number.*/
    static final int MIN_TEST_NUMBER = 1;
    /** First name of this student. */
    private String firstName;

    /** Last name of this student. */
    private String lastName;

    /** Home address of this student. */
    private Address homeAddress;

    /** School address of this student.  Can be shared by other students */
    private Address schoolAddress;
    
    /** Score of test1.*/
    private double test1Score;

    /** Score of test2.*/
    private double test2Score;

    /** Score of test3.*/
    private double test3Score;
    

    /**
    * Constructor: Sets up this student with the specified values.
    * @param first The first name of the student
    * @param last The last name of the student
    * @param home The home address of the student
    * @param school The school address of the student
    * @param score1 The score of test 1
    * @param score2 The score of test 2
    * @param score3 The score of test 3
    *  
    */
    public Student(String first, String last, Address home, Address school,
                    double score1, double score2, double score3) {
        //Throw exception when both first and last name are empty
        if ((first.length() == 0) && (last.length() == 0)) {
            throw new IllegalArgumentException(
                    "Invalid name! Student name cannot be empty.");
        }
        
        //Throw exception when any one of the scores are out of range
        if (score1 < MIN_SCORE || score1 > MAX_SCORE 
         || score2 < MIN_SCORE || score2 > MAX_SCORE
         || score3 < MIN_SCORE || score3 > MAX_SCORE) {
            throw new IllegalArgumentException(
                    "Invalid score! Score should be in the range of 0 ~ 100.");
        }
        firstName = first;
        lastName = last;
        homeAddress = home;
        schoolAddress = school;
        test1Score = score1;
        test2Score = score2;
        test3Score = score3;
    }
    
    /**
     * Constructor: zero-parameter constructor that sets each test score to 
     * zero.
     */
    public Student() {
        test1Score = 0;
        test2Score = 0;
        test3Score = 0;
    }

    /**
    * Returns a string description of this Student object.
    * @return formatted name and addresses of student
    */
    public String toString() {
        DecimalFormat fmt = new DecimalFormat("0.0");
        String result;

        result = firstName + " " + lastName + "\n";
        result += "Home Address:\n" + homeAddress + "\n";
        result += "School Address:\n" + schoolAddress + "\n";
        result += "Test1 Score: " + test1Score + "\n";
        result += "Test2 Score: " + test2Score + "\n";
        result += "Test3 Score: " + test3Score + "\n";
        result += "Average: " + fmt.format(average()) + "\n";
        return result;
    }
        
    /**A method to set test score with the test number.
     * @param testNumber The number of the test. (1, 2, 3)
     * @param score The score of the test
     */   
    public void setTestScore(int testNumber, double score) {
        final int testNumber1 = 1;
        final int testNumber2 = 2;
        final int testNumber3 = 3;
        final int minScore = 0;
        final int maxScore = 100;
        //Check illegal score argument
        if (score > maxScore || score < minScore) {
            throw new IllegalArgumentException(
                    "Invalid score! Score should be in the range of 0 ~ 100.");
        }
        
        switch (testNumber) {
        case testNumber1: 
            test1Score = score;
            break;
        case testNumber2:
            test2Score = score;
            break;
        case testNumber3:
            test3Score = score;
            break;
        default:
            //Throw exception if no test number matched
            throw new IllegalArgumentException(
                    "Invalid test number. Test number should be 1,2 or 3.");
        }
    }
    
    /**A method to get test score by the number of the test.
     * @param testNumber The number of the test.
     * @return The score of the test of the requiring number.
     */
    public double getTestScore(int testNumber) {
        final int testNumber1 = 1;
        final int testNumber2 = 2;
        final int testNumber3 = 3;
        
        //Throw exception if test number is not in the range
        if (!(testNumber == testNumber1) 
         && !(testNumber == testNumber2) 
         && !(testNumber == testNumber3)) {
            throw new IllegalArgumentException(
                    "Invalid test number. Test number should be 1,2 or 3.");
        }
        //Return the corresponding test score.
        double score = 0;
        switch (testNumber) {
        case testNumber1:
            score = test1Score;
            break;
        case testNumber2:
            score = test2Score;
            break;
        case testNumber3:
            score = test3Score;
            break;
        default:
            break;    
        }
        return score;
    }
    
    /**A method return the average score of the student.
     * @return The average of score of 3 test. 
     */
    public double average() {
        final int testCount = 3;
        return (test1Score + test2Score + test3Score) / testCount;
    }    
}
