package com.epam.maven.model.operation;

/**
 * Created by Alexander_Gaptullin on 11/28/2016.
 */
public class Addition implements MathOperation {

    private final String operationSign = "+";

    public String getOperationSign() {
        return operationSign;
    }

    public double calculate(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
