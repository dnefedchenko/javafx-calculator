package com.freeman.calculator.util;

import java.util.*;

import static com.freeman.calculator.util.KeyAction.*;
import static com.freeman.calculator.util.KeyAction.POWER;

/**
 * Created by freeman on 28.08.2016.
 */
public class CalculationUtils {
    public static final String DIGIT_PATTERN = "[−+-]?[0-9]*\\.?[0-9]";
    public static final String OPERATOR_PATTERN = "[-+−×÷^]";

    public static final String OPENING_PARENTHESIS = "(";
    public static final String CLOSING_PARENTHESIS = ")";
    public static List<String> functions = Arrays.asList("^", "exp");
    public static Map<String, Integer> operatorPriorities = new HashMap<>();

    static {
        operatorPriorities.put(PLUS.getAction(), 0);
        operatorPriorities.put(MINUS.getAction(), 0);
        operatorPriorities.put(MULTIPLY.getAction(), 1);
        operatorPriorities.put(DIVIDE.getAction(), 1);
        operatorPriorities.put(POWER.getAction(), 2);
    }

    public static boolean isDigit(String character) {
        return character.matches(CalculationUtils.DIGIT_PATTERN);
    }

    public static boolean isFunction(String character) {
        return CalculationUtils.functions.contains(character);
    }

    public static boolean isOpeningParenthesis(String character) {
        return Objects.equals(character, CalculationUtils.OPENING_PARENTHESIS);
    }

    public static boolean isClosingParenthesis(String character) {
        return Objects.equals(character, CalculationUtils.CLOSING_PARENTHESIS);
    }

    public static boolean isOperator(String character) {
        return character.matches(CalculationUtils.OPERATOR_PATTERN);
    }
}
