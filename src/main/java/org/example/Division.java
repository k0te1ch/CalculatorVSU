package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Division implements Operation {

    private static final Logger logger = LoggerFactory.getLogger(Division.class);

    @Override
    public double calculate(double a, double b) {
        try {
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        } catch (ArithmeticException e) {
            logger.error("Error during division: {}", e.getMessage(), e);
            throw e;
        }
    }
}
