package com.freeman.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static com.freeman.calculator.KeyAction.PLUS;
import static com.freeman.calculator.KeyAction.MINUS;
import static com.freeman.calculator.KeyAction.MULTIPLY;
import static com.freeman.calculator.KeyAction.DIVIDE;

/**
 * Created by freeman on 07.08.2016.
 */
public class CalculatorTest {
    private static String EXPRESSION = "3 + 4 × 2 ÷ ( 1 − 5 ) ^ 2";

    private Calculator testee;

    @Before
    public void setUp() {
        testee = new Calculator();
    }

    @Test
    public void testAdding() {
        assertEquals("One plus two equals three", "3.0", testee.calculate("1 " + PLUS.action + " 2"));
    }

    @Test
    public void testSubtraction() {
        assertEquals("Three minus one equals two", "2.0", testee.calculate("3 " + MINUS.action + " 1"));
    }

    @Test
    public void testMultiplication() {
        assertEquals("Three multiplied by two equals six", "6.0", testee.calculate("3 " + MULTIPLY.action + " 2"));
    }

    @Test
    public void testDivision() {
        assertEquals("Six divided by two equals three", "3.0", testee.calculate("6 " + DIVIDE.action + " 2"));
    }

    @Test
    public void testExpression() {
        assertEquals("Evaluated expression equals 3.5", "3.5", testee.calculate(EXPRESSION));
    }
}
