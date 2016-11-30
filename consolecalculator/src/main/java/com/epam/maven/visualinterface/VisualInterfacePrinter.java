package com.epam.maven.visualinterface;

import com.epam.maven.model.history.Key;
import com.epam.maven.model.history.OperationHistory;

public class VisualInterfacePrinter {

    public VisualInterfacePrinter() {
    }


    public void printGreetings() {
        System.out.println("Добро пожаловать в калькулятор!");
    }

    public void printInputFunction() {
        System.out.println("Выберете функцию:\n" +
                "1 - сложение\n" +
                "2 - вычитание\n" +
                "3 - умножение\n" +
                "4 - деление\n" +
                "0 - выход");
    }

    public void printWrongFunction() {
        System.out.println("Неверно выбрана функция!");
    }

    public void printWrongMathOperation(String message) {
        System.out.println("Неверная арифметическая операция!");
        System.out.println("Подробнее: " + message);
    }

    public void printInputFirstNumber() {
        System.out.println("Введите первое число:");
    }

    public void printInputSecondNumber() {
        System.out.println("Введите второе число:");
    }

    public void printResult(String result) {
        System.out.println("Результат: " + result);
    }

    public void printSavingHistory() {
        System.out.println("Введите номер и имя для сохранения операции. ");
    }

    public void printInputNumberForHistory() {
        System.out.println("Введите номер: ");
    }

    public void printInputNameForHistory() {
        System.out.println("Введите имя: ");
    }

    public void printGettingHistory() {
        System.out.println("Введите сочетание номера и имени сохраненной операции. ");
    }

    public void printWrongKeyForSavingHistory() {
        System.out.println("Введенное сочетание номера и имени уже существует. Введите другое сочетание.");
    }

    public void printHistoryByName(OperationHistory operationHistory) {

        if (!operationHistory.keyValidation()) {
            System.out.println("Введенное сочетание номера и имени не найдено.");
        } else {
            System.out.println("Сохранённый результат: " + operationHistory.getHistoryByKey().toString());
        }

    }

    public void printAllHistory(OperationHistory operationHistory) {
        System.out.println("История операций:");

        for (Key k : operationHistory.getAll().keySet()) {

            System.out.println(k.toString() + " " + operationHistory.getAll().get(k).toString());
        }
    }

    public void printInputNextAction() {
        System.out.println("Чтобы продолжить наберите - 1, чтобы найти сохранённый результат - 2, чтобы выйти и распечатать историю - любое другое число");

    }

    public void exit() {
        System.out.println("Выход...");
    }
}
