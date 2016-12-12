package com.epam.maven.controller;

import com.epam.maven.model.history.Key;
import com.epam.maven.model.history.OperationHistory;
import com.epam.maven.model.operation.*;
import com.epam.maven.visualinterface.VisualInterfacePrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CalculatorController {

    private static final Logger logger = LogManager.getLogger(CalculatorController.class);

    private Scanner scanner;

    private HashMap<String, Boolean> stage;

    private String currentStage;

    private VisualInterfacePrinter visualInterfacePrinter;

    private int operator = 0;

    private Operation operation = null;

    private OperationHistory operationHistory;

    private boolean hasStage = true;

    private final int[] allowedOperations = {0,1,2,3,4};

    public CalculatorController() {
        this(System.in);
    }

    public CalculatorController(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        this.stage = new HashMap<>();
        this.visualInterfacePrinter = new VisualInterfacePrinter();
        this.operationHistory = new OperationHistory();
        setNextStage("Beginning");
    }

    public void setStage(String stageName, boolean stageState) {
        stage.put(stageName, stageState);
    }

    public boolean getStage(String stageName) {
        if (stage.containsKey(stageName)) {
            return stage.get(stageName);
        }

        return false;
    }

    public void setNextStage(String nextStage) {
        if (this.currentStage != null)
            setStage(this.currentStage, false);
        setStage(nextStage, true);
        this.currentStage = nextStage;
    }

    public String getInputStringValue() {
        while (!scanner.hasNext()) {
            scanner.next();
        }
        return scanner.next();
    }

    public int getInputIntValue() {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        return scanner.nextInt();
    }

    public OperationHistory getHistory() {
        return operationHistory;
    }

    public Operation getHistoryByName(int number, String name) {
        return operationHistory.getAll().get(new Key(number, name));
    }

    public void executeStage() {

        while (hasStage) {

            logger.info("Current stage: {}", this.currentStage);

            if (getStage("Beginning")) {
                visualInterfacePrinter.printGreetings();
                setNextStage("OperatorChoosing");
            }
            if (getStage("OperatorChoosing")) {
                operatorChoosing();
            }
            if (getStage("InputNumbers")) {
                inputNumbers();

                try {
                    operation.calculateResult();
                    setNextStage("PrintResult");
                } catch (ArithmeticException e) {
                    logger.catching(e);
                    visualInterfacePrinter.printWrongMathOperation(e.getMessage());
                    setNextStage("NextAction");
                }
            }
            if (getStage("PrintResult")) {
                visualInterfacePrinter.printResult(String.valueOf(operation.getResult()));
                setNextStage("SaveResult");
            }
            if (getStage("SaveResult")) {
                visualInterfacePrinter.printSavingHistory();
                saveResult();
                setNextStage("NextAction");
            }
            if (getStage("NextAction")) {
                nextActionChoosing();
            }
            if (getStage("PrintHistoryByName")) {
                getOperationHistoryByName();
                setNextStage("NextAction");
            }
            if (getStage("Exit")) {
                getOperationHistory();
                visualInterfacePrinter.exit();
                break;
            }
        }
    }

    public void operatorChoosing() {

        visualInterfacePrinter.printInputFunction();

        operator = getInputIntValue();

        if (!operatorValidation()) {
            operatorChoosing();
        } else {

            if (operator == 1) {
                operation = new Operation(new Addition());
            }
            if (operator == 2) {
                operation = new Operation(new Subtraction());
            }
            if (operator == 3) {
                operation = new Operation(new Multiplication());
            }
            if (operator == 4) {
                operation = new Operation(new Division());
            }
            if (operator == 0) {
                setNextStage("Exit");
            } else
                setNextStage("InputNumbers");
        }
    }

    public boolean operatorValidation() {
        if (!IntStream.of(allowedOperations).anyMatch(x -> x == operator)) {
            visualInterfacePrinter.printWrongFunction();
            return false;
        }

        return true;
    }

    public void inputNumbers() {
        visualInterfacePrinter.printInputFirstNumber();
        operation.setFirstNumber(getInputIntValue());
        visualInterfacePrinter.printInputSecondNumber();
        operation.setSecondNumber(getInputIntValue());
    }

    public void getKeyForHistory() {
        int keyNumber;
        String keyName;

        visualInterfacePrinter.printInputNumberForHistory();
        keyNumber = getInputIntValue();
        visualInterfacePrinter.printInputNameForHistory();
        keyName = getInputStringValue();
        operationHistory.setKey(keyNumber, keyName);
    }

    public void saveResult() {
        getKeyForHistory();

        if (operationHistory.keyValidation()) {
            visualInterfacePrinter.printWrongKeyForSavingHistory();
            saveResult();
        } else {
            operationHistory.setValue(operation);
            operationHistory.put();
        }
    }

    public void nextActionChoosing() {
        int nextAction;

        visualInterfacePrinter.printInputNextAction();
        nextAction = getInputIntValue();

        setNextStage("Exit");

        if (nextAction == 1) {
            setNextStage("OperatorChoosing");
        }
        if (nextAction == 2) {
            setNextStage("PrintHistoryByName");
        }

    }

    public void getOperationHistoryByName() {
        visualInterfacePrinter.printGettingHistory();
        getKeyForHistory();
        visualInterfacePrinter.printHistoryByName(operationHistory);
    }

    public void getOperationHistory() {

        if (operationHistory.getAll().size() > 0)
            visualInterfacePrinter.printAllHistory(operationHistory);
    }
}
