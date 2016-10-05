package q3;

import java.util.Random;

/**A class that offers data to test the arguments supplied version
 * constructor of the Student class.
 * @author Hai Hua, Tan
 * @version 1.0
 * */

public class TestConstructorData {
    
    /**A description of the test case.*/
    private String caseDescription;
    
    /**A string to fill in the "firstName" field.*/
    private String firstName;
    
    /**A string to fill in the "lastName" field.*/
    private String lastName;
    
    /**An Address variable to fill in the "homeAddress" field.*/
    private Address homeAddress;
    
    /**An Address variable to fill in the "schoolAddress" field.*/    
    private Address schoolAddress;
    
    /**A double value to fill in the "test1Score" field.*/
    private double test1Score;
    
    /**A double value to fill in the "test2Score" field.*/
    private double test2Score;
    
    /**A double value to fill in the "test3Score" field.*/
    private double test3Score;
    
    /**The expected value when executing the case.*/
    private boolean expectedResult;
    
    /**
     *Overloaded constructor of the class with description, firstName, 
     *lastName offered.
     *@param caseDescription A description of the testing case
     *@param firstName Test data for "firstName" field
     *@param lastName Test data for "lastName" field
     *@param expectedResult A value set to be compared with the actual result.
     *
     */
    TestConstructorData(String caseDescription, String firstName, 
            String lastName, boolean expectedResult) {
        Random generator = new Random();
        this.caseDescription = caseDescription;
        this.firstName = firstName;
        this.lastName = lastName;
        homeAddress = new Address("21 Jump Street", "Langley", "BC",
                "V3A 6K6");
        schoolAddress = new Address("3700 Willingdon Ave.", "Burnaby", "BC",
                "V5G 3H2");
        test1Score = Math.round(Student.MAX_SCORE * generator.nextDouble());
        test2Score = Math.round(Student.MAX_SCORE * generator.nextDouble());
        test3Score = Math.round(Student.MAX_SCORE * generator.nextDouble());
        this.expectedResult = expectedResult;
    }
    
    /**
     *Overloaded constructor of the class with description, firstName, 
     *lastName offered.
     *@param caseDescription A description of the testing case
     *@param test1Score Test data for "test1Score" field
     *@param test2Score Test data for "test2Score" field
     *@param test3Score Test data for "test3Score" field
     *@param expectedResult A value set to be compared with the actual result.
     *
     */
    TestConstructorData(String caseDescription, double test1Score, 
            double test2Score, double test3Score, boolean expectedResult) {
        this.caseDescription = caseDescription;
        firstName = "John";
        lastName = "Smith";
        homeAddress = new Address("21 Jump Street", "Langley", "BC",
                "V3A 6K6");
        schoolAddress = new Address("3700 Willingdon Ave.", "Burnaby", "BC",
                "V5G 3H2");
        this.test1Score = test1Score;
        this.test2Score = test2Score;
        this.test3Score = test3Score;
        this.expectedResult = expectedResult;
    }
    
    /**
     * Getter method of caseDescription.
     * @return Description of the test case.
     */
    public String getCaseDescription() {
        return caseDescription;
    }
    
    /**
     * Getter method of firstName.
     * @return String of firstName.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Getter method of lastName.
     * @return String of lastName.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Getter method of homeAddress.
     * @return Address object reference of homeAddress
     */
    public Address getHomeAddress() {
        return homeAddress;
    }
    
    /**
     * Getter method of schoolAddress.
     * @return Address object reference of schoolAddress
     */
    public Address getSchoolAddress() {
        return schoolAddress;
    }
    
    /**
     * Getter method of test1Score.
     * @return Double value of test1Score.
     */
    public double getTest1Score() {
        return test1Score;
    }
    
    /**
     * Getter method of test2Score.
     * @return Double value of test2Score
     */
    public double getTest2Score() {
        return test2Score;
    }
    
    /**
     * Getter method of test3Score.
     * @return Double value of test2Score
     */
    public double getTest3Score() {
        return test3Score;
    }
    
    /**
     * Getter method of expectedResult.
     * @return Boolean value of expectedResult
     */ 
    public boolean getExpectedResult() {
        return expectedResult;
    }
    
}
