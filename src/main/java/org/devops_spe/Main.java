package org.devops_spe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nScientific Calculator Menu:");
            System.out.println("1. Square Root (√x)");
            System.out.println("2. Factorial (!x)");
            System.out.println("3. Natural Logarithm (ln x)");
            System.out.println("4. Power (x^b)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter a number: ");
                        double sqrtInput = sc.nextDouble();
                        System.out.println("√" + sqrtInput + " = " + calculator.sqrt(sqrtInput));
                        break;

                    case 2:
                        System.out.print("Enter an integer: ");
                        int factInput = sc.nextInt();
                        System.out.println(factInput + "! = " + calculator.factorial(factInput));
                        break;

                    case 3:
                        System.out.print("Enter a positive number: ");
                        double lnInput = sc.nextDouble();
                        System.out.println("ln(" + lnInput + ") = " + calculator.naturalLog(lnInput));
                        break;

                    case 4:
                        System.out.print("Enter base: ");
                        double base = sc.nextDouble();
                        System.out.print("Enter exponent: ");
                        double exponent = sc.nextDouble();
                        System.out.println(base + "^" + exponent + " = " + calculator.power(base, exponent));
                        break;

                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Try again!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}