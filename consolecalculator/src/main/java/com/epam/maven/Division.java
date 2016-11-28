package com.epam.maven;

/**
 * Created by Alexander_Gaptullin on 11/28/2016.
 */
public class Division implements MathOperation {

    private final String operationSign = "/";

    public String getOperationSign() {
        return operationSign;
    }

    public double calculate(int firstNumber, int secondNumber) {

        if (secondNumber== 0) {
            throw new ArithmeticException("/ by zero");
        }

        return (double) firstNumber / secondNumber;

    }
}
