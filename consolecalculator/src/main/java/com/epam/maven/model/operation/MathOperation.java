package com.epam.maven.model.operation;

public interface MathOperation {

    public String getOperationSign();

    public double calculate(int firstNumber, int secondNumber);
}
