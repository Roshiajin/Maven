package com.epam.maven;

/**
 * Created by Alexander_Gaptullin on 11/17/2016.
 */
public class Operation {

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
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
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
        return  firstNumber +
                operator +
                secondNumber +
                "=" + result;
    }

}
