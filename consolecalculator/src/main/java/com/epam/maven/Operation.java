package com.epam.maven;

/**
 * Created by Alexander_Gaptullin on 11/17/2016.
 */
public class Operation {

    private int firstNumber;
    private int secondNumber;
    private String operator;
    private double result;

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

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void addition () {

        setResult(firstNumber + secondNumber);
    }

    public void subtraction () {

        setResult(firstNumber - secondNumber);
    }

    public void  multiplication () {

        setResult(firstNumber * secondNumber);
    }
    public void division () throws ArithmeticException {

        setResult((double) firstNumber / secondNumber);

        if (getSecondNumber() == 0) {
            throw new ArithmeticException("/ by zero");
        }
    }

    @Override
    public String toString() {
        return  firstNumber +
                operator +
                secondNumber +
                "=" + result;
    }

}
