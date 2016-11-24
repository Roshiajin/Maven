package com.epam.maven;

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

        Operation operation = new Operation();
        operation.setFirstNumber(2);
        operation.setSecondNumber(3);
        operation.addition();

        assertEquals(5d, operation.getResult());

    }

    public void testSubtraction() {

        Operation operation = new Operation();
        operation.setFirstNumber(5);
        operation.setSecondNumber(2);
        operation.subtraction();

        assertEquals(3d, operation.getResult());
    }

    public void testMultiplication() {

        Operation operation = new Operation();
        operation.setFirstNumber(5);
        operation.setSecondNumber(2);
        operation.multiplication();

        assertEquals(10d, operation.getResult());
    }

    public void testDivision() {

        Operation operation = new Operation();
        operation.setFirstNumber(8);
        operation.setSecondNumber(2);
        operation.division();

        assertEquals(4d, operation.getResult());
    }

    public void testDivisionByZero() {

        Operation operation = new Operation();
        operation.setFirstNumber(8);
        operation.setSecondNumber(0);
        try {
            operation.division();
            assertTrue(false);
        } catch (ArithmeticException e) {
            assertTrue(true);
        }

    }

   public void testGetFirstNumber() {

       Operation operation = new Operation();
       operation.setFirstNumber(8);

       assertEquals(8, operation.getFirstNumber());
   }

   public void testGetSecondNumber() {

       Operation operation = new Operation();
       operation.setSecondNumber(9);

       assertEquals(9, operation.getSecondNumber());
   }

   public void testGetOperator() {

       Operation operation = new Operation();
       operation.setOperator("+");

       assertEquals("+", operation.getOperator());
   }
}
