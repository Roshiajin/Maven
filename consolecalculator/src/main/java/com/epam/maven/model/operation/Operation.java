package com.epam.maven.model.operation;

import com.epam.maven.visualinterface.VisualInterfacePrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Operation {

    private static final Logger logger = LogManager.getLogger(Operation.class);

    private int firstNumber;
    private int secondNumber;
    private String operator;
    private double result;
    private MathOperation mathOperation;

    public Operation(MathOperation mathOperation) {
        this.mathOperation = mathOperation;
        setOperator(this.mathOperation.getOperationSign());
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {

        logger.trace("operation.setFirstNumber {}", firstNumber);

        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {

        logger.trace("operation.setSecondNumber {}", secondNumber);

        this.secondNumber = secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    private void setOperator(String operator) {
        this.operator = operator;
    }

    public double getResult() {
        return result;
    }

    private void setResult(double result) {
        this.result = result;
    }

    public void calculateResult() {
        setResult(mathOperation.calculate(getFirstNumber(), getSecondNumber()));
    }

    @Override
    public String toString() {
        return firstNumber +
                operator +
                secondNumber +
                "=" + result;
    }

}
