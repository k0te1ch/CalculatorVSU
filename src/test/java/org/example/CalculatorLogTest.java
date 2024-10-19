package org.example;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorLogTest {

    private final Calculator calculator = new Calculator();

    // Custom appender for capture logging events
    private static class TestAppender extends AppenderBase<ILoggingEvent> {
        private final List<ILoggingEvent> logEvents = new ArrayList<>();

        @Override
        protected void append(ILoggingEvent eventObject) {
            logEvents.add(eventObject);
        }

        public List<ILoggingEvent> getLogEvents() {
            return logEvents;
        }

    }

    private TestAppender testAppender;

    @BeforeEach
    public void setup() {
        Logger calculatorLogger = (Logger) LoggerFactory.getLogger(Calculator.class);
        Logger divisionLogger = (Logger) LoggerFactory.getLogger(Division.class);

        testAppender = new TestAppender();
        calculatorLogger.addAppender(testAppender);
        divisionLogger.addAppender(testAppender);
        testAppender.start();
    }

    @AfterEach
    public void tearDown() {
        Logger calculatorLogger = (Logger) LoggerFactory.getLogger(Calculator.class);
        Logger divisionLogger = (Logger) LoggerFactory.getLogger(Division.class);

        if (testAppender != null) {
            if (calculatorLogger.isAttached(testAppender)) {
                calculatorLogger.detachAppender(testAppender);
            }
            if (divisionLogger.isAttached(testAppender)) {
                divisionLogger.detachAppender(testAppender);
            }
            testAppender.stop();
        }
    }


    @Test
    public void testDivisionByZeroLogsError() {
        assertThrows(ArithmeticException.class, () -> calculator.executeOperation(new Division(), 6, 0));

        List<ILoggingEvent> divisionLogs = testAppender.getLogEvents();
        assertEquals(2, divisionLogs.size(), "Expected exactly two logging events");

        String logMessage1 = divisionLogs.get(0).getFormattedMessage();
        assertTrue(logMessage1.contains("Executing operation"), "Expected log to contain 'Executing operation'");
        assertTrue(logMessage1.contains("Division with values: 6.0 and 0.0"), "Expected log to mention 'Division with values: 6.0 and 0.0'");

        String logMessage2 = divisionLogs.get(1).getFormattedMessage();
        assertTrue(logMessage2.contains("Error during division"), "Expected error log to contain 'Error during division'");
        assertTrue(logMessage2.contains("Division by zero"), "Expected error log to mention 'Division by zero'");

        assertEquals(Level.INFO, divisionLogs.get(0).getLevel(), "Expected INFO level log for first message");
        assertEquals(Level.ERROR, divisionLogs.get(1).getLevel(), "Expected ERROR level log for second message");
    }

    @Test
    public void testAdditionLogsInfo() {
        calculator.executeOperation(new Addition(), 2, 3);

        List<ILoggingEvent> additionLogs = testAppender.getLogEvents();
        assertEquals(2, additionLogs.size(), "Expected exactly two logging events");

        String logMessage1 = additionLogs.get(0).getFormattedMessage();
        assertTrue(logMessage1.contains("Executing operation"), "Expected log to contain 'Executing operation'");
        assertTrue(logMessage1.contains("Addition with values: 2.0 and 3.0"), "Expected log to mention 'Addition with values: 2.0 and 3.0'");

        String logMessage2 = additionLogs.get(1).getFormattedMessage();
        assertTrue(logMessage2.contains("Result"), "Expected log to contain 'Result'");
        assertTrue(logMessage2.contains("5.0"), "Expected log to mention '5.0'");

        assertEquals(Level.INFO, additionLogs.get(0).getLevel(), "Expected INFO level log for first message");
        assertEquals(Level.INFO, additionLogs.get(1).getLevel(), "Expected INFO level log for second message");
    }

    @Test
    public void testSubtractionLogsInfo() {
        calculator.executeOperation(new Subtraction(), 5, 3);

        List<ILoggingEvent> subtractionLogs = testAppender.getLogEvents();
        assertEquals(2, subtractionLogs.size(), "Expected exactly two logging events");

        String logMessage1 = subtractionLogs.get(0).getFormattedMessage();
        assertTrue(logMessage1.contains("Executing operation"), "Expected log to contain 'Executing operation'");
        assertTrue(logMessage1.contains("Subtraction with values: 5.0 and 3.0"), "Expected log to mention 'Subtraction with values: 5.0 and 3.0'");

        String logMessage2 = subtractionLogs.get(1).getFormattedMessage();
        assertTrue(logMessage2.contains("Result"), "Expected log to contain 'Result'");
        assertTrue(logMessage2.contains("2.0"), "Expected log to mention '2.0'");

        assertEquals(Level.INFO, subtractionLogs.get(0).getLevel(), "Expected INFO level log for first message");
        assertEquals(Level.INFO, subtractionLogs.get(1).getLevel(), "Expected INFO level log for second message");
    }

    @Test
    public void testMultiplicationLogsInfo() {
        calculator.executeOperation(new Multiplication(), 3, 4);

        List<ILoggingEvent> multiplicationLogs = testAppender.getLogEvents();
        assertEquals(2, multiplicationLogs.size(), "Expected exactly two logging events");

        String logMessage1 = multiplicationLogs.get(0).getFormattedMessage();
        assertTrue(logMessage1.contains("Executing operation"), "Expected log to contain 'Executing operation'");
        assertTrue(logMessage1.contains("Multiplication with values: 3.0 and 4.0"), "Expected log to mention 'Multiplication with values: 3.0 and 4.0'");

        String logMessage2 = multiplicationLogs.get(1).getFormattedMessage();
        assertTrue(logMessage2.contains("Result"), "Expected log to contain 'Result'");
        assertTrue(logMessage2.contains("12.0"), "Expected log to mention '12.0'");

        assertEquals(Level.INFO, multiplicationLogs.get(0).getLevel(), "Expected INFO level log for first message");
        assertEquals(Level.INFO, multiplicationLogs.get(1).getLevel(), "Expected INFO level log for second message");
    }

}
