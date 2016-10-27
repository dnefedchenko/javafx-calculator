package com.freeman.calculator;

import com.freeman.calculator.validator.InputValidator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by freeman on 21.10.2016.
 */
public class InputValidatorTest {
    @Test
    public void assertInputStartsFromOpeningParenthesis() {
        Assert.assertTrue(InputValidator.containsLeadingOpeningParenthesis("(1+2)*3"));
    }

    @Test
    public void assertInputDoesNotStartFromOpeningParenthesis() {
        Assert.assertFalse(InputValidator.containsLeadingOpeningParenthesis("1+2"));
    }

    @Test
    public void assertInputStartsFromClosingParenthesis() {
        Assert.assertTrue(InputValidator.containsLeadingClosingParenthesis(")1+2"));
    }

    @Test
    public void assertInputDoesNotStartFromClosingParenthesis() {
        Assert.assertFalse(InputValidator.containsLeadingClosingParenthesis("1+2"));
    }

    @Test
    public void assertInputStartsFromOperand() {
        Assert.assertTrue(InputValidator.containsLeadingOperand("+1+2"));
        Assert.assertTrue(InputValidator.containsLeadingOperand("×1+2"));
        Assert.assertTrue(InputValidator.containsLeadingOperand("/1+2"));
        Assert.assertTrue(InputValidator.containsLeadingOperand("=1+2"));
        Assert.assertTrue(InputValidator.containsLeadingOperand("^1+2"));
    }

    @Test
    public void assertInputDoesNotStartFromOperand() {
        Assert.assertFalse(InputValidator.containsLeadingOperand("1+2"));
    }

    @Test
    public void assertInputEndsWithOperand() {
        Assert.assertTrue(InputValidator.containsTrailingOperand("1-"));
        Assert.assertTrue(InputValidator.containsTrailingOperand("1+"));
        Assert.assertTrue(InputValidator.containsTrailingOperand("1×"));
        Assert.assertTrue(InputValidator.containsTrailingOperand("1^"));
        Assert.assertTrue(InputValidator.containsTrailingOperand("1/"));
    }

    @Test
    public void assertInputDoesNotEndWithOperand() {
        Assert.assertFalse(InputValidator.containsTrailingOperand("1+2"));
    }

    @Test
    public void assertInputStartsFromAllowedCharacter() {
        Assert.assertTrue(InputValidator.containsAllowedLeadingOperand("-1"));
        Assert.assertTrue(InputValidator.containsAllowedLeadingOperand("-11"));
        Assert.assertTrue(InputValidator.containsAllowedLeadingOperand("(1"));
        Assert.assertTrue(InputValidator.containsAllowedLeadingOperand("-(1"));
    }

    @Test
    public void assertInputDoesNotStartFromAllowedCharacter() {
        Assert.assertFalse(InputValidator.containsAllowedLeadingOperand("*1"));
    }

    @Test
    public void assertInputIsDigital() {
        Assert.assertTrue(InputValidator.isDigit("-1"));
        Assert.assertTrue(InputValidator.isDigit("-11"));
        Assert.assertTrue(InputValidator.isDigit("-1.1"));
        Assert.assertTrue(InputValidator.isDigit("11"));
        Assert.assertTrue(InputValidator.isDigit("11.1"));
    }

    @Test
    public void assertInputIsNotDigital() {
        Assert.assertFalse(InputValidator.isDigit("(1+2)"));
    }

    @Test
    public void assertInputContainsOpeningParenthesis() {
        Assert.assertTrue(InputValidator.containsOpeningParenthesis("1-(3-2)"));
        Assert.assertTrue(InputValidator.containsOpeningParenthesis("(3-2)"));
    }

    @Test
    public void assertInputDoesNotContainOpeningParenthesis() {
        Assert.assertFalse(InputValidator.containsOpeningParenthesis("1+1"));
    }

    @Test
    public void assertInputContainsClosingParenthesis() {
        Assert.assertTrue(InputValidator.containsClosingParenthesis("(3-2)"));
    }

    @Test
    public void assertInputIsPoint() {
        Assert.assertTrue(InputValidator.isPoint("."));
    }

    @Test
    public void assertInputIsNotPoint() {
        Assert.assertFalse(InputValidator.isPoint("1+2"));
    }

    @Test
    public void assertInputContainsTrailingZeroFloatingPoint() {
        Assert.assertTrue(InputValidator.containsTrailingFloatingPointZero("1.0"));
        Assert.assertTrue(InputValidator.containsTrailingFloatingPointZero("1.00000"));
        Assert.assertTrue(InputValidator.containsTrailingFloatingPointZero("1.030"));
        Assert.assertTrue(InputValidator.containsTrailingFloatingPointZero("100.0"));
    }

    @Test
    public void assertInputDoesNotContainTrailingZeroFloatingPoint() {
        Assert.assertFalse(InputValidator.containsTrailingFloatingPointZero("1.1"));
        Assert.assertFalse(InputValidator.containsTrailingFloatingPointZero("100.2"));
    }
}
