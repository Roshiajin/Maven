package com.epam.maven;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);;
        int operation = 0;
        int firstNumber;
        int secondNumber;
        int nextCalculation;
        boolean doCalculation = true;
        boolean hasOperation;
        double result = 0;


        System.out.println("Добро пожаловать в калькулятор");
        while (doCalculation) {

            hasOperation = false;

            while (!hasOperation) {
                System.out.println("Выберете функцию:\n" +
                        "1 - сложение\n" +
                        "2 - вычитание\n" +
                        "3 - умножение\n" +
                        "4 - деление");

                while (!scanner.hasNextInt()) {
                    scanner.next();
                }

                operation = scanner.nextInt();
                if (operation < 1 || operation > 4) {
                    System.out.println("Неверно выбрана функция!");
                    continue;
                }
                hasOperation = true;
            }

            System.out.println("Введите первое число:");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }

            firstNumber = scanner.nextInt();

            System.out.println("Введите второе число:");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            secondNumber = scanner.nextInt();

            try {
                if (operation == 1) {
                    result = Operation.addition(firstNumber, secondNumber);
                }
                if (operation == 2) {
                    result = Operation.subtraction(firstNumber, secondNumber);
                }
                if (operation == 3) {
                    result = Operation.multiplication(firstNumber, secondNumber);
                }
                if (operation == 4) {
                    result = Operation.division(firstNumber, secondNumber);
                }

                //Print result
                System.out.println("Результат: " + String.valueOf(result));
            } catch (ArithmeticException e) {
                System.out.println("Неверная арифметическая операция!");
            }

            System.out.println("Чтобы продолжить наберите: 1, чтобы выйти - любое другое число");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }

            nextCalculation = scanner.nextInt();
            if (nextCalculation != 1) {
                System.out.println("Выход...");
                doCalculation = false;
            }

        }

    }

}
