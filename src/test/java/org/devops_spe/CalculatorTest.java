package org.devops_spe;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testSqrt() {
        assertEquals(5.0, calculator.sqrt(25), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSqrtNegative() {
        calculator.sqrt(-4);
    }

    @Test
    public void testFactorial() {
        assertEquals(120, calculator.factorial(5));
    }

    @Test
    public void testNaturalLog() {
        assertEquals(0.0, calculator.naturalLog(1), 0.0001);
    }

    @Test
    public void testPower() {
        assertEquals(8.0, calculator.power(2, 3), 0.0001);
    }
}