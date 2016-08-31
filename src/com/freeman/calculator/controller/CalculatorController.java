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

import static com.freeman.calculator.util.CalculationUtils.*;

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
//        initDisplayListener();
    }

    private void initKeyListeners() {
        keyboard.getChildren().forEach(key -> {
            ((Button)key).setOnAction(event -> {
                handleInput(KeyAction.valueOf(key.getId()));
            });
        });
    }

    private void initDisplayListener() {
        displayField.textProperty().addListener((observable, oldValue, newValue) -> {
            String[] inputCharacters = newValue.split("\\s");
            String lastTypedCharacter = inputCharacters[inputCharacters.length - 1];

            if (!isDigit(lastTypedCharacter) && !isOpeningParenthesis(lastTypedCharacter) && !isPoint(lastTypedCharacter)
                    && !isClosingParenthesis(lastTypedCharacter) && !isOperator(lastTypedCharacter)) {
                displayField.textProperty().setValue("");
            } else if (!displayField.textProperty().getValue().endsWith(" ")) {
                displayField.textProperty().setValue(newValue.concat(" "));
            }
        });
    }

    private void handleInput(KeyAction keyAction) {
        if (KeyAction.EQUALS == keyAction) {
            calculateResult();
        } else if (KeyAction.CLEAR == keyAction) {
            clearEntry();
        } else {
            processInput(keyAction.getAction());
        }
    }

    private void calculateResult() {
        currentInput.set(calculator.calculate(currentInput.getValue()).concat(" "));
    }

    private void clearEntry() {
        if (currentInput.get().isEmpty()) {
            return;
        }
        currentInput.set(currentInput.get().substring(0, currentInput.get().length() - 1));
    }

    private void processInput(String input) {
        if (startsFromClosingParenthesis(input)) {
            return;
        }

        if (startsFromMinus(input) || isDigit(input) || isPoint(input)) {
            currentInput.set(currentInput.get().concat(input));
        } else if(startsFromOpeningParenthesis(input)) {
            currentInput.set(currentInput.get().concat(input).concat(" "));
        } else {
            currentInput.set(currentInput.get().concat(" ").concat(input).concat(" "));
        }
    }

    private boolean startsFromOpeningParenthesis(String input) {
        return currentInput.get().isEmpty() && Objects.equals(OPENING_PARENTHESIS, input);
    }

    private boolean startsFromMinus(String input) {
        return currentInput.get().isEmpty() && Objects.equals(KeyAction.MINUS.getAction(), input);
    }

    private boolean startsFromClosingParenthesis(String input) {
        return currentInput.get().isEmpty() && Objects.equals(CLOSING_PARENTHESIS, input);
    }
}
