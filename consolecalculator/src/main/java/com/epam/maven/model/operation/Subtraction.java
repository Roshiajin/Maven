package com.epam.maven.model.operation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Alexander_Gaptullin on 11/28/2016.
 */
public class Subtraction implements MathOperation {

    private static final Logger logger = LogManager.getLogger(Subtraction.class);

    private final String operationSign = "-";

    public String getOperationSign() {
        return operationSign;
    }

    public double calculate(int firstNumber, int secondNumber) {

        logger.trace("Subtraction.calculate({}, {})", firstNumber, secondNumber);

        return firstNumber - secondNumber;
    }
}
