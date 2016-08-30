package com.freeman.calculator;

import com.freeman.calculator.service.Calculator;
import org.junit.Before;
import org.junit.Test;

import static com.freeman.calculator.util.KeyAction.*;
import static org.junit.Assert.assertEquals;

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
        assertEquals("One plus two equals three", "3.0", testee.calculate("1 + 2"));
    }

    @Test
    public void testSubtraction() {
        assertEquals("Three minus one equals two", "2.0", testee.calculate("3 " + MINUS.action + " 1"));
    }

    @Test
    public void testUnaryMinus() {
        assertEquals("Minus one minus two equals minus three", "-3.0", testee.calculate(MINUS.getAction() + "1 " + MINUS.getAction() + " 2"));
    }

    @Test
    public void testUnaryMinusWithParenthesis() {
        assertEquals("(-101 + 102) = 1", "1.0", testee.calculate("( -101 + 102 )"));
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

    @Test
    public void testFloatingPointExpression() {
        assertEquals("(-101.0 + 102.0)", "1.0", testee.calculate("-101.0 + 102.0"));
    }
}
