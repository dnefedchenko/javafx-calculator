package com.freeman.calculator.controller;

import com.freeman.calculator.util.CalculationUtils;
import com.freeman.calculator.service.Calculator;
import com.freeman.calculator.util.KeyAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Objects;

import static com.freeman.calculator.util.CalculationUtils.OPENING_PARENTHESIS;
import static com.freeman.calculator.util.CalculationUtils.isDigit;
import static com.freeman.calculator.util.CalculationUtils.isPoint;

/**
 * Created by freeman on 31.07.2016.
 */
public class CalculatorController {
    @FXML private TextField displayField;
    @FXML private GridPane keyboard;

    private Calculator calculator;
    private StringProperty currentInput;

    @FXML
    private void initialize() {
        calculator = new Calculator();
        currentInput = new SimpleStringProperty("");
        displayField.textProperty().bindBidirectional(currentInput);
        initKeyListeners();
    }

    private void initKeyListeners() {
        keyboard.getChildren().forEach(key -> {
            ((Button)key).setOnAction(event -> {
                handleInput(KeyAction.valueOf(key.getId()));
            });
        });
    }

    private void handleInput(KeyAction keyAction) {
        if (KeyAction.EQUALS == keyAction) {
            calculateResult();
        } else {
            processInput(keyAction.getAction());
        }
    }

    private void calculateResult() {
        currentInput.set(calculator.calculate(currentInput.getValue()).concat(" "));
    }

    private void processInput(String input) {
        if (currentInput.get().isEmpty() && Objects.equals(KeyAction.MINUS.getAction(), input)
                || currentInput.get().lastIndexOf(OPENING_PARENTHESIS) == currentInput.get().length() - 1
                || isDigit(input) || isPoint(input)) {
            currentInput.set(currentInput.get().concat(input));
        } else {
            currentInput.set(currentInput.get().concat(" ").concat(input).concat(" "));
        }
    }
}
