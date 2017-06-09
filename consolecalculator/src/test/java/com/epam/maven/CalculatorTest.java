package com.epam.maven;

import com.epam.maven.controller.CalculatorController;
import com.epam.maven.model.history.Key;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Unit test for simple App.
 */
public class CalculatorTest extends TestCase
{

    public CalculatorTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( CalculatorTest.class );
    }

    public void testCalculator()
    {
        assertTrue( true );
    }

    public void testCalculatorAddition() {

        String userInput = "1\n1\n1\n1\ntest1\n0";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        CalculatorController controller = new CalculatorController(inputStream);
        controller.executeStage();
        assertEquals("1+1=2.0", controller.getHistoryByName(1, "test1").toString());
    }

    public void testCalculatorSubtraction() {

        String userInput = "2\n2\n1\n2\ntest2\n0";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        CalculatorController controller = new CalculatorController(inputStream);
        controller.executeStage();
        assertEquals("2-1=1.0", controller.getHistoryByName(2, "test2").toString());
    }

    public void testCalculatorMultiplication() {

        String userInput = "3\n3\n3\n3\ntest3\n0";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        CalculatorController controller = new CalculatorController(inputStream);
        controller.executeStage();
        assertEquals("3*3=9.0", controller.getHistoryByName(3, "test3").toString());
    }

    public void testCalculatorDivision() {

        String userInput = "4\n4\n4\n4\ntest4\n0";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        CalculatorController controller = new CalculatorController(inputStream);
        controller.executeStage();
        assertEquals("4/4=1.0", controller.getHistoryByName(4, "test4").toString());
    }

    public void testCalculatorHistoryByName() {
        String userInput = "1\n1\n1\n1\ntest1\n1\n2\n2\n1\n2\ntest2\n2\n1\ntest1\n0";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        CalculatorController controller = new CalculatorController(inputStream);
        controller.executeStage();
        assertEquals("1+1=2.0", controller.getHistoryByName(1, "test1").toString());
    }

    public void testCalculatorHistory() {
        String userInput = "1\n1\n1\n1\ntest1\n1\n2\n2\n1\n2\ntest2\n1\n3\n3\n3\n3\ntest3\n1\n4\n4\n4\n4\ntest4\n0";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        String actualHistory = "";
        String expectedHistory = "1+1=2.0\n2-1=1.0\n3*3=9.0\n4/4=1.0\n";

        CalculatorController controller = new CalculatorController(inputStream);
        controller.executeStage();
        for (Key k : controller.getHistory().getAll().keySet()) {

            actualHistory += controller.getHistory().getAll().get(k).toString() + "\n";
        }

        assertEquals(expectedHistory, actualHistory);
    }

}
