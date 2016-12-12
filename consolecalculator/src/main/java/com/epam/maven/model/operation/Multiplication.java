package com.epam.maven.model.operation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Alexander_Gaptullin on 11/28/2016.
 */
public class Multiplication implements MathOperation {

    private static final Logger logger = LogManager.getLogger(Multiplication.class);

    private final String operationSign = "*";

    public String getOperationSign() {
        return operationSign;
    }

    public double calculate(int firstNumber, int secondNumber) {

        logger.trace("Multiplication.calculate({}, {})", firstNumber, secondNumber);

        return firstNumber * secondNumber;
    }

}
