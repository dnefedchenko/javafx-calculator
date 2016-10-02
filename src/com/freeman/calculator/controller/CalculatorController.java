package com.freeman.calculator.controller;

import com.freeman.calculator.service.Calculator;
import com.freeman.calculator.util.KeyAction;
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
        displayField.textProperty().set(calculator.calculate(displayField.textProperty().get()).concat(" "));
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
        if (startsFromClosingParenthesis(input)) {
            return;
        }

        if (startsFromMinus(input) || isDigit(input) || isPoint(input)) {
            displayField.textProperty().set(displayField.textProperty().get().concat(input));
        } else if(startsFromOpeningParenthesis(input)) {
            displayField.textProperty().set(displayField.textProperty().get().concat(input).concat(" "));
        } else {
            displayField.textProperty().set(displayField.textProperty().get().concat(" ").concat(input).concat(" "));
        }
    }

    private boolean startsFromOpeningParenthesis(String input) {
        return displayField.textProperty().get().isEmpty() && Objects.equals(OPENING_PARENTHESIS, input);
    }

    private boolean startsFromMinus(String input) {
        return displayField.textProperty().get().isEmpty() && Objects.equals(KeyAction.MINUS.getAction(), input);
    }

    private boolean startsFromClosingParenthesis(String input) {
        return displayField.textProperty().get().isEmpty() && Objects.equals(CLOSING_PARENTHESIS, input);
    }
}
