package q3;

import java.util.Random;

/**
 * <p>A class that provide test data to populate test cases.</p>
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class TestData {
    /**An array holds data for testing constructor of Student.*/
    private TestConstructorData[] testConstructorData;
    
    /**An array holds data for testing get and set test score.*/
    private TestGetSetScoreData[] testGetSetScoreData;
    
    /**Constructor. Do the data preparing jobs here.*/
    TestData() {
        prepareConstructorData();
        prepareGetSetScoreData();
    }
    
    /**
     * A method to generate data for testing constructor with arguments of 
     * the Student class. Add new items to the array will automatically
     * create a new test case.
     */
    private void prepareConstructorData() {
        Random generator = new Random();
        testConstructorData = new TestConstructorData[] {
          new TestConstructorData(
                  "Test constructor with all arguments supplied appropriately.",
                  "John",
                  "Smith",
                  true
                  ),
          new TestConstructorData(
                  "Test constructor with empty first name supplied.",
                  "",
                  "Smith",
                  true
                  ),
          new TestConstructorData(
                  "Test constructor with empty last name supplied.",
                  "John",
                  "",
                  true
                  ),
          new TestConstructorData(
                  "Test constructor with empty last and last name supplied.",
                  "",
                  "",
                  false
                  ),
          new TestConstructorData(
                  "Test constructor with  test1 score exceeds the upper "
                  + "boundary.",
                  Math.round(Student.MAX_SCORE + 1),
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  false
                  ),
          new TestConstructorData(
                  "Test constructor with test2 score exceeds the upper "
                  + "boundary.",
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  Math.round(Student.MAX_SCORE + 1),
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  false
                  ),
          new TestConstructorData(
                  "Test constructor with test3 score exceeds the upper "
                  + "boundary.",
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  Math.round(Student.MAX_SCORE + 1),                  
                  false
                  ),
          new TestConstructorData(
                  "Test constructor with test1 score less than the lower "
                  + "boundary.",
                  Math.round(Student.MIN_SCORE - 1),
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  false
                  ),
          new TestConstructorData(
                  "Test constructor with test2 score less than the lower "
                  + "boundary.",
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  Math.round(Student.MIN_SCORE - 1),
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  false
                  ),
          new TestConstructorData(
                  "Test constructor with test3 score less than the lower "
                  + "boundary.",
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  Math.round(Student.MAX_SCORE * generator.nextDouble()),
                  Math.round(Student.MAX_SCORE + 1),                  
                  false
                  )        
          
        };
    }
    
    /**
     * A method to generate data for testing getTestScore and setTestScore
     * methods of the Student class. Add new items to the array will 
     * automatically create a new test case.
     */
    private void prepareGetSetScoreData() {
        Random generator = new Random();
        testGetSetScoreData = new TestGetSetScoreData[]{
                new TestGetSetScoreData(
                    "Test setting test1 with valid score",
                    1,
                    Math.round(Student.MAX_SCORE * generator.nextDouble()),
                    true
                    ),
                new TestGetSetScoreData(
                    "Test setting test2 with valid score",
                    2,
                    Math.round(Student.MAX_SCORE * generator.nextDouble()),
                    true
                    ),               
                new TestGetSetScoreData(
                        "Test setting test3 with valid score",
                        Student.MAX_TEST_NUMBER,
                        Math.round(Student.MAX_SCORE * generator.nextDouble()),
                        true
                        ),   
                new TestGetSetScoreData(
                        "Test setting test1 with a score greater than "
                        + "upper boundary",
                        1,
                        Student.MAX_SCORE + 1,
                        false
                        ),   
                new TestGetSetScoreData(
                        "Test setting test1 with a score less than "
                        + "lower boundary",
                        1,
                        Student.MIN_SCORE - 1,
                        false
                        ),  
                new TestGetSetScoreData(
                        "Test setting score to a number greater than the "
                        + "upper boundary of test number.",
                        Student.MAX_TEST_NUMBER + 1,
                        Math.round(Student.MAX_SCORE * generator.nextDouble()),
                        false
                        ), 
                new TestGetSetScoreData(
                        "Test setting score to a number less than the lower "
                        + "boundary of test number.",
                        Student.MAX_TEST_NUMBER + 1,
                        Math.round(Student.MAX_SCORE * generator.nextDouble()),
                        false
                        ), 
        };
        
    }
    
    /**Getter method of testConstructorData.
     * @return the reference of the testConstructorData array
     */
    public TestConstructorData[] getTestConstructorData() {
        return testConstructorData;
    }
    
    /**Getter method of testConstructorData.
     * @return the reference of the testGetSetScoreData array
     */
    public TestGetSetScoreData[] getTestGetSetScoreData() {
        return testGetSetScoreData;
    }
}

