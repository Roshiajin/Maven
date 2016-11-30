package com.epam.maven.controller;

import com.epam.maven.model.history.OperationHistory;
import com.epam.maven.model.operation.*;
import com.epam.maven.visualinterface.VisualInterfacePrinter;

import java.util.HashMap;
import java.util.Scanner;

public class CalculatorController {

    private Scanner scanner;

    private HashMap<String, Boolean> stage;

    private String currentStage;

    private VisualInterfacePrinter visualInterfacePrinter;

    private int operator = 0;

    private Operation operation = null;

    private OperationHistory operationHistory;

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
        setStage(this.currentStage, false);
        setStage(nextStage, true);
        this.currentStage = nextStage;
    }


    public CalculatorController() {
        this.scanner = new Scanner(System.in);
        this.stage = new HashMap<String, Boolean>();
        this.visualInterfacePrinter = new VisualInterfacePrinter();
        this.operationHistory = new OperationHistory();
        setNextStage("Beginning");
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

    public void executeStage() {

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
        } else
            executeStage();
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
        if (operator < 0 || operator > 4) {
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
        visualInterfacePrinter.printAllHistory(operationHistory);
    }
}
