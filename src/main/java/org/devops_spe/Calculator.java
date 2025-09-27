package org.devops_spe;

public class Calculator {

    // Square root
    public double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(x);
    }

    // Factorial
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot calculate factorial of negative number");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Natural logarithm
    public double naturalLog(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Natural log is only defined for positive numbers");
        }
        return Math.log(x);
    }

    // Power function
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}