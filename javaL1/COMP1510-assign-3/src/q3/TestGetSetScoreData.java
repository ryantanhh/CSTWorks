package q3;

/**
 * <p>A class that offer data for testing the getter and setter methods of 
 * test.</p>
 * score of the Student class.
 * @author Hai Hua, Tan
 * @version 1.0 
 */
public class TestGetSetScoreData {
    /**A description of the test case.*/
    private String caseDescription;
    
    /**An integer value to fill in the "testNum" field.*/
    private int testNum;
    
    /**An double value to fill in the "testScore" field.*/
    private double testScore;
    
    /**The expected value when executing the case.*/
    private boolean expectedResult;
    
    /**Constructor.
     *@param caseDescription A description of the testing case
     * @param testNum An integer to indicate which test.
     * @param testScore The score of the test.
     *@param expectedResult A value set to be compared with the actual result.
     * 
     */
    TestGetSetScoreData(String caseDescription, int testNum, double testScore,
            boolean expectedResult) {
        this.caseDescription = caseDescription;
        this.testNum = testNum;
        this.testScore = testScore;
        this.expectedResult = expectedResult;
    }
    
    /**
     * Getter method of caseDescription.
     * @return Description of the test case.
     */
    public String getCaseDescription() {
        return caseDescription;
    }
    
    /**Getter method of testNum.
     * @return The test number
     */
    public int getTestNum() {
        return testNum;
    }
    
    /**
     * Getter method of testScore.
     * @return The value of testScore
     */
    public double getTestScore() {
        return testScore;
    }
    
    /**
     * Getter method of expectedResult.
     * @return Boolean value of expectedResult
     */ 
    public boolean getExpectedResult() {
        return expectedResult;
    }
    
}
