package org.example;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("Addition: " + calculator.executeOperation(new Addition(), 10, 5));
        System.out.println("Subtraction: " + calculator.executeOperation(new Subtraction(), 10, 5));
        System.out.println("Multiplication: " + calculator.executeOperation(new Multiplication(), 10, 5));
        System.out.println("Division: " + calculator.executeOperation(new Division(), 10, 5));
    }
}
