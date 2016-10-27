package com.freeman.calculator.controller;

import com.freeman.calculator.InputFormatter;
import com.freeman.calculator.service.Calculator;
import com.freeman.calculator.util.CalculationUtils;
import com.freeman.calculator.util.KeyAction;
import com.freeman.calculator.validator.InputValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;
import java.util.Objects;

import static com.freeman.calculator.util.CalculationUtils.CLOSING_PARENTHESIS;

/**
 * Created by freeman on 31.07.2016.
 */
public class CalculatorController {
    @FXML private TextField displayField;
    @FXML private GridPane keyboard;

    @FXML private Button CLEAR_ENTRY;
    @FXML private Button CLEAR_ALL;

    private Calculator calculator;

    @FXML
    private void initialize() {
        calculator = new Calculator();
        initKeyListeners();
        toggleClearButtonsVisibility(true);
    }

    private void initKeyListeners() {
        keyboard.getChildren().forEach(key -> {
            ((Button)key).setOnAction(event -> {
                handleInput(KeyAction.valueOf(key.getId()));
            });
        });
    }

    private void handleInput(KeyAction keyAction) {
        switch (keyAction) {
            case EQUALS:
                calculateResult();
                toggleClearButtonsVisibility(false);
                break;
            case CLEAR_ENTRY:
                clearEntry();
                break;
            case CLEAR_ALL:
                clearAll();
                toggleClearButtonsVisibility(true);
                break;
            default:
                processInput(keyAction.getAction());
                break;
        }
    }

    private void calculateResult() {
        String result = null;
        try {
            result = calculator.calculate(displayField.textProperty().get());
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot calculate result. Invalid input expression.");
            result = "Error";
        }
        displayField.textProperty().set(InputFormatter.format(result).concat(" "));
    }

    private String format(String result) {
        return InputValidator.containsTrailingFloatingPointZero(result)
                ? result.substring(0, result.indexOf('0') - 1) : String.format("%.9f%n", Double.parseDouble(result));
    }

    private void clearEntry() {
        if (displayField.textProperty().get().isEmpty()) {
            return;
        }
        displayField.textProperty().set(displayField.textProperty().get().substring(0, displayField.textProperty().get().length() - 1));
    }

    private void toggleClearButtonsVisibility(boolean visibility) {
        CLEAR_ENTRY.setVisible(visibility);
        CLEAR_ALL.setVisible(!visibility);
    }

    private void clearAll() {
        displayField.clear();
    }

    private void processInput(String input) {
        if (!InputValidator.validate(displayField.textProperty().get().trim(), input)) {
            return;
        }

        if (InputValidator.containsAllowedLeadingOperand(input) || InputValidator.isDigit(input) || InputValidator.isPoint(input)) {
            displayField.textProperty().set(displayField.textProperty().get().concat(input));
        } else if (InputValidator.containsLeadingOpeningParenthesis(input)) {
            displayField.textProperty().set(displayField.textProperty().get().concat(input).concat(" "));
        } else {
            displayField.textProperty().set(displayField.textProperty().get().concat(" ").concat(input).concat(" "));
        }
    }

    private boolean startsFromOpeningParenthesis(String input) {
        return displayField.textProperty().get().isEmpty() && CalculationUtils.isOpeningParenthesis(input);
    }

    private boolean startsFromMinus(String input) {
        return displayField.textProperty().get().isEmpty() && CalculationUtils.isMinus(input);
    }

    private boolean startsFromClosingParenthesis(String input) {
        return displayField.textProperty().get().isEmpty() && Objects.equals(CLOSING_PARENTHESIS, input);
    }
}
