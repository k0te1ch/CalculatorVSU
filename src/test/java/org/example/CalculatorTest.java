package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void testAddition() {
        assertEquals(5, calculator.executeOperation(new Addition(), 2, 3));
    }

    @Test
    public void testSubtraction() {
        assertEquals(1, calculator.executeOperation(new Subtraction(), 3, 2));
    }

    @Test
    public void testMultiplication() {
        assertEquals(6, calculator.executeOperation(new Multiplication(), 2, 3));
    }

    @Test
    public void testDivision() {
        assertEquals(2, calculator.executeOperation(new Division(), 6, 3));
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.executeOperation(new Division(), 6, 0));
    }
}
