package com.epam.maven;

import com.epam.maven.model.operation.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OperationTest extends TestCase {


    public OperationTest(String testName )
    {
        super( testName );
    }


    public static Test suite()
    {
        return new TestSuite( OperationTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testOperation()
    {
        assertTrue( true );

    }

    public void testAddition() {

        Operation operation = new Operation(new Addition());
        operation.setFirstNumber(2);
        operation.setSecondNumber(3);
        operation.calculateResult();

        assertEquals(5d, operation.getResult());

    }

    public void testSubtraction() {

        Operation operation = new Operation(new Subtraction());
        operation.setFirstNumber(5);
        operation.setSecondNumber(2);
        operation.calculateResult();

        assertEquals(3d, operation.getResult());
    }

    public void testMultiplication() {

        Operation operation = new Operation(new Multiplication());
        operation.setFirstNumber(5);
        operation.setSecondNumber(2);
        operation.calculateResult();

        assertEquals(10d, operation.getResult());
    }

    public void testDivision() {

        Operation operation = new Operation(new Division());
        operation.setFirstNumber(8);
        operation.setSecondNumber(2);
        operation.calculateResult();

        assertEquals(4d, operation.getResult());
    }

    public void testDivisionByZero() {

        Operation operation = new Operation(new Division());
        operation.setFirstNumber(8);
        operation.setSecondNumber(0);
        try {
            operation.calculateResult();
            assertTrue(false);
        } catch (ArithmeticException e) {
            assertTrue(true);
        }

    }

   public void testGetOperator() {

       Operation operation = new Operation(new Addition());

       assertEquals("+", operation.getOperator());
   }
}
