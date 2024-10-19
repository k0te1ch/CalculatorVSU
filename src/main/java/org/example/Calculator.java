package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    public double executeOperation(Operation operation, double a, double b) {
        logger.info("Executing operation: {} with values: {} and {}", operation.getClass().getSimpleName(), a, b);
        double result = operation.calculate(a, b);
        logger.info("Result: {}", result);
        return result;
    }
}
