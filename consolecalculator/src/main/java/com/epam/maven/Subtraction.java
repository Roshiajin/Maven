package com.epam.maven;

/**
 * Created by Alexander_Gaptullin on 11/28/2016.
 */
public class Subtraction implements MathOperation {

    private final String operationSign = "-";

    public String getOperationSign() {
        return operationSign;
    }

    public double calculate(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }
}
