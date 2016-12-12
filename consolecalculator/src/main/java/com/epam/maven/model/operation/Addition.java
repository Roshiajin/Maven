package com.epam.maven.model.operation;

import com.epam.maven.controller.CalculatorController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Alexander_Gaptullin on 11/28/2016.
 */
public class Addition implements MathOperation {

    private static final Logger logger = LogManager.getLogger(Addition.class);

    private final String operationSign = "+";

    public String getOperationSign() {
        return operationSign;
    }

    public double calculate(int firstNumber, int secondNumber) {

        logger.trace("Addition.calculate({}, {})", firstNumber, secondNumber);

        return firstNumber + secondNumber;
    }
}
