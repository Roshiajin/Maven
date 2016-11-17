package com.epam.maven;

/**
 * Created by Alexander_Gaptullin on 11/17/2016.
 */
public class Operation {

    private int firstNumber;
    private int secondNumber;
    private double result;

    static int addition (int firstNumber, int secondNumber) {

        return firstNumber + secondNumber;
    }

    static int subtraction (int firstNumber, int secondNumber) {

        return firstNumber - secondNumber;
    }

    static int multiplication (int firstNumber, int secondNumber) {

        return firstNumber * secondNumber;
    }
    static double division (int firstNumber, int secondNumber) {

        return (double) firstNumber / secondNumber;
    }

}
