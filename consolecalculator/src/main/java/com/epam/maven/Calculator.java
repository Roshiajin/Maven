package com.epam.maven;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);;
        int operator = 0;
        int nextCalculation;
        boolean doCalculation = true;
        boolean hasOperation;
        Operation operation;
        List<Operation> operationsHistory = new ArrayList<Operation>();


        System.out.println("Добро пожаловать в калькулятор");
        while (doCalculation) {

            hasOperation = false;
            operation = new Operation();

            while (!hasOperation) {
                System.out.println("Выберете функцию:\n" +
                        "1 - сложение\n" +
                        "2 - вычитание\n" +
                        "3 - умножение\n" +
                        "4 - деление");

                while (!scanner.hasNextInt()) {
                    scanner.next();
                }

                operator = scanner.nextInt();
                if (operator < 1 || operator > 4) {
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

            operationsHistory.add(operation);

            System.out.println("Чтобы продолжить наберите: 1, чтобы выйти и распечатать историю - любое другое число");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }

            nextCalculation = scanner.nextInt();
            if (nextCalculation != 1) {
                System.out.println("История операций:");

                for (Operation o : operationsHistory) {

                    System.out.println(o.toString());
                }

                System.out.println("Выход...");
                doCalculation = false;
            }

        }

    }

}
