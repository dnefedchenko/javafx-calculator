package com.freeman.calculator.service;

import java.util.*;

import static com.freeman.calculator.util.CalculationUtils.*;
import static com.freeman.calculator.util.KeyAction.PLUS;
import static com.freeman.calculator.util.KeyAction.MINUS;
import static com.freeman.calculator.util.KeyAction.MULTIPLY;
import static com.freeman.calculator.util.KeyAction.DIVIDE;
import static com.freeman.calculator.util.KeyAction.POWER;

/**
 * Created by freeman on 07.08.2016.
 */
public class Calculator {
    private Stack<String> result = new Stack<>();
    private Stack<String> output = new Stack<>();
    private Stack<String> operators = new Stack<>();

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
                operators.push(character);
                continue;
            }

            if (isClosingParenthesis(character)) {
                String operator;
                do {
                    operator = operators.pop();
                    output.push(operator);
                } while (!operators.isEmpty() && !isOpeningParenthesis(operators.peek()));
                if (!operators.isEmpty()) {
                    operators.pop();
                }
                if (!operators.isEmpty() && isFunction(operators.peek())) {
                    output.push(operators.pop());
                }
                continue;
            }

            if (isOperator(character)) {
                while (!operators.isEmpty() && !isOpeningParenthesis(operators.peek()) && comparePriorities(character, operators.peek()) <= 0) {
                    output.push(operators.pop());

                    if (!operators.isEmpty() && isOpeningParenthesis(operators.peek())) {
                        operators.pop();
                        break;
                    }
                }
                operators.push(character);
                continue;
            }
        }
        popAllOperators();
    }

    private void popAllOperators() {
        while (!operators.empty()) {
            output.push(operators.pop());
        }
    }

    private int comparePriorities(String firstOperator, String secondOperator) {
        return operatorPriorities.get(firstOperator) - operatorPriorities.get(secondOperator);
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

        Double secondOperand = getOperand();
        Double firstOperand = getOperand();

        if (Objects.equals(PLUS.getAction(), operator)) {
            Double sum = firstOperand + secondOperand;
            operationResult = sum.toString();
        }
        if (Objects.equals(MINUS.getAction(), operator)) {
            Double subtraction = firstOperand - secondOperand;
            operationResult = subtraction.toString();
        }
        if (Objects.equals(MULTIPLY.getAction(), operator)) {
            Double mul = firstOperand * secondOperand;
            operationResult = mul.toString();
        }
        if (Objects.equals(DIVIDE.getAction(), operator)) {
            Double division = firstOperand / secondOperand;
            operationResult = division.toString();
        }
        if (Objects.equals(POWER.getAction(), operator)) {
            Double mul = Math.pow(firstOperand, secondOperand);
            operationResult = mul.toString();
        }
        result.push(operationResult);
    }

    private Double getOperand() {
        String operand = null;
        try {
            operand = result.pop();
        } catch (EmptyStackException e) {
            clearOutput();
            throw new IllegalArgumentException("User's input is invalid");
        }
        if (operand.indexOf(MINUS.getAction()) == 0) {
            operand = operand.replace(MINUS.getAction(), "-");
        }
        return new Double(operand);
    }

    private void clearOutput() {
        output.clear();
    }

    private void clearResult() {
        result.clear();
    }
}
