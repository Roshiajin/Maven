package com.epam.maven;

import java.util.*;

public class Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int operator = 0;
        int nextAction;
        boolean doCalculation = true;
        boolean hasOperation;
        boolean hasOperationMapKey;
        boolean lastAction;

        Operation operation;
        Key key = new Key();
        String keyName;
        int keyNumber;

        //List<Operation> operationsHistory = new ArrayList<Operation>();
        Map<Key, Operation> operationsHistoryMap = new LinkedHashMap<Key, Operation>();


        System.out.println("Добро пожаловать в калькулятор");

        beforeCalculation:
        while (doCalculation) {

            hasOperation = false;
            operation = new Operation();

            while (!hasOperation) {
                System.out.println("Выберете функцию:\n" +
                        "1 - сложение\n" +
                        "2 - вычитание\n" +
                        "3 - умножение\n" +
                        "4 - деление\n" +
                        "0 - выход");

                while (!scanner.hasNextInt()) {
                    scanner.next();
                }

                operator = scanner.nextInt();
                if (operator < 0 || operator > 4) {
                    System.out.println("Неверно выбрана функция!");
                    continue;
                }

                if (operator == 1) {
                    operation.setOperator("+");
                }
                if (operator == 2) {
                    operation.setOperator("-");
                }
                if (operator == 3) {
                    operation.setOperator("*");
                }
                if (operator == 4) {
                    operation.setOperator("/");
                }
                if (operator == 0) {
                    break beforeCalculation;
                }

                hasOperation = true;
            }

            System.out.println("Введите первое число:");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }

            operation.setFirstNumber(scanner.nextInt());

            System.out.println("Введите второе число:");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }

            operation.setSecondNumber(scanner.nextInt());

            try {
                if (operator == 1) {
                    operation.addition();
                }
                if (operator == 2) {
                    operation.subtraction();
                }
                if (operator == 3) {
                    operation.multiplication();
                }
                if (operator == 4) {
                    operation.division();
                }

                //Print result
                System.out.println("Результат: " + operation.getResult());
                System.out.println(operation.toString());

            } catch (ArithmeticException e) {
                System.out.println("Неверная арифметическая операция!");
            }

            //operationsHistory.add(operation);
            hasOperationMapKey = false;
            System.out.println("Введите номер и имя для сохранения операции. ");
            //System.out.println("Введите номер: ");
            while (!hasOperationMapKey) {

                System.out.println("Введите номер: ");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }

                keyNumber = scanner.nextInt();

                System.out.println("Введите имя: ");
                while (!scanner.hasNext()) {
                    scanner.next();
                }

                keyName = scanner.next();

                key = new Key(keyNumber, keyName);
                if (operationsHistoryMap.containsKey(key)) {
                    System.out.println("Введенное сочетание номера и имени уже существует. Введите другое сочетание.");
                    continue;
                }
                hasOperationMapKey = true;
            }

            operationsHistoryMap.put(key, operation);

            lastAction = false;
            beforeLastAction:
            while (!lastAction) {
                System.out.println("Чтобы продолжить наберите - 1, чтобы найти сохранённый результат - 2, чтобы выйти и распечатать историю - любое другое число");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }

                nextAction = scanner.nextInt();
                if (nextAction == 1) {
                    continue beforeCalculation;
                }
                if (nextAction == 2) {
                    System.out.println("Введите сочетание номера и имени сохраненной операции. ");


                        System.out.println("Введите номер: ");
                        while (!scanner.hasNextInt()) {
                            scanner.next();
                        }

                        keyNumber = scanner.nextInt();

                        System.out.println("Введите имя: ");
                        while (!scanner.hasNext()) {
                            scanner.next();
                        }

                        keyName = scanner.next();

                        key = new Key(keyNumber, keyName);
                        if (!operationsHistoryMap.containsKey(key)) {
                            System.out.println("Введенное сочетание номера и имени не найдено.");
                        } else {
                            System.out.println("Сохранённый результат: " + operationsHistoryMap.get(key).toString());
                        }
                        continue beforeLastAction;

                } else {
                    System.out.println("История операций:");

                    for (Key k : operationsHistoryMap.keySet()) {

                        System.out.println(k.toString() + " " + operationsHistoryMap.get(k).toString());
                    }

                    System.out.println("Выход...");
                    doCalculation = false;
                    break beforeLastAction;
                }
            }

        }

    }

}
