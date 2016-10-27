package com.freeman.calculator.validator;

import com.freeman.calculator.util.CalculationUtils;

import java.util.regex.Pattern;

import static com.freeman.calculator.util.KeyAction.POINT;

/**
 * Created by freeman on 12.10.2016.
 */
public class InputValidator {
    public static Pattern LEADING_OPENING_PARENTHESIS_PATTERN = Pattern.compile("^[(]");
    public static Pattern LEADING_CLOSING_PARENTHESIS_PATTERN = Pattern.compile("^[)]");
    public static Pattern LEADING_OPERAND_PATTERN = Pattern.compile("^[+×^=/]");
    public static Pattern TRAILING_OPERAND_PATTERN = Pattern.compile("[-+×^/]$");
    public static Pattern ALLOWED_LEADING_OPERAND_PATTERN = Pattern.compile("^[-(\\d]");
    public static Pattern DIGIT_PATTERN = Pattern.compile("[−+-]?[0-9]*\\.?[0-9]");
    public static Pattern OPENING_PARENTHESIS_PATTERN = Pattern.compile("[(]");
    public static Pattern CLOSING_PARENTHESIS_PATTERN = Pattern.compile("[)]");
    public static Pattern OPERATOR_PATTERN = Pattern.compile("[-+−×÷^]");
    public static Pattern TRAILING_FLOATING_POINT_PATTERN = Pattern.compile("(.0{1,})$");
    
    public static boolean validate(String fullExpression, String currentInput) {
        if (currentInput.isEmpty() && !containsAllowedLeadingOperand(currentInput)) {
            return false;
        } else if (!isDigit(currentInput) && !isOperator(currentInput) && !isPoint(currentInput)) {
            return false;
        } else if (containsTrailingOperand(fullExpression) && isOperator(currentInput)) {
            return false;
        }
        return true;
    }

    public static boolean containsAllowedLeadingOperand(String input) {
        return ALLOWED_LEADING_OPERAND_PATTERN.matcher(input).find();
    }

    public static boolean containsLeadingOpeningParenthesis(String input) {
        return LEADING_OPENING_PARENTHESIS_PATTERN.matcher(input).find();
    }

    public static boolean containsOpeningParenthesis(String input) {
        return OPENING_PARENTHESIS_PATTERN.matcher(input).find();
    }

    public static boolean containsLeadingClosingParenthesis(String input) {
        return LEADING_CLOSING_PARENTHESIS_PATTERN.matcher(input).find();
    }

    public static boolean containsClosingParenthesis(String input) {
        return CLOSING_PARENTHESIS_PATTERN.matcher(input).find();
    }

    public static boolean containsLeadingOperand(String input) {
        return LEADING_OPERAND_PATTERN.matcher(input).find();
    }

    public static boolean isOperator(String input) {
        return OPERATOR_PATTERN.matcher(input).find();
    }

    public static boolean containsTrailingOperand(String input) {
        return TRAILING_OPERAND_PATTERN.matcher(input).find();
    }

    public static boolean isDigit(String input) {
        return DIGIT_PATTERN.matcher(input).matches();
    }

    public static boolean isPoint(String character) {
        return character.equals(POINT.getAction());
    }

    public static boolean containsTrailingFloatingPointZero(String input) {
        return TRAILING_FLOATING_POINT_PATTERN.matcher(input).find();
    }
}
