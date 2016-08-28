package com.freeman.calculator;

import java.util.*;

/**
 * Created by freeman on 07.08.2016.
 */
public class Calculator {
    private static final String DIGIT_PATTERN = "[-+]?[0-9]*\\.?[0-9]";
    private static final String OPERATOR_PATTERN = "[+−×÷^]";

    private static final String OPENING_PARENTHESIS = "(";
    private static final String CLOSING_PARENTHESIS = ")";

    private Stack<String> result = new Stack<>();
    private Stack<String> output = new Stack<>();
    private Stack<String> operatorsStack = new Stack<>();

    private List<String> functions = Arrays.asList("^", "exp");
    private Map<String, Integer> operatorPriorities = new HashMap<>();

    public Calculator() {
        operatorPriorities.put(Operator.PLUS.operator, 0);
        operatorPriorities.put(Operator.MINUS.operator, 0);
        operatorPriorities.put(Operator.MUL.operator, 1);
        operatorPriorities.put(Operator.DIV.operator, 1);
        operatorPriorities.put(Operator.POW.operator, 2);
    }

    public String calculate(String input) {
        parse(input);
        calculate();
        return result.pop();
    }

    private void parse(String input) {
        String[] characters = input.trim().split("\\s");
        for (int i = 0; i < characters.length; i++) {
            String character = characters[i];

            if (isDigit(character)) {
                output.push(character);
                continue;
            }

            if (isFunction(character) || isOpeningParenthesis(character)) {
                operatorsStack.push(character);
                continue;
            }

            if (isClosingParenthesis(character)) {
                String operator;
                do {
                    operator = operatorsStack.pop();
                    output.push(operator);
                } while (!operatorsStack.isEmpty() && !isOpeningParenthesis(operatorsStack.peek()));
                if (!operatorsStack.isEmpty()) {
                    operatorsStack.pop();
                }
                if (!operatorsStack.isEmpty() &&isFunction(operatorsStack.peek())) {
                    output.push(operatorsStack.pop());
                }
                continue;
            }

            if (isOperator(character)) {
                while (!operatorsStack.isEmpty() && !isOpeningParenthesis(operatorsStack.peek()) && comparePriorities(character, operatorsStack.peek()) <= 0) {
                    output.push(operatorsStack.pop());

                    if (!operatorsStack.isEmpty() && isOpeningParenthesis(operatorsStack.peek())) {
                        operatorsStack.pop();
                        break;
                    }
                }
                operatorsStack.push(character);
                continue;
            }
        }
        popAllOperators();
    }

    private int comparePriorities(String firstOperator, String secondOperator) {
        return operatorPriorities.get(firstOperator) - operatorPriorities.get(secondOperator);
    }

    private boolean isDigit(String character) {
        return character.matches(DIGIT_PATTERN);
    }

    private boolean isFunction(String character) {
        return functions.contains(character);
    }

    private boolean isOpeningParenthesis(String character) {
        return Objects.equals(character, OPENING_PARENTHESIS);
    }

    private boolean isClosingParenthesis(String character) {
        return Objects.equals(character, CLOSING_PARENTHESIS);
    }

    private boolean isOperator(String character) {
        return character.matches(OPERATOR_PATTERN);
    }

    private void popAllOperators() {
        while (!operatorsStack.empty()) {
            output.push(operatorsStack.pop());
        }
    }

    private void calculate() {
        output.forEach(token -> {
            if (isDigit(token)) {
                result.push(token);
            } else {
                executeOperation(token);
            }
        });
        output.clear();
    }

    private void executeOperation(String operator) {
        String operationResult = "";
        Double secondOperand = new Double(result.pop());
        Double firstOperand = new Double(result.pop());

        if (Objects.equals(Operator.PLUS.operator, operator)) {
            Double sum = firstOperand + secondOperand;
            operationResult = sum.toString();
        }
        if (Objects.equals(Operator.MINUS.operator, operator)) {
            Double subtraction = firstOperand - secondOperand;
            operationResult = subtraction.toString();
        }
        if (Objects.equals(Operator.MUL.operator, operator)) {
            Double mul = firstOperand * secondOperand;
            operationResult = mul.toString();
        }
        if (Objects.equals(Operator.DIV.operator, operator)) {
            Double division = firstOperand / secondOperand;
            operationResult = division.toString();
        }
        if (Objects.equals(Operator.POW.operator, operator)) {
            Double mul = Math.pow(firstOperand, secondOperand);
            operationResult = mul.toString();
        }
        result.push(operationResult);
    }

    enum Operator {
        PLUS("\u002B"),
        MINUS("\u2212"),
        MUL("\u00D7"),
        DIV("\u00F7"),
        POW("^");

        String operator;

        Operator(String operator) {
            this.operator = operator;
        }
    }
}
